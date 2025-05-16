package components.Accounts;

import components.Clients;
import components.Flow.Credit;
import components.Flow.Debit;
import components.Flow.Flow;

//1.2.1

public abstract class Accounts {
	protected String label;
	protected double balance;
	protected int id;
	protected Clients client;
	
	private static int numberOfAccounts;
	
	public Accounts() {}
	
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
	
	//1.3.5
	
	public void setBalance(double amount) {
		this.balance = amount;
	}
	
	public void setBalance(Flow flow) {
		if (flow instanceof Credit) {
			this.balance += flow.getAmount();
		} 
		else if (flow instanceof Debit) {
			this.balance -= flow.getAmount();
		} 
		else if (flow.getAccountId() == this.id) {
			this.balance += flow.getAmount();
		} 
		else if (flow.getFromAccountId() == this.id) {
			this.balance -= flow.getAmount();
		}
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
