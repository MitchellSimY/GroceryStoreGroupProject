package business.facade;

import java.util.Calendar;

/**
 * This class is used for requesting many of the results of the GroceryStore
 * system's business logic to user interface. It is a singleton
 * 
 * At present, the Request object returns an int code,plus values of selected
 * fields of Product and Member. They are the product name, id, member id,
 * member name, member phone, and member id.
 * 
 * @author Group
 *
 */
public class Request extends DataTransfer {
	private static Request request;
	private Calendar date;

	// variables used for printTransaction
	private Calendar startDate;
	private Calendar endDate;

	/**
	 * This is a singleton class. Hence the private constructor.
	 */
	private Request() {

	}

	/**
	 * Returns the only instance of the class.
	 * 
	 * @return the only instance
	 */
	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
}
