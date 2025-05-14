package components.Flow;

import java.time.LocalDate;

//1.3.3

public class Credit extends Flow {
	
	public Credit( double amount, int accountId, LocalDate flowDate) {
		super( amount, accountId, flowDate);
	}

	public Credit(String comment, double amount, int accountId, boolean effet, LocalDate flowDate) {
		super(comment, amount, accountId, effet, flowDate);
	}

}
