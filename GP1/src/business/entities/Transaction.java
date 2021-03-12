package business.entities;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
	// Variables for Transaction
	private static final long serialVersionUID = 1L;
	private String type;
	private String productName;
	private double totalCost;
	private Calendar date;

	public Transaction(String type, String productName) {
		this.type = type;
		this.productName = productName;
		date = new GregorianCalendar();
	}

	/**
	 * Checks whether this transaction is on the given date
	 * 
	 * @param date The date for which transactions are being sought
	 * @return true iff the dates match
	 */
	public boolean onDate(Calendar date) {
		return ((date.get(Calendar.YEAR) == this.date.get(Calendar.YEAR))
				&& (date.get(Calendar.MONTH) == this.date.get(Calendar.MONTH))
				&& (date.get(Calendar.DATE) == this.date.get(Calendar.DATE)));
	}

	/**
	 * Returns the type field
	 * 
	 * @return type field
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the title field
	 * 
	 * @return title field
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Returns the date as a String
	 * 
	 * @return date with month, date, and year
	 */
	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	/**
	 * String form of the transaction
	 * 
	 */
	@Override
	public String toString() {
		return (type + "   " + productName);
	}
}