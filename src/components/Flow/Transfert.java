package components.Flow;

import java.time.LocalDate;

//1.3.3

public class Transfert extends Flow {
	
	private int fromAccountId;
	
	public Transfert( double amount, int accountId, int fromAccountId, LocalDate flowDate) {
		super(amount, accountId, flowDate);
		this.fromAccountId = fromAccountId;
	}
	
	public Transfert(String comment, double amount, int accountId, boolean effet, int fromAccountId, LocalDate flowDate) {
		super(comment, amount, accountId, effet, flowDate);
		this.fromAccountId = fromAccountId;
	}
	
	@Override
	public int getFromAccountId() {
		return fromAccountId;
	}

}
