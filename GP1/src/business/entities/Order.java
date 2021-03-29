package business.entities;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Order Class
 * This class is utilized for creating orders. 
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 *
 */


public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private int orderID, quantityOrdered;
	private Product reorderProduct;
	private Calendar dateOrderPlaced, dateOrderArrival;
	private boolean orderStatus;
	private static int counter = 0;
	
	public Order(Product productToOrder, Calendar date) {
		counter += 1;
		orderID = counter;
		quantityOrdered = productToOrder.getReorderLevel()*2;
		reorderProduct = productToOrder;
		dateOrderPlaced = date;
		dateOrderArrival = date;
		dateOrderArrival.add(Calendar.WEEK_OF_YEAR, 2);	//Shipment will always arrive 2 weeks after initial order date.
		orderStatus = false;
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
	
	public String getDateOrderPlaced() {
		return dateOrderPlaced.get(Calendar.MONTH) + "/" + dateOrderPlaced.get(Calendar.DATE) + "/" + dateOrderPlaced.get(Calendar.YEAR);
	}

	public String getDateOrderArrival() {
		return dateOrderArrival.get(Calendar.MONTH) + "/" + dateOrderArrival.get(Calendar.DATE) + "/" + dateOrderArrival.get(Calendar.YEAR);
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
		return "OrderID: " + orderID + "\tProduct Name: " + reorderProduct.getProductName() + "\tDate Ordered: " + getDateOrderPlaced()
		+ "\tQty Ordered: " + "\t" + quantityOrdered;
	}
	
	
}
