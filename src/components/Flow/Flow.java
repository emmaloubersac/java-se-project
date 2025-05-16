package components.Flow;

//1.3.2

public abstract class Flow {
	

	private String comment;
	private int id;
	private double amount;
	private int accountId;
	private boolean effet;
	private String flowDate;
	
	private static int numberOfFlows;
	
	public Flow() {}
	
	public Flow( double amount, int accountId, String flowDate) {
		this.amount = amount;
		this.accountId = accountId;
		this.effet = false;
		numberOfFlows++;
		this.id = numberOfFlows;
		this.flowDate = flowDate;
	}
	
	public Flow(String comment, double amount, int accountId, boolean effet, String flowDate) {
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
	
	public String getFlowDate() {
		return flowDate;
	}
	
	public void setFlowDate(String flowDate) {
		this.flowDate = flowDate;
	}
	
	@Override
	public String toString() {
		return "{"
		        + "\"comment\":\"" + comment + "\","
		        + "\"id\":" + id + ","
		        + "\"amount\":" + amount + ","
		        + "\"accountId\":" + accountId + ","
		        + "\"effet\":" + effet + ","
		        + "\"flowDate\":\"" + flowDate + "\""
		        + "}";
	}
	
}
