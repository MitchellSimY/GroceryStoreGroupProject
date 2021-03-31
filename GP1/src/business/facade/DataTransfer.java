package business.facade;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import business.entities.Member;
import business.entities.Order;
import business.entities.Product;
import business.entities.Transaction;

/**
 * The DataTransfer class is used to facilitate data transfer between
 * GroceryStore and Interface. It is also used to support iterating over Member
 * and Product objects. The class stores copies of fields that may be sent in
 * either direction.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 *
 */
public abstract class DataTransfer {

	// Member Variables
	private String memberId, memberName, memberAddress, memberPhone;
	private Calendar dateJoined;
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private double feePaid;
	private int checkOutTransactionIndex;

	// Product variables
	private String productName, productId;
	private int stockInhand, reorderLevel, checkoutQty;
	private double currentPrice;
	private boolean reorderPlaced = false;
	private Product product;

	//Order Variables
	private int orderID, quantityOrdered;
	private Product reorderProduct;
	private Calendar dateOrderPlaced, dateOrderArrival;
	private boolean orderStatus;
	
	/**
	 * This sets all fields to "none".
	 */
	public DataTransfer() {
		reset();
	}

	/**
	 * Sets all the product-related fields using the Product parameter. If the
	 * product is not checked out "none" is stored in the borrower and due date
	 * fields.
	 * 
	 * @param product the product whose fields should be copied.
	 */
	public void setProductFields(Product product) {
		productId = product.getProductId();
		productName = product.getProductName();
		stockInhand = product.getStockInHand();
		reorderLevel = product.getReorderLevel();
		currentPrice = product.getCurrentPrice();
		checkoutQty = product.getCheckoutQty();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	public int getStockInhand() {
		return stockInhand;
	}

	public void setStockInhand(int stockInhand) {
		this.stockInhand = stockInhand;
	}

	public int getCheckoutQty() {
		return checkoutQty;
	}

	public void setCheckoutQty(int checkoutQty) {
		this.checkoutQty = checkoutQty;
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

	public boolean isReorderPlaced() {
		return reorderPlaced;
	}

	public void setReorderPlaced(boolean reorderPlaced) {
		this.reorderPlaced = reorderPlaced;
	}

	/**
	 * Sets all the member-related fields using the Member parameter.
	 * 
	 * @param member the member whose fields should be copied.
	 */
	public void setMemberFields(Member member) {
		memberId = member.getMemberId();
		memberName = member.getName();
		memberPhone = member.getPhoneNumber();
		memberAddress = member.getAddress();
		feePaid = member.getFeePaid();
		dateJoined = member.getJoinedDate();
		transactions = member.getTransactionsList();
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public void setMemberFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	public double getMemberFeePaid() {
		return feePaid;
	}

	public Calendar getDateJoined() {
		return dateJoined;
	}

	public String getDateJoinedByString() {

		int month = dateJoined.get(Calendar.MONTH) + 1;
		return "" + month + "/" + dateJoined.get(Calendar.DAY_OF_MONTH) + "/" + dateJoined.get(Calendar.YEAR);
	}

	public void setDateJoined(Calendar dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public int getCheckOutTransactionIndex() {
		return checkOutTransactionIndex;
	}

	public void setCheckOutTransactionIndex(int checkOutTransactionIndex) {
		this.checkOutTransactionIndex = checkOutTransactionIndex;
	}

	//Order Setters/Getters
	public void setOrderFields(Order order) {
		orderID = order.getOrderID();
		quantityOrdered = order.getQuantityOrdered();
		reorderProduct = order.getReorderProduct();
		dateOrderPlaced = order.getCalendarOrderPlaced();
		dateOrderArrival = order.getCalendarOrderArrival();
		orderStatus = order.isOrderStatus();
	}
	
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public Product getReorderProduct() {
		return reorderProduct;
	}

	public void setReorderProduct(Product reorderProduct) {
		this.reorderProduct = reorderProduct;
	}

	public Calendar getDateOrderPlaced() {
		return dateOrderPlaced;
	}

	public void setDateOrderPlaced(Calendar dateOrderPlaced) {
		this.dateOrderPlaced = dateOrderPlaced;
	}

	public Calendar getDateOrderArrival() {
		return dateOrderArrival;
	}

	public void setDateOrderArrival(Calendar dateOrderArrival) {
		this.dateOrderArrival = dateOrderArrival;
	}

	public boolean isOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		//Reset Member Fields
		memberId = "N/A";
		memberName = "N/A";
		memberPhone = "N/A";
		memberAddress = "N/A";
		dateJoined = null;
		transactions = null;
		feePaid = 0;
		checkOutTransactionIndex = 0;

		//Reset Product Fields
		productName = "N/A";
		productId = "N/A";
		stockInhand = 0;
		reorderLevel = 0;
		currentPrice = 0;
		checkoutQty = 0;
		reorderPlaced = false;
		product = null;

		//Reset Order Fields
		orderID = 0;
		quantityOrdered = 0;
		reorderProduct = null;
		dateOrderPlaced= null;
		dateOrderArrival = null;
		orderStatus = false;
	}
}
