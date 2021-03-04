
public class Product {
	
	// Product variables
	private String productName;
	private int productId, stockInHand, reorderLevel;
	private double currentPrice;
	
	// Constructor 
	public Product(String productName, int productId, int stockInHand, int reorderLevel, double currentPrice) {
		this.productName = productName;
		this.productId = productId;
		this.stockInHand = stockInHand;
		this.reorderLevel = reorderLevel;
		this.currentPrice = currentPrice;
	}
	
	
	
	
}
