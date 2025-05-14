package components.Flow;

import java.time.LocalDate;

//1.3.2

public abstract class Flow {
	

	private String comment;
	private int id;
	private double amount;
	private int accountId;
	private boolean effet;
	private LocalDate flowDate;
	
	private static int numberOfFlows;
	
	public Flow( double amount, int accountId, LocalDate flowDate) {
		this.amount = amount;
		this.accountId = accountId;
		this.effet = false;
		numberOfFlows++;
		this.id = numberOfFlows;
		this.flowDate = flowDate;
	}
	
	public Flow(String comment, double amount, int accountId, boolean effet, LocalDate flowDate) {
		this.comment = comment;
		this.amount = amount;
		this.accountId = accountId;
		this.effet = effet;
		numberOfFlows++;
		this.id = numberOfFlows;
		this.flowDate = flowDate;
	}
	
	//1.3.5
	//default function, to be override in Transfert Class
	public int getFromAccountId() {
		return -1;
	}
	
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public boolean isEffet() {
		return effet;
	}
	
	public void setEffet(boolean effet) {
		this.effet = effet;
	}
	
	public LocalDate getFlowDate() {
		return flowDate;
	}
	
	public void setFlowDate(LocalDate flowDate) {
		this.flowDate = flowDate;
	}
	
}
