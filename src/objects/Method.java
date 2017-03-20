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

	/**
	 * Returns the RealClass belonging to this Method
	 * 
	 * @return the RealClass belonging to this Method
	 */
	public RealClass getRealClass() {
		return realClass;
	}

	/**
	 * Sets the RealClass belonging to this Method
	 * 
	 * @param 	rc
	 * 			the RealClass to be set
	 */
	private void setRealClass(RealClass rc) {
		this.realClass = rc;
	}

	private RealClass realClass;

	@Override
	public void delete() {
		getRealClass().deleteChild(this);
	}
}
