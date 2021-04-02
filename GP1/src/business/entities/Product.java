package business.entities;
import java.io.Serializable;

/**
 * Product Class.
 * This class is utilized to create product objects.
 * Each product object will have a series of variables such as the name
 * ID, current stock, price, and it's re-order value.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	// Product variables
	private String productName, productId;
	private int stockInHand, reorderLevel, checkoutQty;
	private double currentPrice;

	// Constructor
	public Product(String productName, String productId, int stockInHand, int reorderLevel, double currentPrice) {
		this.productName = productName;
		this.productId = productId;
		this.stockInHand = stockInHand;
		this.reorderLevel = reorderLevel;
		this.currentPrice = currentPrice;
	}

	/**
	 * Updates current stock based on how much the member is buying.
	 * 
	 * @param member the borrower
	 * @return true iff the book could be issued. True currently
	 */
	public boolean checkOut(int quantity) {
		if ((getStockInHand() - quantity) < 0) {
			return false;
		} else {
			checkoutQty = quantity;
			setStockInHand(stockInHand - quantity);
			return true;
		}
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public int getCheckoutQty() {
		return checkoutQty;
	}

	public void setCheckoutQty(int checkoutQty) {
		this.checkoutQty = checkoutQty;
	}

	public int getStockInHand() {
		return stockInHand;
	}

	public void setStockInHand(int stockInHand) {
		this.stockInHand = stockInHand;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Product Name " + productName + " Product ID " + productId + " Stock in Hand " + stockInHand
				+ " Reoder Level " + reorderLevel + " Current Price" + currentPrice;

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
		Product other = (Product) object;
		if (productId == null) {
			if (other.productId != null) {
				return false;
			}
		} else if (!productId.equals(other.productId)) {
			return false;
		}
		return true;
	}

}
