import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;




/**
 * Mitch: I made memberList and productList as their own classes when this 
 * was first created. I ended up understanding the basic concepts
 * of singletons and I've since deleted those classes.
 * 
 * If im correct they'll both go in here as Singletons.
 *
 */
public class GroceryStore implements Serializable {
	
    private static final long serialVersionUID = 1L;
	private MemberList members = new MemberList();
	private static GroceryStore groceryStore;
	
	// Singleton constructor
	private GroceryStore() {
	}
	
	public static GroceryStore instance() {
        if (groceryStore == null) {
            return groceryStore = new GroceryStore();
        } else {
            return groceryStore;
        }
	}
	
	/**
	 * Line "Result result = groceryStore.addMember(Request.instance());" 
	 * in Interface.java is not making it to this method
	 * 
	 */
	public Result addMember(Request request) {	

		System.out.println("Reached add member within grocery store");
		Result result = new Result();
		Member member = new Member(request.getMemberName(), request.getMemberPhone(), request.getMemberAddress());
		members.insertMember(member);
        if (members.insertMember(member)) {
            result.setResultCode(Result.OPERATION_COMPLETED);
            result.setMemberFields(member);
            return result;
        }
        result.setResultCode(Result.OPERATION_FAILED);
        return result;
	}
	
	
	
	// ====== Start Member List Class ========
	private class MemberList implements Serializable {
        private static final long serialVersionUID = 1L;
		private List<Member> members = new LinkedList <Member>();

		// Adding members method.
		public boolean insertMember(Member member) {
			members.add(member);
			return true;
		}
		
		// Showing members to see if it was added successfully. Can delete this method.
		public void showMembers() {
			
		}
		
		

		
		
	}
	// ====== End of Member List Class ========
	
	public void testMethod() {
		System.out.println("Hello there!");
	}
	
	// TODO
	public void showMembers() {
		
	}
    /**
     * String form of the library
     * 
     */
    @Override
    public String toString() {
        return "\n" + members;
    }
}
