import java.io.Serializable;
import java.util.Calendar;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	// variables to help create the member object
	// private String firstName, lastName;
	private String name; // Do we should use name for member or firstName and lastName since his Member
							// class in Library is used only one string for name
	private String phoneNumber, address;
	private double feePaid;
	private String memberId;
	private static int counter = 0;

	private Calendar joinedDate;// Trung thinks we must have this variable since the first use case required to
								// initial the date of joining.

	// Member constructor
	public Member(String name, String phoneNumber, String address, double feePaid, String memberId,
			Calendar joinedDate) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.feePaid = feePaid;
		counter = counter + 1;
		this.memberId = "" + counter;
		this.joinedDate = joinedDate;

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

//	 /**
//     * Getter for firstName
//     * 
//     * @return String firstName
//     */
//	public String getFirstName() {
//		return firstName;
//	}
//	/**
//     * Setter for firstName
//     * 
//     * @param a String for new fistName
//     */
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	 /**
//     * Getter for lastName
//     * 
//     * @return string lastName
//     */
//	public String getLastName() {
//		return lastName;
//	}
//	/**
//     * Setter for lastName
//     * 
//     * @param a string for new lastName
//     */
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
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

}
