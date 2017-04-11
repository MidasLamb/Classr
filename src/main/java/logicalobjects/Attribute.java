package logicalobjects;

import interfaces.LogicalObjectVisitor;

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
	public Attribute(LogicalClass rc) {
		super(rc);
		this.setName("New attribute");
	}


	@Override
	public Object accept(LogicalObjectVisitor v) {
		return v.visit(this);
	}
}
