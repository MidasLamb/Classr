package logicalobjects;

import java.text.AttributedString;

import interfaces.LogicalObjectDeleteVisitor;
import interfaces.LogicalObjectStringVisitor;
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
	public void accept(LogicalObjectDeleteVisitor v) {
		v.visit(this);	
	}

	@Override
	public AttributedString accept(LogicalObjectStringVisitor v) {
		return v.visit(this);
	}
	

	@Override
	public Object accept(LogicalObjectVisitor v) {
		return v.visit(this);
	}
}
