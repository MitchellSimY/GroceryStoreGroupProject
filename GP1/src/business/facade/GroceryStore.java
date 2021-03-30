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
import business.entities.Order;
import business.entities.Product;
import business.entities.Transaction;
import business.entities.iterators.SafeIterator;

/**
 * The facade class handling all requests from users.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 *
 */
public class GroceryStore implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProductList productList = new ProductList();
	private MemberList members = new MemberList();
	private OrderList orderList = new OrderList();
	private static GroceryStore groceryStore;

	/**
	 * The collection class for Product objects
	 * 
	 * @author group
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
		 * Checks whether a product with a given product name exists.
		 * 
		 * @param product name of the product
		 * @return That product iff the product exists
		 * 
		 */
		public Product searchProductName(String name) {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getProductName().equals(name)) {
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

		public List<Product> getProductList() {
			return products;
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
		 * @return Member iff member exists
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
		 * 
		 * Checks whether a member with a given name exists.
		 * 
		 * @param name
		 * @return Member iff member exists
		 */
		/**
		 * @param name
		 * @return
		 */
		public Member searchName(String name) {
			for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
				Member member = iterator.next();
				if (member.getName().equals(name)) {
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

		/**
		 * Remove member method Is called outside of the said class and removes the
		 * member from the list.
		 */
		public boolean removeMember(Member member) {
			members.remove(member);
			return true;
		}

		/**
		 *
		 */
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
	 * The collection class for Order objects
	 * 
	 * @author group
	 */
	private class OrderList implements Iterable<Order>, Serializable {
		private static final long serialVersionUID = 1L;
		private List<Order> orders = new LinkedList<Order>();

		/**
		 * Checks whether an order with a given order id exists.
		 * 
		 * @param orderID: the ID of the order we wish to find.
		 * @return Order: returns the Order object in the orderList if it exists.
		 * 
		 */
		public Order search(int orderId) {
			Order tempOrder = new Order(null, null);
			tempOrder.setOrderID(orderId);
			for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
				Order orderCursor = (Order) iterator.next();
				if (orderCursor.equals(tempOrder)) {
					return orderCursor;
				}
			}
			return null;
		}

		/**
		 * Removes an order from the orderList given a specific orderID
		 * 
		 * @param orderID: the ID of the order we wish to find.
		 * @return true iff product could be removed
		 */
		public boolean removeOrder(int orderId) {
			Order orderToBeRemove = search(orderId);
			if (orderToBeRemove == null) {
				return false;
			} else {
				return orders.remove(orderToBeRemove);
			}
		}

		/** Original insertOrder code
		 * 	if (orderList == null) {
			orders.add(order);
			return true;
			}
			else if (search(order.getOrderID()) == null){
				orders.add(order);
				return true;
			}
			System.out.println("An Order with " + order.getOrderID() + " id already exists. Order could not be added.");
			return false;
		 */
		/**
		 * Inserts an order into the OrderList.
		 * 
		 * @param order: the Order object to be inserted.
		 * @return true: iff an Order with the orderID doesn't already exists within orders.
		 * @return false: iff an Order with the orderID already exists within orders.
		 */
		public boolean insertOrder(Order order) {
			orders.add(order);
			return true;
		}

		/**
		 * Returns an iterator to all Orders.
		 * 
		 * @return iterator to the collection
		 */
		public Iterator<Order> iterator() {
			return orders.iterator();
		}

		public List<Order> getOrderList() {
			return orders;
		}

		/**
		 * String form of the collection
		 * 
		 */
		public String toString() {
			return orders.toString();
		}
	}
	
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

	public ProductList getProductList() {
		return productList;

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
		Member member = new Member(request.getMemberName(), request.getMemberPhone(), request.getMemberAddress(),
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
	 * Remove Member. This method is utilized for people that no longer want to be a
	 * member with the grocery store.
	 * 
	 * @param instance - An object that has the requested memberId
	 * @return result - this is the result for whether the operation failed or not.
	 */
	public Result removeMember(Request instance) {
		Member memberToBeRemoved = members.search(instance.getMemberId());
		Result result = new Result();

		if (memberToBeRemoved == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
			return result;
		} else {
			members.removeMember(memberToBeRemoved);
			result.setResultCode(Result.OPERATION_COMPLETED);
			return result;
		}
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

		/**
		 * If satements checking to see if the product already exists via the ID or the
		 * name.
		 */
		if (productList.search(request.getProductId()) != null) {
			result.setResultCode(Result.PRODUCTID_EXISTS);
			return result;
		} else if (productList.searchProductName(request.getProductName()) != null) {
			result.setResultCode(Result.PRODUCT_NAME_EXISTS);
			return result;
		}

		if (productList.insertProduct(product)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
			return result;
		}

		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Get members method Showing all members that have since registered. This was
	 * called by interface > listAllMembers
	 * 
	 * @return all members
	 */
	public Iterator<Result> getMembers() {
		return new SafeIterator<Member>(members.iterator(), SafeIterator.MEMBER);
	}

	/**
	 * getProduct method Showing all products that have since added. This was called
	 * by interface > listAllProducts
	 * 
	 * @return all iterator of productList
	 */
	public Iterator<Result> getProducts() {
		return new SafeIterator<Product>(productList.iterator(), SafeIterator.PRODUCT);
	}

	/**
	 * retrievedMemberInfor searching the members list by iterator to find every
	 * member has matching name with the user input then add all found result in to
	 * a list and return an iterator of that result list
	 * 
	 * @param is user input which is a string of member's name
	 * @return all iterator of all members has matching name
	 */
	public Iterator<Result> retrievedMemberInfor(String name) {
		List<Member> memberList = new LinkedList<Member>();
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getName().equals(name)) {
				memberList.add(member);
			}
		}
		return new SafeIterator<Member>(memberList.iterator(), SafeIterator.MEMBER);
	}

	/**
	 * retrieveProductInfo searches the product list by iterator to find every
	 * product that with that matches parameter name, then adds all found results in
	 * to a temp list and returns it as an iterator.
	 * 
	 * @param name of the product(user input)
	 * @return iterator of all products with matching name.
	 */
	public Iterator<Result> retrieveProductInfo(String name) {
		List<Product> tempList = new LinkedList<Product>();
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getProductName().equals(name)) {
				tempList.add(product);
			}
		}
		return new SafeIterator<Product>(tempList.iterator(), SafeIterator.PRODUCT);
	}

	/**
	 * Searches for a given member
	 * 
	 * @param memberId id of the member
	 * @return true if the member is in the member list collection
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
	 * Searches for a given member by member name. Create a new result object and
	 * assign name.
	 * 
	 * @param request object that has the name to use for searching.
	 * @return Result object. (OPERATION_COMPLETED if the member is in the member
	 *         list collection and NO_SUCH_MEMBER if no member found).
	 */
	public Result searchName(Request request) {
		Result result = new Result();
		Member member = members.searchName(request.getMemberName());
		if (member == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setMemberFields(member);
		}
		return result;
	}

	/**
	 * 
	 * Search for a product by i.d.
	 * 
	 * @param request - has the product i.d. to use for searching.
	 * @return Result object with product files set.
	 */
	public Result searchProduct(Request request) {
		Result result = new Result();
		Product product = productList.search(request.getProductId());
		if (product == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		}
		return result;
	}

	/**
	 * Searches for a given product by product name which assigned in result object
	 * 
	 * @param request - object that has name to use as condition for searching
	 * @return Result - OPERATION_COMPLETED if the product is in the product list
	 *         collection and NO_SUCH_MEMBER if no product found
	 */
	/**
	 * @param request
	 * @return
	 */
	public Result searchProductName(Request request) {
		Result result = new Result();
		Product product = productList.searchProductName(request.getProductName());
		if (product == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
		} else {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setProductFields(product);
		}
		return result;
	}

	/**
	 * 
	 * change the price of a product.
	 * 
	 * 
	 * @param productId - The product to change.
	 * @param price     - the new price.
	 */
	public void changePrice(String productId, double price) {
		for (Product productToUpdate : getProductList().getProductList()) {
			if (productToUpdate.getProductId().equals(productId)) {
				productToUpdate.setCurrentPrice(price);
				break;
			}
		}

	}

	/**
	 * Organizes the checking out of a product
	 * 
	 * @param Request   - has the product id of the item getting checked out and
	 *                  member id of the member checking out.
	 * @param quantity- how much of the item is getting checked out.
	 * @return Result - The result of the operation.
	 */

	public Result checkOut(Request request) {
		Result result = new Result();
		Member member = members.search(request.getMemberId());
		if (member == null) {
			result.setResultCode(Result.NO_SUCH_MEMBER);
			return result;
		}
		
		Product product = productList.search(request.getProductId());
		if (product == null) {	//Check if product id exists in warehouse
			result.setResultCode(Result.PRODUCT_NOT_FOUND);
			return result;
		}
	
		if (!(product.checkOut(request.getCheckoutQty()))) {
			result.setResultCode(Result.INSUFFICIENT_STOCK);
		} 
		else {
			result.setCheckOutTransactionIndex(member.checkOut(product, request.getDate())); //Creates/Edit and get current transaction index
			if (product.getStockInHand() <= product.getReorderLevel()) {
				Order newOrder = new Order(product, request.getDate());
				System.out.println(newOrder.toString()); //TODO: DELETE AFTER DEBUG > CURRENT DAY INCORRECTLY PRINTING
				orderList.insertOrder(newOrder);
				result.setReorderPlaced(true);
			}
			result.setResultCode(Result.OPERATION_COMPLETED);
		}
		result.setProduct(product);
		result.setProductFields(product);
		result.setMemberFields(member);
		return result;
	}
	
	/**
	 * Returns an iterator to the transactions for a specific member on a certain
	 * date
	 * 
	 * @param Request - used for member id. start date and end date.
	 * @return iterator to the collection
	 */
	public Iterator<Transaction> getTransactions(Request request) {
		Member member = members.search(request.getMemberId());
		if (member == null) {
			return new LinkedList<Transaction>().iterator();
		}
		return member.getTransactionsDateRange(request.getStartDate(), request.getEndDate());
	}

	/**
	 * Retrieves a de-serialized version of the groceryStore from disk
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
	 * Serializes the GroceryStore object
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
	 * String form of the groceryStore
	 * 
	 */
	@Override
	public String toString() {
		return productList + "\n" + members;
	}

}
