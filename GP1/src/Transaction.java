import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class Transaction implements Serializable {
	// Variables for Transaction
	private Member member;
	private List<Product> products = new LinkedList<Product>();
	private Calendar date;

	public Transaction(Member member, List<Product> products, Calendar date) {
		this.member = member;
		this.products = products;
		this.date = date;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

}
