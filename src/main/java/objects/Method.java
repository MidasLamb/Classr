package objects;

/**
 * A class of logical objects, involving a real class
 * 
 * @author team 11
 */
public class Method extends ClassContent {

	/**
	 * Constructs a new Method belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the Method belongs.
	 */
	public Method(RealClass rc) {
		super(rc);
	}
	
	/**
	 * Accepts a RealClassVisitor and calls the correct concrete visitor
	 * 
	 * @param 	rcv
	 * 			the RealClassVisitor to accept
	 */
	public void accept(RealClassVisitor rcv){
		rcv.visitMethod(this);
	}
}
