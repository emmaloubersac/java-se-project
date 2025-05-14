package main;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

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
		Clients[] clientsArray = generateClients(3);
		
		displayClients(clientsArray);
		System.out.println();
		
		//1.2.3
		Accounts[] accountsArray = generateAccountsForClients(clientsArray);
		
		displayAccountsArray(accountsArray);
		System.out.println();
		
		//1.3.1
		Map<Integer, Accounts> accountsMap = generateMapAccountsFromArray(accountsArray);
		
		displayAccountsMap(accountsMap);
		System.out.println();
		
		//1.3.4
		 List<Flow> flowsArray = generateFlowArray(accountsArray);
		 
		 
		//1.3.5
		performFlowsToAccounts(flowsArray, accountsMap);
		System.out.println();
		
		displayAccountsMap(accountsMap);

	}
	
	////////////Flow function ////////////////
	
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
		
		flowsArray.add(new Debit( 50.0, 1, LocalDate.now().plusDays(2)));
		
		for (Accounts acc : accountsArray) {
			if (acc.getLabel().contains("save")) {
				flowsArray.add(new Credit( 1050.0, acc.getId(), LocalDate.now().plusDays(2)));
			}	
		}
		
		flowsArray.add(new Transfert( 50.0, 2, 1, LocalDate.now().plusDays(2)));
		
		
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
	
	//////////////////////////////////////////////////

	
	//////////// Clients Array function ////////////////

	public static void displayClients(Clients[] clientsArray) {
		Arrays.stream(clientsArray).forEach(c -> System.out.println(c.toString()));
		
	}

	public static Clients[] generateClients(int clientsNumber) {
		Clients[] clientsArray = new Clients[clientsNumber];
		
		for (int i = 0; i < clientsNumber; i++) {
			clientsArray[i] = new Clients("name" + (i + 1), "firstName" + (i + 1));
		}
		return clientsArray;
	}
	
	//////////////////////////////////////////////////
}
