package logicalobjects;

import static main.Constants.REGEX_START_NO_CAPITAL;

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
		this.setName("newAttribute");
		this.setType("string");
	}


	@Override
	Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}
	
	@Override
	public boolean canHaveAsName(String name) {
		return name.matches(REGEX_START_NO_CAPITAL);
	}
}
