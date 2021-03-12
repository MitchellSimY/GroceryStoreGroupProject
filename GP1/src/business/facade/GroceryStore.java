package business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business.entities.Member;
import business.entities.Product;
import business.entities.Transaction;
import business.entities.iterators.SafeIterator;

/**
 * The facade class handling all requests from users.
 * 
 * @author group
 *
 */
public class GroceryStore implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductList productList = new ProductList();
	private MemberList members = new MemberList();
	private static GroceryStore groceryStore;

	/**
	 * 
	 * @author Brahma Dathan and Sarnath Ramnath
	 * @Copyright (c) 2010
	 * 
	 *            Redistribution and use with or without modification, are permitted
	 *            provided that the following conditions are met:
	 *
	 *            - the use is for academic purpose only - Redistributions of source
	 *            code must retain the above copyright notice, this list of
	 *            conditions and the following disclaimer. - Neither the name of
	 *            Brahma Dathan or Sarnath Ramnath may be used to endorse or promote
	 *            products derived from this software without specific prior written
	 *            permission.
	 *
	 *            The authors do not make any claims regarding the correctness of
	 *            the code in this module and are not responsible for any loss or
	 *            damage resulting from its use.
	 */

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

	/**
	 * The collection class for Member objects
	 * 
	 * ====== Start Member List Class ========
	 */
	private class MemberList implements Iterable<Member>, Serializable {
		private static final long serialVersionUID = 1L;
		private List<Member> members = new LinkedList<Member>();

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

		/**
		 * Inserts a member into the collection
		 * 
		 * @param member the member to be inserted
		 * @return true iff the member could be inserted. Currently always true
		 */
		public boolean insertMember(Member member) {
			members.add(member);
			return true;
		}

		@Override
		public Iterator<Member> iterator() {
			return members.iterator();
		}

		/**
		 * String form of the collection
		 * 
		 */
		@Override
		public String toString() {
			return members.toString();
		}

	}
	// ====== End of Member List Class ========

	/**
	 * Private for the singleton pattern Creates the productList and member
	 * collection objects
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

	/**
	 * Add member method. This method adds a member utilizing the request object as
	 * parameters for the new member. In order the member parameters are, Name,
	 * Address Phone, Membership Fee, and date.
	 * 
	 * @param request - This has all the user input for the member's information
	 * @return result - The return is to determine whether the addition of the
	 *         member was successful. If not, it returns an error code.
	 */
	public Result addMember(Request request) {
		Result result = new Result();
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone(),
				request.getMemberFeePaid(), request.getDate());

		if (members.insertMember(member)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Add product method. This method will allow for the addition of new products.
	 * Called from addProduct method in Interface.
	 * 
	 * @param request - This is the product object. Contains all the product
	 *                information.
	 * @return Result - Which advises the user whether the function was successful.
	 */
	public Result addProduct(Request request) {
		Result result = new Result();
		Product product = new Product(request.getProductName(), request.getProductId(), request.getStockInhand(),
				request.getReorderLevel(), request.getCurrentPrice());
		if (productList.insertProduct(product)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Get members method Showing all members that have since registerd. This was
	 * called by interface > listAllMembers
	 * 
	 * @return all members
	 */
	public Iterator<Result> getMembers() {
		return new SafeIterator<Member>(members.iterator(), SafeIterator.MEMBER);
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

		result.setMemberFields(member);

		if (!(product.checkOut(quantity) && member.checkOut(product))) {
			result.setResultCode(Result.OPERATION_FAILED);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		}
		return result;

	}

	/**
	 * Returns an iterator to the transactions for a specific member on a certain
	 * date
	 * 
	 * @param memberId member id
	 * @param date     date of issue
	 * @return iterator to the collection
	 */
	public Iterator<Transaction> getTransactions(Request request) {
		Member member = members.search(request.getMemberId());
		if (member == null) {
			return new LinkedList<Transaction>().iterator();
		}
		return member.getTransactionsOnDate(request.getDate());
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

	/**
	 * Serializes the Library object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(groceryStore);
			Member.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * String form of the library
	 * 
	 */
	@Override
	public String toString() {
		return productList + "\n" + members;
	}
}
