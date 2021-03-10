/**
 * The DataTransfer class is used to facilitate data transfer between Library
 * and UserInterface. It is also used to support iterating over Member and Book
 * objects. The class stores copies of fields that may be sent in either
 * direction.
 * 
 * @author Brahma Dathan
 *
 */
public abstract class DataTransfer {
    private String memberId;
    private String memberName;
    private String memberAddress;
    private String memberPhone;
    private String feePaid;

    /**
     * This sets all fields to "none".
     */
    public DataTransfer() {
        reset();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    /**
     * Sets all the member-related fields using the Member parameter.
     * 
     * @param member the member whose fields should be copied.
     */
    public void setMemberFields(Member member) {
        memberId = member.getMemberId();
        memberName = member.getName();
        memberPhone = member.getPhoneNumber();
        memberAddress = member.getAddress();
        feePaid = member.getFeePaid();
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }
    
    public void setMemberFeePaid(String feePaid) {
    	this.feePaid = feePaid;
    }
    
    public String getMemberFeePaid() {
    	return feePaid;
    }


    /**
     * Sets all String fields to "none"
     */
    public void reset() {
        memberId = "none";
        memberName = "none";
        memberPhone = "none";
        memberAddress = "none";
        feePaid = "none";
    }
}
