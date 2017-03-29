package objects;

/**
 * A class of attributes, involving a real class.
 * 
 * @author team 11
 */
public class Attribute extends ClassContent {

	/**
	 * Constructs a new Attribute belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the Attribute belongs.
	 */
	public Attribute(RealClass rc) {
		super(rc);
	}
	
	public void accept(RealClassVisitor rcv){
		rcv.visitAttribute(this);
	}
}
