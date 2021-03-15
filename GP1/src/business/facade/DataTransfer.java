package business.facade;

import java.util.Calendar;

import business.entities.Member;
import business.entities.Product;

/**
 * The DataTransfer class is used to facilitate data transfer between
 * GroceryStore and Interface. It is also used to support iterating over Member
 * and Product objects. The class stores copies of fields that may be sent in
 * either direction.
 * 
 * @author Group
 *
 */
public abstract class DataTransfer {

	// Member Variables
	private String memberId;
	private String memberName;
	private String memberAddress;
	private String memberPhone;
	private Calendar dateJoined;
	private double feePaid;

	// Product variables
	private String productName;
	private String productId;
	private int stockInhand, reorderLevel;
	private double currentPrice;

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

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		productName = "none";
		productId = "none";
		stockInhand = 0;
		reorderLevel = 0;
		currentPrice = 0;
		memberId = "none";
		memberName = "none";
		memberPhone = "none";
		memberAddress = "none";
	}
}
