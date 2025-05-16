package components.Flow;

//1.3.3

public class Credit extends Flow {
	
	public Credit() {}
	
	public Credit( double amount, int accountId, String flowDate) {
		super( amount, accountId, flowDate);
	}

	public Credit(String comment, double amount, int accountId, boolean effet, String flowDate) {
		super(comment, amount, accountId, effet, flowDate);
	}
	
	@Override
	public String toString() {
		return super.toString().replace("{", "{\"type\":\"credit\",");
	}

}
