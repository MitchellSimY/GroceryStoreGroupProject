package business.facade;

/**
 * This class is used for returning many of the results of the library system's
 * business logic to user interface.
 * 
 * At present, the Result object returns an int code,plus values of selected
 * fields of Product and Member. They are the product name, id, member id,
 * member name, member phone, and member id.
 * 
 * @author group
 */
public class Result extends DataTransfer {

	// here are a few variable we may need. Feel free to add more. -jack
	public static final int PRODUCT_NOT_FOUND = 1;
	public static final int CHANGE_ME_1 = 2;// this can be something else
	public static final int INSUFFICIENT_STOCK = 3;
	public static final int CHANGE_ME_2 = 4; // this can be something else
	public static final int ORDER_PLACED = 5; // maybe we will use this for order
	public static final int NO_ORDER_FOUND = 6;// maybe we will use this for order
	public static final int OPERATION_COMPLETED = 7;
	public static final int OPERATION_FAILED = 8;
	public static final int NO_SUCH_MEMBER = 9;
	public static final int PRODUCTID_EXISTS = 10;
	public static final int PRODUCT_NAME_EXISTS = 11;
	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}