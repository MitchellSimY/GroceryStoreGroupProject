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

	// we can change these just a starting point...
	public static final int PRODUCT_NOT_FOUND = 1;
	public static final int PRODUCT_NOT_CHEKCED_OUT = 2;
	public static final int INSUFFICIENT_STOCK = 3;
	public static final int PROCUT_CHECKED_OUT = 4;
	public static final int ORDER_PLACED = 5;
	public static final int NO_ORDER_FOUND = 6;
	public static final int OPERATION_COMPLETED = 7;
	public static final int OPERATION_FAILED = 8;
	public static final int NO_SUCH_MEMBER = 9;

	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

}