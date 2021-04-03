package business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import business.entities.Product;

public class CheckOutProductList implements Iterable<Product>, Serializable{
		private static final long serialVersionUID = 1L;
		private List<Product> products = new LinkedList<Product>();

		/**
		 * Checks whether a product with a given product id exists in products list.
		 * 
		 * @param productId: of type String. The ID of the product to be searched.
		 * @return product: returns the product whose productID matches the input if it exists.
		 * @return null: returns null if the product doesn't exist.
		 * 
		 */
		public Product search(String productId) {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getProductId().equals(productId)) {
					return product;
				}
			}
			return null;
		}

		/**
		 * Checks whether a product with a given product name exists.
		 * 
		 * @param name: of type String. Name of the product to be searched.
		 * @return product: returns the product whose productID matches the input if it exists.
		 * @return null: returns null if the product doesn't exist.
		 */
		public Product searchProductName(String name) {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getProductName().equals(name)) {
					return product;
				}
			}
			return null;
		}

		/**
		 * Removes a product from the products list.
		 * 
		 * @param productId: of type String. ProductId of the product to be removed.
		 * @return true: iff the product exists in the list and was removed.
		 * @return false: iff the product doesn't exist in the list.
		 */
		public boolean removeProduct(String productId) {
			Product product = search(productId);
			if (product == null) {
				return false;
			} else {
				return products.remove(product);
			}
		}

		/**
		 * Inserts a product into the collection
		 * 
		 * @param product the product to be inserted
		 * @return true iff the product could be inserted. Currently always true
		 */
		public boolean insertProduct(Product product, int checkOutQty) {
			Product tempProduct = new Product(product.getProductName(), product.getProductId(), product.getStockInHand()
					, product.getReorderLevel(), product.getCurrentPrice());
			tempProduct.setCheckoutQty(checkOutQty);
			products.add(tempProduct);
			return true;
		}

		/**
		 * Returns an Iterator object of the product list.
		 * 
		 * @return Iterator<Product>: of type Iterator. An iterator of the list of products.
		 */
		public Iterator<Product> iterator() {
			return products.iterator();
		}

		/**
		 * Returns the product list object itself.
		 * 
		 * @return List<Product>: of type List. Returns the product list.
		 */
		public List<Product> getProductList() {
			return products;
		}

		public String toString() {
			return products.toString();
		}
}
