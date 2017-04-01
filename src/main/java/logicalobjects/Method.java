package logicalobjects;

import interfaces.LogicalObjectVisitor;

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
	public Method(LogicalClass rc) {
		super(rc);
	}
	
	@Override
	public void accept(LogicalObjectVisitor v) {
		v.visit(this);	
	}
}
