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

public class AutomatedTester2 {
	// Creating the grocerystore object
	private GroceryStore groceryStore;

	// Setting arrays of variables for member creation
	private String[] names = { "name1", "name2", "name3", "name4" };
	private String[] address = { "Address1", "Address2", "Address3", "Address4" };
	private String[] phones = { "Phone1", "Phone2", "Phone3", "Phone4" };
	private double[] feePaid = { 9.99, 4.99, 24.99, 19.99 };
	private Member[] membersList = new Member[4];

	// Setting arrays of variables for product creation
	private String[] productName = { "Product1", "Product2", "Product3", "Product4", "Product5", "Product6", "Product7",
			"Product8", "Product9", "Product10", "Product11", "Product12", "Product13", "Product14", "Product15",
			"Product16", "Product17", "Product18", "Product19", "Product20" };

	private String[] idList = { "id1", "id2", "id3", "id4", "id5", "id6", "id7", "id8", "id9", "id10", "id11", "id12",
			"id13", "id14", "id15", "id16", "id17", "id18", "id19", "id20" };

	private double[] priceList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };

	private int[] reorderLevelList = { 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75, 75,
			75, };

	private int[] stockInHandList = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
			100, 100, 100, 100, };

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
	 * testSearchMembership This method is utilized to test search all the available
	 * members
	 * 
	 * @return nothing. Searches for the member
	 */
	public void testSearchMembership() {
		Request.instance().setMemberId("M1");
//		assert GroceryStore.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
		Request.instance().setMemberId("M10");
//		System.out.println(GroceryStore.instance().searchMembership(Request.instance()));
		assert GroceryStore.instance().searchMembership(Request.instance()).getResultCode() == 9;
	}

	/**
	 * testAllMethods This method is for calling all the test methods
	 */
	public void testAllMethods() {
		testAddMembers();
		testAddProducts();
		testSearchMembership();
	}

	public GroceryStore getGroceryStore() {
		return groceryStore;
	}

	public void setGroceryStore(GroceryStore groceryStore) {
		this.groceryStore = groceryStore;
	}

	public static GroceryStore main(String[] args) {
		AutomatedTester2 myTester = new AutomatedTester2();
		myTester.testAllMethods();

		myTester.getGroceryStore();
		return GroceryStore.instance();

	}

}