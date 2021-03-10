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
	
	private MemberList members = new MemberList();
	private static GroceryStore groceryStore;
	
	
	// ====== Start Member List Class ========
	private class MemberList implements Iterable<Member>, Serializable {
		private List<Member> members = new LinkedList <Member>();

		// Adding members method.
		public boolean insertMember(Member member) {
			members.add(member);
			return true;
		}
		
		// Showing members to see if it was added successfully. Can delete this method.
		public void showMembers() {
			
		}
		
		
		@Override
		public Iterator<Member> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
	// ====== End of Member List Class ========
	
	public void addMember(Request request) {
		Member member = new Member(request.getMemberName(), request.getMemberAddress(), request.getMemberPhone());
		members.insertMember(member);
	}
	
	public void showMembers() {
		
	}

}
