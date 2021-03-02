
public class Interface {
	
	public static final int EXIT = 0;
	public static final int ENROLL_MEMBER = 1;
	public static final int REMOVE_MEMBER = 2;
	public static final int ADD_PRODUCT = 3;
	public static final int CHECKOUT_MEMBER_ITEMS= 4;
	public static final int PROCESS_SHIPMENT = 5;
	public static final int CHANGE_PRICE = 6;
	public static final int RETRIEVE_PRODUCT_INFO = 7;
	public static final int RETRIEVE_MEMBER_INFO = 8;
	public static final int PRINT_TRANSACTIONS = 9;
	
	/**
	 * Displaying help screen (User Interface)
	 */
	public void help() {
		System.out.println("Please select an option");
		System.out.println(EXIT + " to exit the interface");
		System.out.println(ENROLL_MEMBER + " to enroll a member");
		System.out.println(REMOVE_MEMBER + " to remove a member");
		System.out.println(ADD_PRODUCT + " to add a new product");
		System.out.println(CHECKOUT_MEMBER_ITEMS+ " to checkout a member's items");
		System.out.println(PROCESS_SHIPMENT + " to process a shipment");
		System.out.println(CHANGE_PRICE+ " to change the price of an item");
		System.out.println(RETRIEVE_PRODUCT_INFO + " to retrieve product information");
		System.out.println(RETRIEVE_MEMBER_INFO + " to retrieve member information");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
	}
	
	
	
}
