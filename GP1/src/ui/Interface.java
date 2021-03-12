package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import business.facade.GroceryStore;
import business.facade.Request;
import business.facade.Result;

public class Interface {

	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private static Interface interfaceVariable;
	private static GroceryStore groceryStore;

	// Making static variables for user selection later.
	public static final int EXIT = 0;
	public static final int ENROLL_MEMBER = 1;
	public static final int REMOVE_MEMBER = 2;
	public static final int ADD_PRODUCT = 3;
	public static final int CHECKOUT_MEMBER_ITEMS = 4;
	public static final int PROCESS_SHIPMENT = 5;
	public static final int CHANGE_PRICE = 6;
	public static final int RETRIEVE_PRODUCT_INFO = 7;
	public static final int RETRIEVE_MEMBER_INFO = 8;
	public static final int PRINT_TRANSACTIONS = 9;
	public static final int LIST_OUTSTANDING_ORDERS = 10;
	public static final int LIST_ALL_MEMBERS = 11;
	public static final int LIST_ALL_PRODUCTS = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton GroceryStore object.
	 */
	private Interface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			groceryStore = GroceryStore.instance();
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Interface instance() {
		if (interfaceVariable == null) {
			return interfaceVariable = new Interface();
		} else {
			return interfaceVariable;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Gets a name after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				Calendar date = new GregorianCalendar();
				String item = getToken(prompt);
				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
				System.out.println("Please input a date as mm/dd/yy");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command: "));
				if (value >= EXIT && value <= HELP) {
					System.out.println(value);
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}


	
	/**
	 * Help method. 
	 * Method strictly for advising the user which menu selection 
	 * they can select from.
	 * 
	 * @return None. Will prompt for user input.
	 */
	public void help() {
		System.out.println("Please select an option");
		System.out.println(EXIT + " to exit the interface");
		System.out.println(ENROLL_MEMBER + " to enroll a member");
		System.out.println(REMOVE_MEMBER + " to remove a member");
		System.out.println(ADD_PRODUCT + " to add a new product");
		System.out.println(CHECKOUT_MEMBER_ITEMS + " to checkout a member's items");
		System.out.println(PROCESS_SHIPMENT + " to process a shipment");
		System.out.println(CHANGE_PRICE + " to change the price of an item");
		System.out.println(RETRIEVE_PRODUCT_INFO + " to retrieve product information");
		System.out.println(RETRIEVE_MEMBER_INFO + " to retrieve member information");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions");
		System.out.println(LIST_OUTSTANDING_ORDERS + " to print all outstanding (not yet fulfilled) orders");
		System.out.println(LIST_ALL_MEMBERS + " to print information of all members");
		System.out.println(LIST_ALL_PRODUCTS + " to print information of all products");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Completed Add member method. Utilized for adding a new member.
	 * 
	 * @return none. Creates a member
	 */
	public void addMember() {
		Request.instance().setMemberName(getName("Please enter the Member's name: "));
		Request.instance().setMemberAddress(getName("Please enter the Member's address: "));
		Request.instance().setMemberPhone(getName("Please enter the Member's phone number: "));
		Request.instance().setMemberFeePaid(getNumber("Please enter how much the Member paid: "));
		Request.instance().setDate(getDate("Please enter the date joined as mm/dd/yy"));
		Result result = groceryStore.addMember(Request.instance());

		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getMemberName() + "'s ID is " + result.getMemberId());
		}
	}

	//	TODO: Mitch
	public void removeMember() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add product method. Utilized for adding a new product
	 * 
	 * @return none. Creates product object.
	 */
	public void addProduct() {
		do {
			Request.instance().setProductName(getName("Enter Product name: "));
			Request.instance().setProductId(getToken("Enter Product ID: "));
			Request.instance().setCurrentPrice(getNumber("Please enter current price: "));
			Request.instance().setStockInhand(getNumber("Please enter current stock in hand: "));
			Request.instance().setReorderLevel(getNumber("Please enter Re-Order level: "));
			Result result = groceryStore.addProduct(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("Product could not be added.");
			} else {
				System.out.println(result.getProductName() + " Has since been added.");
			}
		} while (yesOrNo("Add more products?"));
	}

	/**
	 * Method to be called for checkingOut. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for checking out.
	 * 
	 */
	public void checkOutProducts() {
		Request.instance().setMemberId(getToken("Enter member id"));
		Result result = groceryStore.searchMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No member with id " + Request.instance().getMemberId());
			return;
		}
		do {
			Request.instance().setProductId(getToken("Enter product id"));
			// test. Is there a safer way to store quantity variable?
			int quantity;
			quantity = (getNumber("Enter the number of units being sold: "));
			result = groceryStore.checkOut(Request.instance(), quantity);
			if (result.getResultCode() == Result.OPERATION_COMPLETED) {
				// Edit output for unit price, total price, etc.
				System.out.println("Product: " + result.getProductName() + "\nNumber of item: " + quantity
						+ "\nUnit Price: " + result.getCurrentPrice() + "\nPrice for Item: "
						+ (result.getCurrentPrice() * quantity));
			} else if (result.getResultCode() == Result.PRODUCT_NOT_FOUND) {
				System.out.println("Product not found.");
			} else if (result.getResultCode() == Result.INSUFFICIENT_STOCK) {
				System.out.println("Not enough stock.");
			} else {
				System.out.println("Could not sell product.");
			}
			// TODO: Check reorder level. Reorder if necessary and display.
		} while (yesOrNo("Check out more products?"));
	}

//	TODO: Kou
	public void processShipment() {
		// TODO Auto-generated method stub

	}

//	TODO: up for grabs
	public void changePrice() {
		// TODO Auto-generated method stub

	}

//	TODO: up for grabs
	public void retrieveProductInfo() {
		// TODO Auto-generated method stub

	}

//	TODO: Trung
	public void retrieveMemberInfo() {
		// TODO Auto-generated method stub

	}

//	TODO: Jack
	public void printTransactions() {
		// TODO Auto-generated method stub

	}

//	TODO: Up for grabs
	private void listOutstandingOrders() {
		// TODO Auto-generated method stub

	}

	/**
	 * List all members will list all the members that have registered.
	 * 
	 * @return All members
	 */
	public void listAllMembers() {
		Iterator<Result> iterator = groceryStore.getMembers();
		System.out.println("Listing all members by Name, Date joined, address and phone number");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getMemberName() + " | " + result.getDateJoined() + " | "
					+ result.getMemberAddress() + " | " + result.getMemberPhone());
		}

	}

//	TODO: Up for grabs
	public void listAllProducts() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method to be called for saving the GroceryStore object. Uses the appropriate
	 * GroceryStore method for saving.
	 * 
	 */
	private void save() {
		if (groceryStore.save()) {
			System.out.println(" The GroceryStore has been successfully saved in the file GroceryStoreData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate
	 * GroceryStore method for retrieval.
	 * 
	 */

	private void retrieve() {
		try {
			if (groceryStore == null) {
				groceryStore = GroceryStore.retrieve();
				if (groceryStore != null) {
					System.out.println(
							" The groceryStore has been successfully retrieved from the file GroceryStoreData \n");
				} else {
					System.out.println("File doesnt exist; creating new groceryStore");
					groceryStore = GroceryStore.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Process method. A bunch of switch cases to find out what the user/clerk wants
	 * to perform.
	 */
	public void process() {
		int command;
		help();
		// Making the command variable equal what the user inputs
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ENROLL_MEMBER:
				addMember();
				break;
//				TODO: Mitch
			case REMOVE_MEMBER:
				removeMember();
				break;
			case ADD_PRODUCT:
				addProduct();
				break;
			case CHECKOUT_MEMBER_ITEMS:
				checkOutProducts();
				break;
//				TODO: Kou
			case PROCESS_SHIPMENT:
				processShipment();
				break;

//				TODO: Up for grabs
			case CHANGE_PRICE:
				changePrice();
				break;

//				TODO: Up for grabs
			case RETRIEVE_PRODUCT_INFO:
				retrieveProductInfo();
				break;

//				TODO: Trung
			case RETRIEVE_MEMBER_INFO:
				retrieveMemberInfo();
				break;

//				TODO:Jack 
			case PRINT_TRANSACTIONS:
				printTransactions();
				break;
//				TODO: Up for grabs
			case LIST_OUTSTANDING_ORDERS:
				listOutstandingOrders();
				break;
			case LIST_ALL_MEMBERS:
				listAllMembers();
				break;
//				TODO: Up for grabs
			case LIST_ALL_PRODUCTS:
				listAllProducts();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;

			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		Interface.instance().process();
	}
}
