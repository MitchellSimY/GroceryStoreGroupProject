package business.tests;

import java.lang.reflect.Member;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import business.entities.Product;
import business.facade.GroceryStore;
import business.facade.Request;
import business.facade.Result;

/**
 * Automated test bed. Utilized for creating the grocery store system.
 * Once ran through the interface, it'll create a list of Members, one of which is deleted,
 * and their respective information.
 * A list of products will be generated, along with their respective information.
 * It'll also perform a quick member search, a change in price for a product, 
 * a checkout, and a ProcessOrder 
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */

public class AutomatedTester {
	// Creating the grocerystore object
	private GroceryStore groceryStore;

	// Setting arrays of variables for member creation
	private String[] names = { "name1", "name2", "name3", "name4", "name5", "name6" };
	private String[] address = { "Address1", "Address2", "Address3", "Address4", "Address5", "Address6" };
	private String[] phones = { "Phone1", "Phone2", "Phone3", "Phone4", "Phone5", "Phone6" };
	private double[] feePaid = { 9.99, 4.99, 24.99, 19.99, 1.99, 0.99 };
	private Member[] membersList = new Member[6];

	// Setting arrays of variables for product creation
	private String[] productName = { "Apple", "Apple Juice", "Apple Pie", "Apple Beer", "Apple Cake", "Banana",
			"Oranges", "Tomato", "Kit-Kat", "BellPeppers", "Snickers", "Olive Oil", "Olives", "Chicken",
			"Chicken Breasts", "Chicken Thighs", "Raw Chicken", "Ground Beef", "Bacon", "Milk" };

	private String[] idList = { "id1", "id2", "id3", "id4", "id5", "id6", "id7", "id8", "id9", "id10", "id11", "id12",
			"id13", "id14", "id15", "id16", "id17", "id18", "id19", "id20" };

	private double[] priceList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	private int[] reorderLevelList = { 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75 };

	private int[] stockInHandList = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
			100, 100, 100, 100 };

	private Product[] productsList = new Product[20];

	private Calendar date = new GregorianCalendar();

	/**
	 * testAddMembers method. This method is to test add members from the above
	 * arrays
	 * 
	 * @return nothing. Creates the member
	 */
	public void testAddMembers() {
		for (int count = 0; count < membersList.length; count++) {
			Request.instance().setMemberName(names[count]);
			Request.instance().setMemberAddress(address[count]);
			Request.instance().setMemberPhone(phones[count]);
			Request.instance().setMemberFeePaid(feePaid[count]);
			DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);

			try {
				date.setTime(dateFormat.parse("04/01/21"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Request.instance().setDate(date);
			Result result = GroceryStore.instance().addMember(Request.instance());

			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
			assert result.getMemberAddress().equals(address[count]);
//			assert result.getMemberFeePaid().equals(feePaid[count]);

		}
	}

	/**
	 * testRemoveMember method Method utlized to test and see if we can successfully
	 * remove a member from the member list.
	 * 
	 * @return Nothing. Advises for a possible exception if the user cannot be
	 *         removed.
	 */
	public void testRemoveMember() {
		Request.instance().setMemberId("6");
		Result result = GroceryStore.instance().removeMember(Request.instance());

		assert result.getResultCode() == Result.OPERATION_COMPLETED : "Operation Failed: User could not be removed";
	}

	/**
	 * testAddProducts method This method is designed to add products
	 * 
	 * @return nothing. Creates products
	 */
	public void testAddProducts() {
		for (int count = 0; count < productsList.length; count++) {
			Request.instance().setProductName(productName[count]);
			Request.instance().setProductId(idList[count]);
			Request.instance().setCurrentPrice(priceList[count]);
			Request.instance().setReorderLevel(reorderLevelList[count]);
			Request.instance().setStockInhand(stockInHandList[count]);

			Result result = GroceryStore.instance().addProduct(Request.instance());

			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productName[count]);
			assert result.getProductId().equals(idList[count]);

		}
	}

	/**
	 * 
	 */
	public void testChangePrice() {
		Result result = GroceryStore.instance().changePrice("id1", 12.34);
		assert result.getResultCode() == Result.OPERATION_COMPLETED : "Price could not be changed";
	}

	/**
	 * testSearchMembership This method is utilized to test search all the available
	 * members
	 * 
	 * @return nothing. Searches for the member
	 */
	public void testSearchMembership() {
		Request.instance().setMemberId("1");
		assert GroceryStore.instance().searchMembership(Request.instance()).getMemberId()
				.equals("1") : "No Member Found";
//		Request.instance().setMemberId("M10");
//		System.out.println(GroceryStore.instance().searchMembership(Request.instance()));
//      assert GroceryStore.instance().searchMembership(Request.instance()).getResultCode() == 9;
	}

	/**
	 * testCheckOut method. Method is used to perform a test checkout.
	 * 
	 * @return none. Will print out a statement if it fails
	 */
	public void testCheckOut() {
		Request.instance().setMemberId("1");
		Request.instance().setProductId("id1");
		Request.instance().setCheckoutQty(5);
		Result result = GroceryStore.instance().checkOut(Request.instance());

		assert result.getResultCode() == Result.OPERATION_COMPLETED : "Operation failed. Could not checkout user.";

	}

	/**
	 * test the processShipment method. Of the 20 products added above, It process
	 * the order 1 through 10.
	 * 
	 * 
	 * 
	 * @return none. Will print out a statement if it fails
	 */
	public void processShipment() {

		for (int i = 1; i <= 10; i++) {
			Request.instance().setOrderID(i);
			Result result = GroceryStore.instance().processShipment(Request.instance());

			assert result.getResultCode() == Result.ORDER_PROCESSED : "Operation failed. Could not process shipment.";

		}
	}

	/**
	 * testAllMethods This method is for calling all the test methods
	 */
	public void testAllMethods() {
		testAddMembers();
		testAddProducts();
		testSearchMembership();
		testRemoveMember();
		testChangePrice();
		testCheckOut();
		processShipment();
	}

	public GroceryStore getGroceryStore() {
		return groceryStore;
	}

	public void setGroceryStore(GroceryStore groceryStore) {
		this.groceryStore = groceryStore;
	}

	public static GroceryStore main(String[] args) {
		AutomatedTester myTester = new AutomatedTester();
		myTester.testAllMethods();

		myTester.getGroceryStore();
		return GroceryStore.instance();

	}

}