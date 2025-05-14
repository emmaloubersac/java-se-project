package components.Accounts;

import components.Clients;

//1.2.1

public abstract class Accounts {
	protected String label;
	protected double balance;
	protected int id;
	protected Clients client;
	
	private static int numberOfAccounts;
	
	public Accounts(String label, Clients client) {
		this.label = label;
		this.client = client;
		numberOfAccounts++;
		this.id = numberOfAccounts;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clients getClient() {
		return client;
	}

	public void setClient(Clients client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account label : " + label + ", balance : " + balance + ", id : " + id + ", client : " + client;
	}
	
	
}
