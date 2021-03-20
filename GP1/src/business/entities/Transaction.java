package business.entities;

/**
 * Transaction Class
 * 
 * This class is made to create Transaction objects.
 * Each transaction object will have a series of variables that'll 
 * determine the correct transactions. (ie when a member purchased)
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */

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
	 * Checks whether this transaction is in a given date range.
	 * 
	 * @param startDate The startDate for which transactions are being sought
	 *                  (inclusive)
	 * @param endDate   The endDate for which transactions are being sought
	 *                  (inclusive)
	 * @return true iff the dates match
	 */
	public boolean inDateRange(Calendar startDate, Calendar endDate) {
		return ((startDate.before(this.date) || startDate.equals(this.date))
				&& (endDate.after(this.date) || endDate.equals(this.date)));
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