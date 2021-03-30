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
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import business.collections.CheckOutProductList;

public class Transaction implements Serializable {
	// Variables for Transaction
	private static final long serialVersionUID = 1L;
	private CheckOutProductList checkOutProductList = new CheckOutProductList();
	private double totalCost;
	private Calendar date;

	public Transaction() {
		totalCost = 0;
		date = new GregorianCalendar(); // TODO : ADD ONE MONTH TO CALENDER.MONTH INDEX GOES FROM 0-11
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

	public void computeTotalCost() {
		double totalCostCalc = 0;
		for (Iterator<Product> iterator = checkOutProductList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			totalCostCalc += product.getCurrentPrice() * product.getCheckoutQty();
		}
		setTotalCost(totalCostCalc);
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public CheckOutProductList getCheckOutProductList() {
		return checkOutProductList;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getCalenderDate() {
		return date;
	}

	/**
	 * Returns the date as a String
	 * 
	 * @return date with month, date, and year
	 */
	public String getDate() {
		return date.get(Calendar.MONTH) + 1 + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Transaction other = (Transaction) object;
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (date.get(Calendar.MONTH) == other.date.get(Calendar.MONTH)
				&& date.get(Calendar.DATE) == other.date.get(Calendar.DATE)
				&& date.get(Calendar.YEAR) == other.date.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}

	// TODO: fix this
	public String currentProductCheckoutToString(Product product) {
		DecimalFormat df = new DecimalFormat("$###,##0.00");
		double productPurchasedCost = product.getCurrentPrice() * product.getCheckoutQty();
		return product.getProductName() + "\t" + product.getCheckoutQty() + "\t" + df.format(product.getCurrentPrice())
				+ "\t" + df.format(productPurchasedCost) + "\n";
	}

	/**
	 * String form of the transaction
	 * 
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("$###,##0.00");
		String productListString = "";
		computeTotalCost();
		for (Iterator<Product> iterator = checkOutProductList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			double productPurchasedCost = product.getCurrentPrice() * product.getCheckoutQty();
			productListString += product.getProductName() + "\t\t" + product.getCheckoutQty() + "\t"
					+ df.format(product.getCurrentPrice()) + "\t" + df.format(productPurchasedCost) + "\n";
		}
		return "Checkout Transaction date: " + getDate() + "\nProduct\t\\tQty\tPerCost\tTotal\n" + productListString
				+ "\nTotal Cost: " + df.format(totalCost);
	}
}