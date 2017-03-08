package objects;

/**
 * A class of logical objects, involving a real class
 * 
 * @author team 11
 */
public class Method extends LogicalObject {
	
	/**
	 * Constructs a new Method belonging to the stated RealClass.
	 * 
	 * @param 	rc	
	 * 			The RealClass to which the Method belongs.
	 */
	public Method(RealClass rc) {
		this.setRealClass(rc);
	}

	public RealClass getRealClass() {
		return realClass;
	}

	private void setRealClass(RealClass rc) {
		this.realClass = rc;
	}

	private RealClass realClass;
}
