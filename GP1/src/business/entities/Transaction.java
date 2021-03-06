package business.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import business.collections.CheckOutProductList;

/**
 * Transaction Class
 * 
 * This class is made to create Transaction objects.
 * Each Transaction object holds a list of Products, the totalcost of the products in the list and the date the Transaction was created.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */


public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private CheckOutProductList checkOutProductList = new CheckOutProductList();
	private double totalCost;
	private Calendar date;

	public Transaction() {
		totalCost = 0;
		date = new GregorianCalendar();
	}

	/**
	 * Checks whether this transaction is in a given date range.
	 * 
	 * @param startDate: of type Calendar. The startDate for which transactions are being sought (inclusive)
	 * @param endDate: of type Calendar. The endDate for which transactions are being sought (inclusive)
	 * @return true: iff the date the Transaction object has is within the two param's date.
	 */
	public boolean inDateRange(Calendar startDate, Calendar endDate) {

		return (startDate.before(this.date) || (startDate.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)
				&& startDate.get(Calendar.DAY_OF_YEAR) == this.date.get(Calendar.DAY_OF_YEAR)))
				&& (endDate.after(this.date) || (endDate.get(Calendar.YEAR) == this.date.get(Calendar.YEAR)
						&& endDate.get(Calendar.DAY_OF_YEAR) == this.date.get(Calendar.DAY_OF_YEAR)));

	}
	
	/**
	 * This method is utilized for calculating the total cost of all the products in the Transaction object's product list.
	 * It sets the totalCost field for the Transaction object.
	 */
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

	/**
	 * Returns the date as a Calendar object.
	 * 
	 * @return date: of type Calendar. The date of the Transaction.
	 */
	public Calendar getCalenderDate() {
		return date;
	}
	
	/**
	 * Returns the date as a String formatted MM/DD/YYYY.
	 * 
	 * @return date: of type String. The date of the Transaction with month, date, and year.
	 */
	public String getDate() {
		return date.get(Calendar.MONTH) + 1 + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	public void setDate(Calendar date) {
		this.date = date;
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


	/**
	 * Return all string have same length and set the string in the middle
	 * 
	 * @param string: of type String. The string to be formatted.
	 * @return string: of type String. A string with space in front and back to make string set in the middle
	 */
	private String equalsLength(String string) {
		int standar = 25 - string.length();
		if (standar > 0 && standar % 2 == 0) {
			for (int i = 0; i <= (standar / 2) - 1; ++i) {
				string = " " + string + " ";
			}
		}
		if (standar > 0 && standar % 2 != 0) {
			for (int i = 0; i <= standar / 2; ++i) {
				if (i == standar / 2) {
					string = string + " ";
				} else
					string = " " + string + " ";
			}
		}
		return string;
	}
	
	/**
	 * Return the current product being checked out as a string with its name, price, checkOutQty, and total cost
	 * 
	 * @param product: of type Product. The Product that is currently being checked out.
	 * @return String: of type String. A string with the Product's name, price, checkOutQty, and total cost.
	 */
	public String currentProductCheckoutToString(Product product) {
		DecimalFormat df = new DecimalFormat("$###,##0.00");
		double productPurchasedCost = product.getCurrentPrice() * product.getCheckoutQty();
		return equalsLength(product.getProductName())  + "|" + equalsLength("" + product.getCheckoutQty())
		+ "|" + equalsLength(df.format(product.getCurrentPrice())) + "|" + equalsLength(df.format(productPurchasedCost)) + "\n";
	}	
		
	/**
	 * String format of the transaction. Prints out all the products purchased in the product list of the Transaction object.
	 * @return String: The transaction's product list. The product's name, cost per, quantity purchased, and total cost.
	 */
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("$###,##0.00");
		String productListString = "";
		computeTotalCost();
		for (Iterator<Product> iterator = checkOutProductList.iterator(); iterator.hasNext();) {
			Product productCursor = (Product) iterator.next();
			productListString += currentProductCheckoutToString(productCursor);
		}
		return "Checkout Transaction date: " + getDate() + "\n" + equalsLength("Product Name") + "|" + equalsLength("Qty Purchased") + "|"
				+ equalsLength("Cost Per") + "|" + equalsLength("Product Cost") + "\n" + productListString
				+ "\nTotal Cost: " + df.format(totalCost);
	}
}