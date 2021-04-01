package business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Member Class.
 * 
 * This class is designed to create member objects for later use. 
 * Once an object (member) is created, the members can purchase items
 * along with other functionality.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 */

import business.entities.iterators.FilteredIterator;

/**
 * Member represents a member of the groceryStore.
 * 
 * @author Mitchell Young, Jack Haben, Trung Pham, Kou Yang
 *
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	// variables to help create the member object
	private String name, phoneNumber, address, memberId;
	private double feePaid;
	private static int counter = 0;
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
	 * @return transactionIndex: returns the current index of the current
	 *         transaction in the transaction list.
	 */
	public int checkOut(Product product, Calendar dateOfTransaction, int checkOutQty) {
		int transactionIndex = 0;
		Transaction tempTransaction = new Transaction();
		tempTransaction.setDate(dateOfTransaction);
		// System.out.println(tempTransaction.getDate());
		if (transactions.isEmpty() == false) {
			// System.out.println("In if");//TODO: DELETE AFTER DEBUG
			for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
				Transaction currentTransaction = (Transaction) iterator.next();
				// System.out.println(currentTransaction.equals(tempTransaction));//TODO: DELETE
				// AFTER DEBUG > CHECK HOW MANY TRANSACTIONS IN LIST
				if (currentTransaction.equals(tempTransaction)) {
					transactions.get(transactionIndex).getCheckOutProductList().insertProduct(product, checkOutQty);
					// System.out.println(transactions.get(transactionIndex).toString()); //TODO:
					// DELETE AFTER DEBUG
					// System.out.println(transactionIndex);//TODO: DELETE AFTER DEBUG
					return transactionIndex;
				}
				// System.out.println("Adding 1 to transaction Index");//TODO: DELETE AFTER
				// DEBUG
				transactionIndex++;
			}
		} else {
			// System.out.println("In else");//TODO: DELETE AFTER DEBUG
			transactions.add(new Transaction());
			transactions.get(transactionIndex).getCheckOutProductList().insertProduct(product, checkOutQty);
			// System.out.println(transactions.get(transactionIndex).toString()); //TODO:
			// DELETE AFTER DEBUG
		}
		// System.out.println(transactionIndex); //TODO: DELETE AFTER DEBUG
		return transactionIndex;
	}

	/**
	 * Gets an iterator to a collection of selected transactions
	 * 
	 * @param startDate The startDate for which transactions are being sought
	 *                  (inclusive)
	 * @param endDate   The endDate for which transactions are being sought
	 *                  (inclusive)
	 * @return the iterator to the collection
	 */
	public Iterator<Transaction> getTransactionsDateRange(Calendar startDate, Calendar endDate) {
		return new FilteredIterator(transactions.iterator(),
				transaction -> transaction.inDateRange(startDate, endDate));
	}

	/**
	 * Returns the list of all transactions for this member.
	 * 
	 * @return the iterator to the list of Transaction objects
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	// Return exact object copy of transactions list
	public List<Transaction> getTransactionsList() {
		return transactions;
	}

	public boolean insertTransaction(Transaction transaction) {
		transactions.add(transaction);
		return true;
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

	public String getJoinedDateByString() {
		return "" + joinedDate.get(Calendar.MONTH) + "/" + joinedDate.get(Calendar.DAY_OF_MONTH) + "/"
				+ joinedDate.get(Calendar.YEAR);
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
