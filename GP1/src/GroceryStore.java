import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Mitch: I made memberList and productList as their own classes when this was
 * first created. I ended up understanding the basic concepts of singletons and
 * I've since deleted those classes.
 * 
 * If im correct they'll both go in here as Singletons.
 *
 */
public class GroceryStore implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductList productList = new ProductList();
	private MemberList members = new MemberList();
	private static GroceryStore groceryStore;

	/**
	 * The collection class for Product objects
	 * 
	 * @author group
	 *
	 */
	private class ProductList implements Iterable<Product>, Serializable {
		private static final long serialVersionUID = 1L;
		private List<Product> products = new LinkedList<Product>();

		/**
		 * Checks whether a product with a given product id exists.
		 * 
		 * @param productId the id of the product
		 * @return true iff the product exists
		 * 
		 */
		public Product search(String productId) {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getProductId().equals(productId)) {
					return product;
				}
			}
			return null;
		}

		/**
		 * Removes a product from the catalog
		 * 
		 * @param productId product id
		 * @return true iff product could be removed
		 */
		public boolean removeProduct(String productId) {
			Product product = search(productId);
			if (product == null) {
				return false;
			} else {
				return products.remove(product);
			}
		}

		/**
		 * Inserts a product into the collection
		 * 
		 * @param product the product to be inserted
		 * @return true iff the product could be inserted. Currently always true
		 */
		public boolean insertProduct(Product product) {
			products.add(product);
			return true;
		}

		/**
		 * Returns an iterator to all products
		 * 
		 * @return iterator to the collection
		 */
		public Iterator<Product> iterator() {
			return products.iterator();
		}

		/**
		 * String form of the collection
		 * 
		 */
		public String toString() {
			return products.toString();
		}
	}

	// ====== Start Member List Class ========
	private class MemberList implements Iterable<Member>, Serializable {
		private static final long serialVersionUID = 1L;
		private List<Member> members = new LinkedList<Member>();

		// Adding members method.
		public boolean insertMember(Member member) {
			members.add(member);
			return true;
		}

		// Showing members to see if it was added successfully. Can delete this method.
		public void showMembers() {

		}

		@Override
		public Iterator<Member> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Checks whether a member with a given member id exists.
		 * 
		 * @param memberId the id of the member
		 * @return true iff member exists
		 * 
		 */
		public Member search(String memberId) {
			for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
				Member member = iterator.next();
				if (member.getMemberId().equals(memberId)) {
					return member;
				}
			}
			return null;
		}

	}
	// ====== End of Member List Class ========

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */
	private GroceryStore() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static GroceryStore instance() {
		if (groceryStore == null) {
			return groceryStore = new GroceryStore();
		} else {
			return groceryStore;
		}
	}

	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone());
		if (members.insertMember(member)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	public void showMembers() {

	}

	/**
	 * Searches for a given member
	 * 
	 * @param memberId id of the member
	 * @return true iff the member is in the member list collection
	 */
	public Result searchMembership(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
		}
		return result;
	}

	/**
	 * Organizes the checking out of a product
	 * 
	 * @param memberId  member id
	 * @param productId product id
	 * @return the product checked out.
	 */
	/**
	 * @param request
	 * @param quantity
	 * @return
	 */
	public Result checkOut(Request request, int quantity) {
		Result result = new Result();
		Product product = productList.search(request.getProductId());

		if (product == null) {
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;

		}
		result.setProductFields(product);
		if ((product.getStockInHand() - quantity) < 0) {
			result.setResultCode(Result.INSUFFICIENT_STOCK);
			return result;
		}
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
			return result;
		}
//		 NEED TO FINISH
//        result.setMemberFields(member);
//        if (!(product.issue(member) && member.issue(product))) {
//            result.setResultCode(Result.OPERATION_FAILED);
//        } else {
//            result.setResultCode(Result.OPERATION_COMPLETED);
//            result.setProductFields(product);
//        }
		return result;

	}

	/**
	 * Retrieves a deserialized version of the groceryStore from disk
	 * 
	 * @return a GroceryStore object
	 */
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("GroceryStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			groceryStore = (GroceryStore) input.readObject();
			Member.retrieve(input);
			return groceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

}
