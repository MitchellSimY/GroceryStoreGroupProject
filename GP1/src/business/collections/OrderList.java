package business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import business.entities.Order;

public class OrderList implements Iterable<Order>, Serializable{
	private List<Order> orders = new LinkedList<Order>();
	
    /**
     * Adds an Order object to the list.
     * 
     * @param hold the Hold object to be added
     */
    public boolean addHold(Order newOrder) {
    	orders.add(newOrder);
        return true;
    }

    @Override
    public Iterator<Order> iterator() {
        return orders.iterator();
    }
}
