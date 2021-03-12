package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business.entities.iterators.FilteredIterator;

/**
 * Member represents a member of the groceryStore.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	// variables to help create the member object
	private String name;
	private String phoneNumber, address;
	private double feePaid;
	private String memberId;
	private static int counter = 0;
	private List<Product> productsPurchased = new LinkedList<Product>();
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private Calendar joinedDate;

	/**
	 * Creates a single member
	 * 
	 * @param name        name of the member
	 * @param address     address of the member
	 * @param phoneNumber phone number of the member
	 * @param feePaid     how much fee have they paid
	 * @param joinedData  date joined
	 */
	public Member(String name, String phoneNumber, String address, double feePaid, Calendar joinedDate) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.feePaid = feePaid;
		counter = counter + 1;
		this.memberId = "" + counter;
		this.joinedDate = joinedDate;

	}

	/**
	 * Stores the product as sold to the member
	 * 
	 * @param product the product to be issued
	 * @return true iff the product could be marked as sold. always true currently
	 */
	public boolean checkOut(Product product) {
		if (productsPurchased.add(product)) {
			transactions.add(new Transaction("Sold", product.getProductName()));
			return true;
		}
		return false;
	}

	/**
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param date the date for which the transactions have to be retrieved
	 * @return the iterator to the collection
	 */
	public Iterator<Transaction> getTransactionsOnDate(Calendar date) {
		return new FilteredIterator(transactions.iterator(), transaction -> transaction.onDate(date));
	}

	/**
	 * Returns the list of all transactions for this member.
	 * 
	 * @return the iterator to the list of Transaction objects
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

///// 			SETTER AND GETTER, HASHCODE,EQUALS AND TOSTRING SECTION	

	/**
	 * Getter for firstName
	 * 
	 * @return String firstName
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name
	 * 
	 * @param newName member's new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for phoneNumber
	 * 
	 * @return String phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for phoneNumber
	 * 
	 * @param a String for new phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Getter for address
	 * 
	 * @return String address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for address
	 * 
	 * @param new String for new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter for memberId
	 * 
	 * @return The String memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * Setter for memberID
	 * 
	 * @param a String for new memberID
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * Getter for feePaid
	 * 
	 * @return integer feePaid
	 */
	public double getFeePaid() {
		return feePaid;
	}

	/**
	 * Setter for feePaid
	 * 
	 * @param a integer for new feePaid
	 */
	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

	/**
	 * Getter for joinedDate
	 * 
	 * @return the Calendar object joinedDate
	 */
	public Calendar getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Calendar joinedDate) {
		this.joinedDate = joinedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return " Name is " + name + " Phone Number " + phoneNumber + " Addess " + address + " Fee Paid " + feePaid
				+ " MemberId " + memberId + " Date of Join" + joinedDate.toString();

	}

	/**
	 * Checks whether the member is equal to the one supplied
	 * 
	 * @param object the member who should be compared
	 * @return true iff the member ids match
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Member other = (Member) object;
		if (memberId == null) {
			if (other.memberId != null) {
				return false;
			}
		} else if (!memberId.equals(other.memberId)) {
			return false;
		}
		return true;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(counter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		counter = (int) input.readObject();
	}

}
