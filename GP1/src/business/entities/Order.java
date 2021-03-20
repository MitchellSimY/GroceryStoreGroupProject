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
	private int orderID;
	private Product reorderProduct;
	private int quantityOrdered;
	private Calendar date;
	private static int counter = 0;
	
	public Order(Product productToOrder, Calendar date) {
		orderID = counter++;
		quantityOrdered = productToOrder.getReorderLevel()*2;
		reorderProduct = productToOrder;
		this.date = date;
	}

	public int getOrderID() {
		return orderID;
	}

	public Product getReorderProduct() {
		return reorderProduct;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}
	
	public String getDate() {
		return date.get(Calendar.MONTH) + "/" + date.get(Calendar.DATE) + "/" + date.get(Calendar.YEAR);
	}

	@Override
	public String toString() {
		return "OrderID: " + orderID + "\t" + reorderProduct.getProductName() + "\tQty Ordered: "
				+ getDate() + "\t" + quantityOrdered;
	}
	
	
}
