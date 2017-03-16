package objects;

/**
 * A class of attributes, involving a real class.
 * 
 * @author team 11
 */
public class Attribute extends LogicalObject {
	
	/**
	 * Constructs a new Attribute belonging to the stated RealClass.
	 * 
	 * @param 	rc	
	 * 			The RealClass to which the Attribute belongs.
	 */
	public Attribute(RealClass rc) {
		this.setRealClass(rc);
	}

	/**
	 * Returns the RealClass belonging to this Attribute
	 * 
	 * @return the RealClass belonging to this Attribute
	 */
	public RealClass getRealClass() {
		return realClass;
	}

	/**
	 * Sets the RealClass belonging to this Attribute
	 * 
	 * @param 	rc
	 * 			the RealClass to be set
	 */
	private void setRealClass(RealClass rc) {
		this.realClass = rc;
	}

	private RealClass realClass;
}
