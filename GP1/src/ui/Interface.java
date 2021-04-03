package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

import business.entities.Transaction;
import business.facade.GroceryStore;
import business.facade.Request;
import business.facade.Result;

//
/**
 * 
 * This class implements the user interface for the Library project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 *
 */

public class Interface {

	// Creating objects for later use.
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
		if (yesOrNo("Would you like to use previously saved data?")) {
			retrieve();
		} else if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?\n")) {
			retrieveTest();
		} else {
			System.out.println("Creating new groceryStore.\n");
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
	public String getUserInput(String prompt) {
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
		String more = getUserInput(prompt + " Enter Y for Yes, anything else for No.");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Return all string have same length and set the string in the middle
	 * 
	 * @param string: of type String. The string to be formatted.
	 * @return string: of type String. A string with space in front and back to make string set in the middle
	 */
	private String equalsLength(String string) {
		int standar = 25 - string.length();
		if (standar > 0 && standar % 2 == 0) {
			for (int i = 0; i <= (standar / 2) - 1; ++i) {
				string = " " + string + " ";
			}
		}
		if (standar > 0 && standar % 2 != 0) {
			for (int i = 0; i <= standar / 2; ++i) {
				if (i == standar / 2) {
					string = string + " ";
				} else
					string = " " + string + " ";
			}
		}
		return string;
	}

	/**
	 * ignoredCase method. Utilized for ignoring the case input from users.
	 * 
	 * @param string
	 * @return returns the string only the first letter uppercased.
	 */
	private String ignoredCase(String string) {
		if (string.length() == 1) {
			string = string.toUpperCase();
		}
		if (string.length() > 1) {
			string = string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
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
				String item = getUserInput(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * getDouble method. This method takes a double type from the user's input
	 * 
	 * @param prompt - this is the user input
	 * @return returns the double that the user input.
	 */
	public double getDouble(String prompt) {
		do {
			try {
				String item = getUserInput(prompt);
				Double number = Double.valueOf(item);
				return number.doubleValue();
			} catch (Exception exception) {
				System.out.println("Invalid input. Please enter a double.");
			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object. In mm/dd/yy form.
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	public Calendar getDate(String prompt) {
		do {
			try {
				boolean dateValid = true;
				String item = "";
				Calendar date = new GregorianCalendar();
				// item = getToken(prompt);

				do {
					item = getUserInput(prompt);
					if (dateIsValid(item)) {
						dateValid = false;
						// break;
					}
				} while (dateValid);

				DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {

			}
		} while (true);
	}

	/**
	 * Prompts for a date and gets a date object. In MM/dd/yyyy form.
	 * 
	 * @param prompt the prompt
	 * @return the data as a Calendar object
	 */
	/**
	 * @param prompt
	 */
	public Calendar getDateFullYear(String prompt) {
		do {
			try {
				boolean dateValid = true;
				String item = "";
				Calendar date = new GregorianCalendar();

				do {
					item = getUserInput(prompt);
					if (dateIsValidMMDDYYYY(item)) {
						dateValid = false; 
					}
				} while (dateValid);
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				date.setTime(dateFormat.parse(item));
				return date;
			} catch (Exception fe) {
			}
		} while (true);
	}

	/**
	 * Checks to see if date is formated properly.
	 * 
	 * @param item - the date to check
	 * @return - true if date is valid or false if not.
	 */
	private boolean dateIsValidMMDDYYYY(String item) {
		boolean result = false;
		if (item.contains("/")) {
			if (item.split("/").length == 3) {
				String[] split = item.split("/");
				if ((split[0].length() < 3) && (split[1].length() < 3) && (split[2].length() == 4)) {
					int month = Integer.parseInt(split[0]);
					int day = Integer.parseInt(split[1]);
					int year = Integer.parseInt(split[2]);
					if (month == 2 && day < 30 && year % 4 == 0) {
						result = true;
					}
					if (month == 2 && day < 29 && year % 4 != 0) {
						result = true;
					}
					if (month == 2 && day >= 30 && year % 4 == 0) {
						System.out.println("The FEBRUARY does not has more than 29 days");
					}
					if (month == 2 && day >= 29 && year % 4 != 0) {
						System.out.println("Not a leap year, FEBRUARY does not has more than 28 days");
					}

					if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) && day < 32) {
						result = true;
					}
					if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) && day >= 32) {
						System.out.println("The Month you entered does not has more than 31 days");
					}
					if ((month == 4 || month == 6 || month == 9 || month == 11) && day < 31) {
						result = true;
					}
					if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 31) {
						System.out.println("The Month you entered does not has more than 30 days");
					}
				}

			}
		}
		return result;
	}

	/**
	 * isValid date checker method. Makes sure the user is entering in the correct
	 * date format.
	 * 
	 * @param item
	 * @return whether the date is valid or not.
	 */
	private boolean dateIsValid(String item) {
		boolean result = false;
		if (item.contains("/")) {

			if (item.split("/").length == 3) {

				String[] split = item.split("/");

				if ((split[0].length() < 3) && (split[1].length() < 3) && (split[2].length() < 3)) {
					int month = Integer.parseInt(split[0]);
					int day = Integer.parseInt(split[1]);
					int year = Integer.parseInt(split[2]);
					if (month == 2 && day < 30 && year % 4 == 0) {
						result = true;
					}
					if (month == 2 && day < 29 && year % 4 != 0) {
						result = true;
					}
					if (month == 2 && day >= 30 && year % 4 == 0) {
						System.out.println("The FEBRUARY does not has more than 29 days");
					}
					if (month == 2 && day >= 29 && year % 4 != 0) {
						System.out.println("Not a leap year, FEBRUARY does not has more than 28 days");
					}

					if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) && day < 32) {
						result = true;
					}
					if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10
							|| month == 12) && day >= 32) {
						System.out.println("The Month you entered does not has more than 31 days");
					}
					if ((month == 4 || month == 6 || month == 9 || month == 11) && day < 31) {
						result = true;
					}
					if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 31) {
						System.out.println("The Month you entered does not has more than 30 days");
					}
				}

			}
		}
		return result;
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
				int value = Integer.parseInt(getUserInput("Enter command: "));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Help method. Method strictly for advising the user which menu selection they can select from.
	 * 
	 */
	public void help() {
		System.out.println("Please select an option.");
		System.out.println(EXIT + " to exit the interface.");
		System.out.println(ENROLL_MEMBER + " to enroll a member.");
		System.out.println(REMOVE_MEMBER + " to remove a member.");
		System.out.println(ADD_PRODUCT + " to add a new product.");
		System.out.println(CHECKOUT_MEMBER_ITEMS + " to checkout a member's items.");
		System.out.println(PROCESS_SHIPMENT + " to process a shipment.");
		System.out.println(CHANGE_PRICE + " to change the price of an item.");
		System.out.println(RETRIEVE_PRODUCT_INFO + " to retrieve product information.");
		System.out.println(RETRIEVE_MEMBER_INFO + " to retrieve member information.");
		System.out.println(PRINT_TRANSACTIONS + " to print transactions.");
		System.out.println(LIST_OUTSTANDING_ORDERS + " to print all outstanding (not yet fulfilled) orders.");
		System.out.println(LIST_ALL_MEMBERS + " to print information of all members.");
		System.out.println(LIST_ALL_PRODUCTS + " to print information of all products.");
		System.out.println(SAVE + " to  save data.");
		System.out.println(HELP + " for help.");
	}

	/**
	 * Completed Add member method. Utilized for adding a new member. Unless the member was successfully added, it prompts the member 
	 * was not added.
	 * 
	 */
	public void addMember() {

		Request.instance().setMemberName(ignoredCase(getName("Please enter the Member's name: ")));
		Request.instance().setMemberAddress(getName("Please enter the Member's address: "));
		Request.instance().setMemberPhone(getName("Please enter the Member's phone number: "));
		Request.instance().setMemberFeePaid(getDouble("Please enter how much the Member paid: "));
		Request.instance().setDate(getDate("Please enter the date joined as mm/dd/yy:"));

		Result result = groceryStore.addMember(Request.instance());

		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member.");
		} else {
			System.out.println(result.getMemberName() + "'s ID is " + result.getMemberId());
		}
	}

	/**
	 * Remove Member Method. This method is used to remove members from the existing
	 * list.
	 * 
	 * @return Advises if the user has since been removed or not.
	 */
	public void removeMember() {
		Request.instance().setMemberId(getUserInput("Enter the ID of the member you'd like to remove: "));
		Result result = groceryStore.removeMember(Request.instance());

		if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Member has since been removed.");
		} else if (result.getResultCode() == Result.NO_SUCH_MEMBER) {
			System.out.println("MemberID entered not found.");
		} else {
			System.out.println("Member could not be removed.");
		}

	}

	/**
	 * Add product method. Utilized for adding a new product
	 * 
	 * @return none. Creates product object.
	 */
	public void addProduct() {
		Request.instance().setProductName(ignoredCase(getName("Enter Product name: ")));
		// Creating a result object to advise any messages
		Result result = groceryStore.searchProductName(Request.instance());
		if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Product Name already exists");
			return;
		}
		Request.instance().setProductId(getUserInput("Enter Product ID: "));
		result = groceryStore.searchProductID(Request.instance());
		if (result.getResultCode() == Result.PRODUCTID_EXISTS) {
			System.out.println("Product ID already exists");
			return;
		}
		Request.instance().setCurrentPrice(getDouble("Please enter current price: "));
		Request.instance().setStockInhand(getNumber("Please enter current stock in hand: "));
		Request.instance().setReorderLevel(getNumber("Please enter Re-Order level: "));
		Request.instance().setDate(new GregorianCalendar());
		result = groceryStore.addProduct(Request.instance());
		// If statements that'll advise the user what had happened
		if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Product could not be added.");
		} else {
			System.out.println(result.getProductName() + " has since been added." + "\nAn Order for "
					+ result.getProductName() + " has been made with qty " + result.getQuantityOrdered() + ".");
		}
	}

	/**
	 * Method to be called for checking out a members products from the grocery
	 * store. Prompts the user for their products and quantity and uses the
	 * appropriate GroceryStore method for checking out. Displays the transaction
	 * and reorders a product if it falls below the reorder level.
	 * 
	 * No parameters and no return.
	 */
	public void checkOutMember() {
		Request.instance().setMemberId(getUserInput("Enter member id:"));
		Result result = groceryStore.searchMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No member with id " + Request.instance().getMemberId());
			return;
		}
		do {
			Request.instance().setProductId(getUserInput("Enter product id:"));
			Request.instance().setCheckoutQty(getNumber("Enter the number of units being sold: "));
			Request.instance().setDate(new GregorianCalendar());
			result = groceryStore.checkOut(Request.instance());
			if (result.getResultCode() == Result.OPERATION_COMPLETED) {
				DecimalFormat df = new DecimalFormat("$###,##0.00");
				int transactionIndex = result.getCheckOutTransactionIndex();
				System.out.println("Purchased: " + result.getTransactions().get(transactionIndex)
						.currentProductCheckoutToString(result.getProduct()) + "\n" 
						+ "Total Price: " + df.format(result.getTransactions().get(transactionIndex).getTotalCost()));
				if (result.isReorderPlaced()) {
					System.out.println("Reorder placed for " + result.getProductName() + " for qty = "
							+ result.getReorderLevel() * 2 + " with orderID " + result.getOrderID());
				}
			} else if (result.getResultCode() == Result.PRODUCT_NOT_FOUND) {
				System.out.println("Product not found.");
			} else if (result.getResultCode() == Result.INSUFFICIENT_STOCK) {
				System.out.println("Not enough stock.");
			} else {
				System.out.println("Could not sell product.");
			}
		} while (yesOrNo("Check out more products?"));
	}

	/**
	 * Method to be called for checking processing a shipment. Prompts the user for
	 * the Order ID and uses the appropriate GroceryStore method. Displays the
	 * appropriate message and loops for more orders if needed.
	 */
	public void processShipment() {
		do {
			Request.instance().setOrderID(getNumber("Enter Order ID:"));
			Result result = groceryStore.processShipment(Request.instance());
			if (result.getResultCode() == Result.ORDER_PROCESSED) {
				if (result.isOrderStatus()) {
					System.out.println("OrderID: " + result.getOrderID() + " processed.\nProduct ID: "
							+ result.getProductId() + "\tProduct Name: " + result.getProductName() + "\tUpdated Qty: "
							+ result.getStockInhand());
				}
			} else if (result.getResultCode() == Result.PRODUCT_NOT_FOUND) {
				System.out.println("Order does not have a product.");
			} else if (result.getResultCode() == Result.ORDER_ALREADY_PROCESSED) {
				System.out.println("Order has already been processed.");
			} else {
				System.out.println("Order does not exist.");
			}
		} while (yesOrNo("Process more Orders?"));

	}

	/**
	 * The method take the user input which is a id of a product then searching in
	 * the product list of GroceryStore object to find out if there a products with
	 * user Id string input exist, then update the price for the product by asking
	 * user Then print out all the information of the product was update in price
	 * term
	 */
	public void changePrice() {
		Request.instance().setProductId(getUserInput("Enter ID of product you want price changed:"));
		Result result = groceryStore.searchProduct(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No Product found with given ID " + Request.instance().getProductId());
		} else {
			double newPrice = getDouble("Please enter the new price: " + Request.instance().getProductId());
			result.setCurrentPrice(newPrice);
			groceryStore.changePrice(Request.instance().getProductId(), newPrice);
			System.out.println(
					"Product name " + result.getProductName() + " has new price is " + result.getCurrentPrice());
		}
	}

	/**
	 * The method take the input string which is a name of a product then searching
	 * in the product list of GroceryStore object to find out all the products have
	 * matching name and return in safe SafeIterator of matching product then print
	 * out all information of each member like productID current price,stock in hand
	 * and reorder level
	 */
	public void retrieveProductInfo() {

		Request.instance()
				.setProductName(getName("Please enter name of product you want to retrieve information about "));

		Iterator<Result> iterator = groceryStore.retrieveProductInfo(Request.instance().getProductName());
		if (!iterator.hasNext()) {
			System.out.println("No Product found with given name " + Request.instance().getProductName());
		} else {
			System.out.println("Listing all ProductID, Current Price, stock in hand and Reoder Level has name is "
					+ Request.instance().getProductName());

			System.out.println(equalsLength("Product Name") + "|" + equalsLength("Product ID") + "|"
					+ equalsLength("Current Price") + "|" + equalsLength("StockInhand") + "|"
					+ equalsLength("Reorder Level"));

			while (iterator.hasNext()) {
				Result result = iterator.next();
				System.out.println(equalsLength(result.getProductName()) + "|" + equalsLength(result.getProductId())
						+ "|" + equalsLength(String.valueOf(result.getCurrentPrice())) + "|"
						+ equalsLength(String.valueOf(result.getStockInhand())) + "|"
						+ equalsLength(String.valueOf(result.getReorderLevel())));
				result.reset();
			}
		}
	}

	/**
	 * RetrieveMemberInfo method The method take the input string which is a name of
	 * a member then searching in the member list of GroceryStore object to find out
	 * all the members have matching name and return in safe SafeIterator of
	 * matching member then print out all information of each member like memberID ,
	 * address,phone number and paid fee
	 */
	public void retrieveMemberInfo() {

		Request.instance().setMemberName(getName("Please enter name of member you want to retrieve information about"));

		Iterator<Result> iterator = groceryStore.retrieveMemberInfo(Request.instance().getMemberName());
		if (!iterator.hasNext()) {
			System.out.println("No member found with given name " + Request.instance().getMemberName());
		} else {
			System.out.println("Listing all  address, fee paid and member id whose name begin with "
					+ Request.instance().getMemberName());
			System.out.println(equalsLength("Member Name") + "|" + equalsLength("Member Address") + "|"
					+ equalsLength("Member Paid Fee") + "|" + equalsLength("Member ID"));
			while (iterator.hasNext()) {
				Result result = iterator.next();
				System.out.println(equalsLength(result.getMemberName()) + "|"
						+ equalsLength(String.valueOf(result.getMemberAddress())) + "|"
						+ equalsLength(String.valueOf(result.getMemberFeePaid())) + "|"
						+ equalsLength(result.getMemberId()));
				result.reset();
			}
		}

	}

	/**
	 * PrintTransactions method Method to be called for displaying transactions within a given date range. 
	 * Prompts the user for the start and end date and uses the appropriate GroceryStore method for displaying transactions.
	 * 
	 */
	public void printTransactions() {
		Request.instance().setMemberId(getUserInput("Enter member id"));
		Result result = groceryStore.searchMembership(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("No member with id " + Request.instance().getMemberId());
			return;
		}
		Request.instance().setStartDate(
				getDateFullYear("Please enter the start date (inclusive) for which you want records as mm/dd/yyyy"));
		Request.instance().setEndDate(
				getDateFullYear("Please enter the end date (inclusive) for which you want records as mm/dd/yyyy"));
		if (!((Request.instance().getStartDate().before(Request.instance().getEndDate()))
				|| (Request.instance().getStartDate().equals(Request.instance().getEndDate())))) {
			System.out.println("Start date must be before or equal to end date.");
		} else {
			Iterator<Transaction> result1 = groceryStore.getTransactions(Request.instance());
			if (!result1.hasNext()) {
				System.out.println("\nNo transactions found in that date range.\n");
			} else {
				while (result1.hasNext()) {
					Transaction transaction = (Transaction) result1.next();
					System.out.println(transaction.toString());
				}
				System.out.println("\nEnd of transactions \n");
			}
		}
	}

	/**
	 * 
	 * List all outstanding OrderID, Product name, Date order was placed, and quantity ordered. Orders are considered outstanding if their
	 * orderStatus is false.
	 * 
	 */
	public void listOutstandingOrders() {
		Iterator<Result> iterator = groceryStore.getOrders();

		System.out.println(equalsLength("OrderID") + "|" + equalsLength("Product Name") + "|"
				+ equalsLength("Date Place") + "|" + equalsLength("Qty Ordered"));

		while (iterator.hasNext()) {
			Result result = iterator.next();
			if (result.isOrderStatus() == false) {
				System.out.println(equalsLength("" + result.getOrderID()) + "|"
						+ equalsLength(result.getReorderProduct().getProductName()) + "|"
						+ equalsLength(result.getDateOrderPlacedString()) + "|"
						+ equalsLength("" + result.getQuantityOrdered()));
			}
		}
	}

	/**
	 * List all the members that have registered.
	 * 
	 *
	 */
	public void listAllMembers() {
		Iterator<Result> iterator = groceryStore.getMembers();

		System.out.println(equalsLength("Member Name") + "|" + equalsLength("Member ID") + "|"
				+ equalsLength("Member Phone") + "|" + equalsLength("Member Address") + "|"
				+ equalsLength("Member Paid Fee") + equalsLength("Member Date Joined"));

		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(equalsLength(result.getMemberName()) + "|" + equalsLength(result.getMemberId()) + "|"
					+ equalsLength(result.getMemberPhone()) + "|" + equalsLength(result.getMemberAddress()) + "|"
					+ equalsLength(String.valueOf(result.getMemberFeePaid())) + "|"
					+ equalsLength(result.getDateJoinedByString()));
		}
	}

	/**
	 * List all products will list all the products that have added on the
	 * productList.
	 * 
	 * 
	 */
	public void listAllProducts() {
		Iterator<Result> iterator = groceryStore.getProducts();
		System.out.println(
				equalsLength("Product Name") + "|" + equalsLength("Product ID") + "|" + equalsLength("Current Price")
						+ "|" + equalsLength("Reorder Level") + "|" + equalsLength("StockInhand"));

		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(equalsLength(result.getProductName()) + "|" + equalsLength(result.getProductId()) + "|"
					+ equalsLength(String.valueOf(result.getCurrentPrice())) + "|"
					+ equalsLength(String.valueOf(result.getReorderLevel())) + "|"
					+ equalsLength(String.valueOf(result.getStockInhand())));
		}

	}

	/**
	 * Method to be called for saving the GroceryStore object. Uses the GroceryStore
	 * method for saving.
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
	 * Method to be called for retrieving saved data. Uses the GroceryStore method
	 * for retrieval.
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
	 * Method to be called for retrieving saved data. Uses the GroceryStore method
	 * for retrieval.
	 * 
	 */

	private void retrieveTest() {
		try {
			if (groceryStore == null) {
				groceryStore = GroceryStore.retrieveTest();
				if (groceryStore != null) {
					System.out.println("The groceryStore test bed has been successfully set up.\n");
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
