
public class Member {
	
	// variables to help create the member object
	private String firstName, lastName;
	private String phoneNumber, address;
	private double feePaid;
	private int memberId;
	private static int counter = 0;
	
	// Member constructor
	public Member(String firstName, String lastName, String phoneNumber, String address, double feePaid, int memberId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.feePaid = feePaid;
		counter = counter + 1;
		this.memberId = counter;
	}
	
	
	
	
	
}
