import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	// variables to help create the member object
	private String name;
	private String phoneNumber, address;
	private double feePaid;
	private String memberId;
	private static final String MEMBER_STRING = "M";
	private static int idCounter;
	private List<Transaction> transactions = new LinkedList<Transaction>();
	private Calendar joinedDate;

	// Member constructor
	public Member(String name, String phoneNumber, String address, double feePaid) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.feePaid = feePaid;
		this.memberId = MEMBER_STRING + ++idCounter;
		this.joinedDate = new GregorianCalendar();

	}

	public Member(String name, String phoneNumber, String address) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.feePaid = 0;
		this.memberId = MEMBER_STRING + ++idCounter;
		this.joinedDate = new GregorianCalendar();

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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Calendar getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Calendar joinedDate) {
		this.joinedDate = joinedDate;
	}

}
