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

import business.entities.Transaction;
import business.facade.GroceryStore;
import business.facade.Request;
import business.facade.Result;

/**
 * 
 * This class implements the user interface for the Library project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class Interface {
	private static Interface interfaceVariable;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static GroceryStore groceryStore;

	// Making static variables for user selection later.
	private static final int EXIT = 0;
	private static final int ENROLL_MEMBER = 1;
	private static final int REMOVE_MEMBER = 2;
	private static final int ADD_PRODUCT = 3;
	private static final int CHECKOUT_MEMBER_ITEMS = 4;
	private static final int PROCESS_SHIPMENT = 5;
	private static final int CHANGE_PRICE = 6;
	private static final int RETRIEVE_PRODUCT_INFO = 7;
	private static final int RETRIEVE_MEMBER_INFO = 8;
	private static final int PRINT_TRANSACTIONS = 9;
	private static final int LIST_OUTSTANDING_ORDERS = 10;
	private static final int LIST_ALL_MEMBERS = 11;
	private static final int LIST_ALL_PRODUCTS = 12;
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

	private String equalsLength(String string) {
		int standar = 20 - string.length();
		if (standar > 0) {
			for (int i = 0; i < (standar / 2); ++i) {
				string = " " + string + " ";
			}
		}
		return string;
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

	public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (Exception exception) {
				System.out.println("Invalid input. Please enter a double.");
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
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Help method. Method strictly for advising the user which menu selection they
	 * can select from.
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
		Request.instance().setMemberFeePaid(getDouble("Please enter how much the Member paid: "));
		Request.instance().setDate(getDate("Please enter the date joined as mm/dd/yy"));
		Result result = groceryStore.addMember(Request.instance());

		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getMemberName() + "'s ID is " + result.getMemberId());
		}
	}

	// TODO: Mitch
	public void removeMember() {
		Request.instance().setMemberId(getToken("Enter the ID of the member you'd like to remove: "));
		Result result = groceryStore.removeMember(Request.instance());

		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not remove member");
		} else {
			System.out.println("Member has since been removed");
		}
		
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
			Request.instance().setCurrentPrice(getDouble("Please enter current price: "));
			Request.instance().setStockInhand(getNumber("Please enter current stock in hand: "));
			Request.instance().setReorderLevel(getNumber("Please enter Re-Order level: "));
			Result result = groceryStore.addProduct(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("Product could not be added.");
			} else {
				System.out.println(result.getProductName() + " has since been added.");
			}
		} while (yesOrNo("Add more products?"));
	}

	/**
	 * Method to be called for checkingOut. Prompts the user for the appropriate
	 * values and uses the appropriate GroceryStore method for checking out.
	 * 
	 */
	public void checkOutMember() {
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

//	TODO: Trung
	/**
	 * The method take the user input which is a id of a product then searching in
	 * the product list of GroceryStore object to find out if there a products with
	 * user Id string input exist, then update the price for the product by asking
	 * user Then print out all the information of the product was update in price
	 * term
	 */
	public void changePrice() {
		do {
			Request.instance().setProductId(getToken("Please enter id of product you want to change the price"));
			Result result = groceryStore.searchProduct(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("No Product found with given name " + Request.instance().getProductName());
			} else {
				double newPrice = getDouble(
						"Please enter the new price for the product has id " + Request.instance().getProductId());
				result.setCurrentPrice(newPrice);
				groceryStore.changePrice(Request.instance().getProductId(), newPrice);
				System.out.println(
						"Prodcut name " + Request.instance().getProductName() + " has ID  is " + result.getProductId()
								+ " | stock in hand is " + result.getStockInhand() + " | product price is "
								+ result.getCurrentPrice() + "| reorder level is " + result.getReorderLevel());
			}
		} while (yesOrNo("Do you want to change the price for different product? "));
	}

//	TODO: Trung
	/**
	 * The method take the input string which is a name of a product then searching
	 * in the product list of GroceryStore object to find out all the products have
	 * matching name and return in safe SafeIterator of matching product then print
	 * out all information of each member like productID current price,stock in hand
	 * and reorder level
	 */
	public void retrieveProductInfo() {

		do {
			Request.instance()
					.setProductName(getName("Please enter name of product you want to retrieved the information"));
			Result result = groceryStore.searchProductName(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("No Product found with given name " + Request.instance().getProductName());
				break;
			}

			Iterator<Result> iterator = groceryStore.retrievedProductInfor(Request.instance().getProductName());
			System.out.println("Listing all ProductID, stock in hand, Current Price and Reoder Level has name is "
					+ Request.instance().getProductName());
			while (iterator.hasNext()) {
				result = iterator.next();
				System.out.println(
						"Prodcut name " + Request.instance().getProductName() + " has ID  is " + result.getProductId()
								+ " | stock in hand is " + result.getStockInhand() + " | product price is "
								+ result.getCurrentPrice() + "| reorder level is " + result.getReorderLevel());
				result.reset();
			}

		} while (yesOrNo("Find information of other product ?"));
	}

	// TODO: Trung
	/**
	 * The method take the input string which is a name of a member then searching
	 * in the member list of GroceryStore object to find out all the members have
	 * matching name and return in safe SafeIterator of matching member then print
	 * out all information of each member like memberID , address,phone number and
	 * paid fee
	 */
	public void retrieveMemberInfo() {
		do {
			Request.instance()
					.setMemberName(getName("Please enter name of member you want to retrieved the information"));
			Result result = groceryStore.searchName(Request.instance());
			if (result.getResultCode() != Result.OPERATION_COMPLETED) {
				System.out.println("No member found with given name " + Request.instance().getMemberName());
				break;
			}

			Iterator<Result> iterator = groceryStore.retrievedMemberInfor(Request.instance().getMemberName());
			System.out.println("Listing all MemberID, Address,Phone and Fee Paid whose name begin with "
					+ Request.instance().getMemberName());
			while (iterator.hasNext()) {
				result = iterator.next();
				System.out.println("Member name " + Request.instance().getMemberName() + " has ID member is "
						+ result.getMemberId() + " | Member Address is " + result.getMemberAddress()
						+ " | member's phone Number is " + result.getMemberPhone() + " | paid fee is "
						+ result.getMemberFeePaid() + " | and the joined date is " + result.getDateJoined().getTime().toString());
				result.reset();
			}

		} while (yesOrNo("Find information of other member ?"));
	}

//	TODO: Jack
	/**
	 * Method to be called for displaying transactions. Prompts the user for the
	 * appropriate values and uses the appropriate Library method for displaying
	 * transactions.
	 * 
	 */
	public void printTransactions() {
		Request.instance().setMemberId(getToken("Enter member id"));
		// TODO: Needs to have a range with a start date and end date inclusive.
		Request.instance().setDate(getDate("Please enter the date for which you want records as mm/dd/yy"));
		Iterator<Transaction> result = groceryStore.getTransactions(Request.instance());
		while (result.hasNext()) {
			Transaction transaction = (Transaction) result.next();
			System.out.println(transaction.getType() + "   " + transaction.getProductName() + "\n");
		}
		System.out.println("\nEnd of transactions \n");

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
		System.out.println("Listing all members by Name, Date joined, address and phone number.");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getMemberName() + " | " + result.getDateJoined().getTime().toString() + " | "
					+ result.getMemberAddress() + " | " + result.getMemberPhone());
		}

	}

//	TODO: Trung
	/**
	 * List all products will list all the products that have added on the
	 * productList.
	 * 
	 * @return All members
	 */
	public void listAllProducts() {
		Iterator<Result> iterator = groceryStore.getProducts();
		System.out.println(
				equalsLength("Product Name") + "|" + equalsLength("Product ID") + "|" + equalsLength("Current Price")
						+ "|" + equalsLength("Reorder Level") + "|" + equalsLength("StockInhand") + "\n");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(equalsLength(result.getProductName()) + "|" + equalsLength(result.getProductId()) + "|"
					+ equalsLength(String.valueOf(result.getCurrentPrice())) + "|"
					+ equalsLength(String.valueOf(result.getReorderLevel())) + "|"
					+ equalsLength(String.valueOf(result.getStockInhand())));
		}

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
			case REMOVE_MEMBER:
				removeMember();
				break;
			case ADD_PRODUCT:
				addProduct();
				break;
			case CHECKOUT_MEMBER_ITEMS:
				checkOutMember();
				break;
			case PROCESS_SHIPMENT:
				processShipment();
				break;
			case CHANGE_PRICE:
				changePrice();
				break;
			case RETRIEVE_PRODUCT_INFO:
				retrieveProductInfo();
				break;
			case RETRIEVE_MEMBER_INFO:
				retrieveMemberInfo();
				break;
			case PRINT_TRANSACTIONS:
				printTransactions();
				break;
			case LIST_OUTSTANDING_ORDERS:
				listOutstandingOrders();
				break;
			case LIST_ALL_MEMBERS:
				listAllMembers();
				break;
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
