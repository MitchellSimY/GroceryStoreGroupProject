import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Interface {
	
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
	private static Interface interfaceVariable;
	
	// Making static variables for user selection later.
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
	
	/**
	 * Process method. A bunch of switch cases to find out what the
	 * user/clerk wants to perform.
	 */
	public void process() { 
		int command;
		help();
		// Making the command variable equal what the user inputs
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ENROLL_MEMBER:
				System.out.println("TODO: ENROLL MEMBER METHOD HERE");
				break;
			case ADD_PRODUCT:
				System.out.println("TODO: ADD PRODUCT METHOD HERE");
				break;
			}
		}
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
                if (value >= EXIT && value <= PRINT_TRANSACTIONS) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
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
	
	
	
	public static void main(String[] args) {
		Interface.instance().process();
	}
	
	
	
}
