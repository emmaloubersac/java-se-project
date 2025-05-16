package components.Flow;

//1.3.3

public class Transfert extends Flow {
	
	private int fromAccountId;
	
	public Transfert() {}
	
	public Transfert( double amount, int accountId, int fromAccountId, String flowDate) {
		super(amount, accountId, flowDate);
		this.fromAccountId = fromAccountId;
	}
	
	public Transfert(String comment, double amount, int accountId, boolean effet, int fromAccountId, String flowDate) {
		super(comment, amount, accountId, effet, flowDate);
		this.fromAccountId = fromAccountId;
	}
	
	@Override
	public int getFromAccountId() {
		return fromAccountId;
	}
	
	public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
	
	@Override
	public String toString() {
		return super.toString().replace("}", ",\"fromAccountId\":" + fromAccountId + "}").replace("{", "{\"type\":\"transfert\",");
	}

}
