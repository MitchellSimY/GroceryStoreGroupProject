package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Order Class is utilized for creating orders. It takes a Product object and orders double the amount of the product's reorderValue. 
 * The orderStatus is always set to false when created until it is processed.
 * The expected date of arrival for the order is always set to 2 weeks ahead of the date the order was placed.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderID, quantityOrdered;
	private Product reorderProduct;
	private Calendar dateOrderPlaced, dateOrderArrival;
	private boolean orderStatus;
	private static int orderCounter = 0;

	/**
	 * Creates an Order object that reflects reordering a product to increase its stock.
	 * @param productToOrder: of type Product. The product that is being reordered.
	 * @param date: of type Calendar. The date the order is placed.
	 */
	public Order(Product productToOrder, Calendar date) {
		orderCounter++;
		orderID = orderCounter;
		quantityOrdered = productToOrder.getReorderLevel() * 2;
		reorderProduct = productToOrder;
		dateOrderPlaced = new GregorianCalendar();
		dateOrderArrival = date;
		dateOrderArrival.add(Calendar.WEEK_OF_YEAR, 2);
		orderStatus = false;
	}
	
	/**
	 * Creates a temporary Order object used to compare with the orders in a list
	 * @param orderID: of type Int. Determines which orderID to look for.
	 */
	public Order(int orderID) {
		this.orderID = orderID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Product getReorderProduct() {
		return reorderProduct;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public Calendar getCalendarOrderPlaced() {
		return dateOrderPlaced;
	}

	public Calendar getCalendarOrderArrival() {
		return dateOrderArrival;
	}

	/**
	 * Obtains a formatted string of the date the Order got placed.
	 * @return String: returns the date in the format MM/DD/YYYY
	 */
	public String getDateOrderPlaced() {
		return dateOrderPlaced.get(Calendar.MONTH) + 1 + "/" + dateOrderPlaced.get(Calendar.DATE) + "/"
				+ dateOrderPlaced.get(Calendar.YEAR);
	}

	/**
	 * Obtains a formatted string of the date the Order is expected to arrive.
	 * @return String: returns the date in the format MM/DD/YYYY
	 */
	public String getDateOrderArrival() {
		return dateOrderArrival.get(Calendar.MONTH) + 1 + "/" + dateOrderArrival.get(Calendar.DATE) + "/"
				+ dateOrderArrival.get(Calendar.YEAR);
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
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
		Order other = (Order) object;
		if (orderID == 0) {
			if (other.orderID != 0) {
				return false;
			}
		} else if (orderID != other.orderID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderID: " + orderID + "\tProduct Name: " + reorderProduct.getProductName() + "\tDate Ordered: "
				+ getDateOrderPlaced() + "\tQty Ordered: " + "\t" + quantityOrdered;
	}

	//Used to serialize the static field orderCounter.
	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(orderCounter);
	}

	//Used to retrieve serialized static field orderCounter.
	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		orderCounter = (int) input.readObject();
	}
	
}
