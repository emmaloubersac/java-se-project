package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import components.Clients;
import components.Accounts.Accounts;
import components.Accounts.CurrentAccount;
import components.Accounts.SavingAccount;

//1.1.2
//1.2.3

public class Main {
	public static void main(String args[]) {
		Clients[] clientsArray = generateClients(3);
		
		displayClients(clientsArray);
		
		Accounts[] accountsArray = generateAccountsForClients(clientsArray);
		
		displayAccounts(accountsArray);

	}

	private static void displayAccounts(Accounts[] accountsArray) {
		Arrays.stream(accountsArray).forEach(acc -> System.out.println(acc.toString()) );
		
	}

	private static Accounts[] generateAccountsForClients(Clients[] clientsArray) {
		int accountsNumber = clientsArray.length * 2;
		Accounts[] accountsArray = new Accounts[accountsNumber];
		for (int i = 0; i < accountsNumber; i += 2 ) {
			accountsArray[i] = new CurrentAccount("label_current" + (i/2), clientsArray[i/2]);
			accountsArray[i+1] = new SavingAccount("label_save" + (i/2), clientsArray[i/2]);
		}
		
		return accountsArray;
	}

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
}
