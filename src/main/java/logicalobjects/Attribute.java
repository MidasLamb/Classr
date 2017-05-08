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
		String originalName = "newAttribute";
		String name = originalName;
		if (getRealClass().attributeNameAlreadyExists(name, this)) {
			int i = 1;
			do {
				name = originalName + i;
				i++;
			} while (getRealClass().attributeNameAlreadyExists(name, this));
		}
		this.setName(name);
		this.setType("string");
	}


	@Override
	Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}
	
	@Override
	public boolean canHaveAsName(String name) {
		return (name.matches(REGEX_START_NO_CAPITAL) && !getRealClass().attributeNameAlreadyExists(name, this));
	}
}
