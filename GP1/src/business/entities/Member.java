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
	private String name, phoneNumber, address, memberId;
	private double feePaid;
	private static int counter = 0;
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private Calendar joinedDate;

	/**
	 * Creates a single member object.
	 * 
	 * @param name: of type String. Name of the member
	 * @param address:		address of the member
	 * @param phoneNumber:	phone number of the member
	 * @param feePaid:		how much fee have they paid
	 * @param joinedData:	date joined
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
	 * @param product: of type Product. The product to be checked out
	 * @param dateOfTransaction: of type Calendar. The date the checkout is occurring on.
	 * @param checkOutQty: of type Int. The quantity being checked out.
	 * @return transactionIndex: of type Int. Returns the current index of the current transaction in the member's transaction list.
	 */
	public int checkOut(Product product, Calendar dateOfTransaction, int checkOutQty) {
		int transactionIndex = 0;
		Transaction tempTransaction = new Transaction();
		tempTransaction.setDate(dateOfTransaction);
		if (transactions.isEmpty() == false) {
			for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext();) {
				Transaction currentTransaction = (Transaction) iterator.next();
				if (currentTransaction.equals(tempTransaction)) {
					transactions.get(transactionIndex).getCheckOutProductList().insertProduct(product, checkOutQty);
					return transactionIndex; 
				}
				transactionIndex++;
			}
		} else {
			transactions.add(new Transaction());
			transactions.get(transactionIndex).getCheckOutProductList().insertProduct(product, checkOutQty);
		}
		return transactionIndex;
	}

	/**
	 * Retrieves an Iterator of transactions whose transaction date falls between two given dates.
	 * 
	 * @param startDate: The startDate for which transactions are being sought
	 *                  (inclusive)
	 * @param endDate:   The endDate for which transactions are being sought
	 *                  (inclusive)
	 * @return Iterator<Transaction>: The iterator of the list of Transaction objects for the member within the two dates given.
	 */
	public Iterator<Transaction> getTransactionsDateRange(Calendar startDate, Calendar endDate) {
		return new FilteredIterator(transactions.iterator(),
				transaction -> transaction.inDateRange(startDate, endDate));
	}

	/**
	 * Returns an Iterator object of the member's transaction list.
	 * 
	 * @return Iterator<Transaction>: of type Iterator. An iterator of the list of Transaction objects for the member.
	 */
	public Iterator<Transaction> getTransactions() {
		return transactions.iterator();
	}

	/**
	 * Returns the transaction list object of the member.
	 * 
	 * @return List<Transaction>: of type List. The List object of Transaction objects for a member.
	 */
	public List<Transaction> getTransactionsList() {
		return transactions;
	}

	/**
	 * Adds a transaction object into the member's transaction list.
	 * 
	 * @return true: iff the transaction was successfully added to the list. Currently always true.
	 */
	public boolean insertTransaction(Transaction transaction) {
		transactions.add(transaction);
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public double getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(double feePaid) {
		this.feePaid = feePaid;
	}

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
