package business.tests;

import java.lang.reflect.Member;

import business.entities.Product;
import business.facade.GroceryStore;
import business.facade.Request;
import business.facade.Result;

/**
 * This class generates sample automated tests for the library system using
 * asserts.
 * 
 * @author group
 *
 */
public class AutomatedTester {
	private GroceryStore groceryStore;
	private String[] names = { "n1", "n2", "n3" };
	private String[] addresses = { "a1", "a2", "a3" };
	private String[] phones = { "p1", "p2", "p3" };
	private Member[] members = new Member[3];
	private String[] productName = { "p1", "p2", "p3", "p4", "p5", "p6" };
	private String[] ids = { "i1", "i2", "i3", "i4", "i5", "i6" };
	private Product[] products = new Product[6];

	/**
	 * Tests Member creation.
	 */
	public void testAddMember() {
		for (int count = 0; count < members.length; count++) {
			Request.instance().setMemberAddress(addresses[count]);
			Request.instance().setMemberName(names[count]);
			Request.instance().setMemberPhone(phones[count]);
			Result result = GroceryStore.instance().addMember(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getMemberName().equals(names[count]);
			assert result.getMemberPhone().equals(phones[count]);
		}
	}

	public void testAddProduct() {
		for (int count = 0; count < products.length; count++) {
			Request.instance().setProductName(productName[count]);
			Request.instance().setProductId(ids[count]);
			Result result = GroceryStore.instance().addProduct(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getProductName().equals(productName[count]);
			assert result.getProductId().equals(ids[count]);
		}
	}

	public void testSearchMembership() {
		Request.instance().setMemberId("M1");
		assert GroceryStore.instance().searchMembership(Request.instance()).getMemberId().equals("M1");
		Request.instance().setMemberId("M4");
		assert GroceryStore.instance().searchMembership(Request.instance()) == null;
	}

	public void testAll() {
		testAddMember();
		testAddProduct();
		testSearchMembership();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
