import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	// Product variables
	private String productName, productId;
	private int stockInHand, reorderLevel;
	private double currentPrice;

	// Constructor
	public Product(String productName, String productId, int stockInHand, int reorderLevel, double currentPrice) {
		this.productName = productName;
		this.productId = productId;
		this.stockInHand = stockInHand;
		this.reorderLevel = reorderLevel;
		this.currentPrice = currentPrice;
	}

///// 			SETTER AND GETTER, HASHCODE AND TOSTRING SECTION	

	/*
	 * Getter for productName
	 * 
	 * @return the string productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Setter for productName
	 * 
	 * @param a String for new prodcutName
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/*
	 * Getter for productId
	 * 
	 * @return the string productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Setter for productId
	 * 
	 * @param a String for new prodcutID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/*
	 * Getter for stockInHand
	 * 
	 * @return the string stockInHand
	 */
	public int getStockInHand() {
		return stockInHand;
	}

	/**
	 * Setter for stockInHand
	 * 
	 * @param a String for new stockInHand
	 */
	public void setStockInHand(int stockInHand) {
		this.stockInHand = stockInHand;
	}

	/*
	 * Getter for reorderLevel
	 * 
	 * @return the string reorderLevel
	 */
	public int getReorderLevel() {
		return reorderLevel;
	}

	/**
	 * Setter for reorderLevel
	 * 
	 * @param a String for new reoderLevel
	 */
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	/*
	 * Getter for surrentPrice()
	 * 
	 * @return the string currentPrice()
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Setter for currentPrice
	 * 
	 * @param a String for new currentPrice
	 */
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
