package main;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.reflect.Type;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import components.Clients;
import components.Accounts.Accounts;
import components.Accounts.CurrentAccount;
import components.Accounts.SavingAccount;
import components.Flow.Credit;
import components.Flow.Debit;
import components.Flow.Flow;
import components.Flow.Transfert;





public class Main {
	public static void main(String args[]) {
		//1.1.2
		//Clients[] clientsArray = generateClients(3);
		List<Clients> clientsArray = generateClients(3);
		
		displayClients(clientsArray);
		System.out.println();
		
		//1.2.3 && //2.2
		//Accounts[] accountsArray = generateAccountsForClients(clientsArray);
		List<Accounts> accountsArray = loadAccountsFromXml("./data/accountsToLoad.xml", clientsArray);
		
		displayAccountsArray(accountsArray);
		System.out.println();
		
		//1.3.1
		Map<Integer, Accounts> accountsMap = generateMapAccountsFromArray(accountsArray);
		
		displayAccountsMap(accountsMap);
		System.out.println();
		
		//1.3.4
		//List<Flow> flowsArray = generateFlowArray(accountsArray);
		List<Flow> flowsArray = loadFlowsFromJson("./data/flowsToLoad.json");
		 
		//1.3.5
		performFlowsToAccounts(flowsArray, accountsMap);
		System.out.println();
		
		displayAccountsMap(accountsMap);
		System.out.println();
		
		//2.1
		List<Flow> flowsArrayFromJson = loadFlowsFromJson("./data/flowsToLoad.json");
		
		flowsArrayFromJson.forEach(System.out::println);

	}
	
	//2.2
	////////////Load Accounts from XML function ////////////////
	
	public static ArrayList<Accounts> loadAccountsFromXml(String filename, List<Clients> clientsArray) {
        ArrayList<Accounts> accounts = new ArrayList<>();

        try {
            File xmlFile = new File(filename);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // Get currentAccount nodes
            NodeList currentNodes = doc.getElementsByTagName("currentAccount");
            for (int i = 0; i < currentNodes.getLength(); i++) {
                Node node = currentNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    CurrentAccount ca = new CurrentAccount();
                    parseAccountElement((Element) node, ca, clientsArray);
                    accounts.add(ca);
                }
            }

            // Get savingAccount nodes
            NodeList savingNodes = doc.getElementsByTagName("savingAccount");
            for (int i = 0; i < savingNodes.getLength(); i++) {
                Node node = savingNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    SavingAccount sa = new SavingAccount();
                    parseAccountElement((Element) node, sa, clientsArray);
                    accounts.add(sa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private static void parseAccountElement(Element element, Accounts account, List<Clients> clientsArray) {
        account.setLabel(getTagValue("label", element));
        account.setBalance(Double.parseDouble(getTagValue("balance", element)));
        account.setId(Integer.parseInt(getTagValue("id", element)));

        Element clientElement = (Element) element.getElementsByTagName("client").item(0);
        if (clientElement != null) {
            String name = getTagValue("name", clientElement);
            String firstname = getTagValue("firstName", clientElement);
            account.setClient(getClient(clientsArray, name, firstname));
        }
    }

    private static String getTagValue(String tag, Element element) {
        NodeList list = element.getElementsByTagName(tag);
        if (list != null && list.getLength() > 0) {
            Node node = list.item(0);
            return node.getTextContent();
        }
        return null;
    }
    /////////////////////////////////////////////
	
	////////////Flow function //////////////////

	public static List<Flow> loadFlowsFromJson(String pathName) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Flow> flows = List.of(mapper.readValue(new File(pathName), Flow[].class));

            // Print them
            System.out.println("Load Flows from Json result : ");
            flows.forEach(f -> System.out.println(f.getClass().getSimpleName() + " -> " + f.toString()));
            System.out.println();
            
            return flows;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
	
	public static void performFlowsToAccounts(List<Flow> flowsArray, Map<Integer, Accounts> accountsMap) {
		for (Flow flow : flowsArray ) {
			accountsMap.get(flow.getAccountId()).setBalance(flow);
		}
		
		Predicate<Accounts> isNegative = acc -> acc.getBalance() < 0;

        Optional<Accounts> negativeAccount = accountsMap.values().stream()
            .filter(isNegative)
            .findFirst();

        negativeAccount.ifPresent(acc ->
            System.out.println("Account with negative balance found: " + acc)
        );
		
	}
	

	public static List<Flow> generateFlowArray(Accounts[] accountsArray) {
		List<Flow> flowsArray = new ArrayList<>();
		
		flowsArray.add(new Debit( 50.0, 1, LocalDate.now().plusDays(2).toString()));
		
		for (Accounts acc : accountsArray) {
			if (acc.getLabel().contains("save")) {
				flowsArray.add(new Credit( 1050.0, acc.getId(), LocalDate.now().plusDays(2).toString()));
			}	
		}
		
		flowsArray.add(new Transfert( 50.0, 2, 1, LocalDate.now().plusDays(2).toString()));
		
		
		return flowsArray;
	}
	
	//////////////////////////////////////////////////
	
	//////////// Accounts Map function ////////////////

	public static Map<Integer, Accounts> generateMapAccountsFromArray(Accounts[] accountsArray) {
		Map<Integer, Accounts> accountsMap = new HashMap<>();
		for (Accounts acc : accountsArray) {
			accountsMap.put(acc.getId(), acc);
		}
		return accountsMap;
	}
	
	public static Map<Integer, Accounts> generateMapAccountsFromArray(List<Accounts> accountsArray) {
		Map<Integer, Accounts> accountsMap = new HashMap<>();
		for (Accounts acc : accountsArray) {
			accountsMap.put(acc.getId(), acc);
		}
		return accountsMap;
	}
	
	public static void displayAccountsMap(Map<Integer, Accounts> accountsMap) {
		accountsMap.entrySet().stream()
			.sorted(Comparator.comparingDouble(entry -> entry.getValue().getBalance()))
			.forEach(entry -> System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue()));
	}
	
	//////////////////////////////////////////////////

	
	//////////// Accounts Array function ////////////////
	

	public static Accounts[] generateAccountsForClients(Clients[] clientsArray) {
		int accountsNumber = clientsArray.length * 2;
		Accounts[] accountsArray = new Accounts[accountsNumber];
		for (int i = 0; i < accountsNumber; i += 2 ) {
			accountsArray[i] = new CurrentAccount("label_current" + (i/2 + 1), clientsArray[i/2]);
			accountsArray[i+1] = new SavingAccount("label_save" + (i/2 + 1), clientsArray[i/2]);
		}
		
		return accountsArray;
	}
	
	public static void displayAccountsArray(Accounts[] accountsArray) {
		Arrays.stream(accountsArray).forEach(acc -> System.out.println(acc.toString()) );
		
	}
	
	public static void displayAccountsArray(List<Accounts> accountsArray) {
		accountsArray.stream().forEach(acc -> System.out.println(acc.toString()) );
		
	}
	
	//////////////////////////////////////////////////

	
	//////////// Clients Array function ////////////////

	public static void displayClients(List<Clients> clientsArray) {
		clientsArray.stream().forEach(c -> System.out.println(c.toString()));
		
	}
	
	public static void displayClients(Clients[] clientsArray) {
		Arrays.stream(clientsArray).forEach(c -> System.out.println(c.toString()));
		
	}
	
	//if the client is already in the clientArray we return it, otherwise we create it and add it to the clientsArray before return it
	public static Clients getClient(List<Clients> clientsArray, String name, String firstName ) {
		
		for (Clients client : clientsArray) {
			if (client.getName().equals(name) && client.getFirstName().equals(firstName)) {
				return client;
			}
		}
		
		Clients newClient = new Clients(name, firstName);
		System.out.println(newClient);
		clientsArray.add(newClient);
			
		return newClient;
		
	}
	
	public static List<Clients> generateClients(int clientsNumber) {
		List<Clients> clientsArray = new ArrayList<>();
		
		for (int i = 0; i < clientsNumber; i++) {
			clientsArray.add(new Clients("name" + (i + 1), "firstName" + (i + 1)));
		}
		return clientsArray;
	}

	/*public static Clients[] generateClients(int clientsNumber) {
		Clients[] clientsArray = new Clients[clientsNumber];
		
		for (int i = 0; i < clientsNumber; i++) {
			clientsArray[i] = new Clients("name" + (i + 1), "firstName" + (i + 1));
		}
		return clientsArray;
	}*/
	
	
	//////////////////////////////////////////////////
}
