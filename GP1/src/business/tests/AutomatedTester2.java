package business.tests;

import java.lang.reflect.Member;

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
	private String[] productName = { "Product1", "Product2", "Product3", "Product4" };
	private String[] idList = { "id1", "id2", "id3", "id4" };
	private Product[] productsList = new Product[4];

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
			Result result = GroceryStore.instance().addMember(Request.instance());

			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
			assert result.getMemberAddress().equals(address[count]);
			// assert result.getMemberFeePaid().equals(feePaid[count]);

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
		assert GroceryStore.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
		Request.instance().setMemberId("M10");
		System.out.println(GroceryStore.instance().searchMembership(Request.instance()));
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

	public static void main(String[] args) {
		new AutomatedTester2().testAllMethods();
	}

}