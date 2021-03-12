package business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import business.entities.Order;
public class OrderList implements Iterable<Order>, Serializable{
	private List<Order> orders = new LinkedList<Order>();

	@Override
	public Iterator<Order> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
