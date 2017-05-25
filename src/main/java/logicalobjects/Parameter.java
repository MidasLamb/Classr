package logicalobjects;

import static main.Constants.REGEX_ALPHANUMERIC_UNDERSCORE;
import static main.Constants.REGEX_START_NO_CAPITAL;

/**
 * Parameter which can be added to methods
 */
public class Parameter extends LogicalObject {
	private String type;

	/**
	 * 
	 * @param name
	 *            The name of the parameter
	 * @param type
	 *            The type of the parameter
	 */
	public Parameter(String name, String type) {
		this.setName(name);
		this.setType(type);
	}

	@Override
	protected void onDelete() {
	}

	/**
	 * Returns the type of the parameter
	 * 
	 * @return the parameter type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * To set the type of the parameter
	 * 
	 * @param type
	 *            the new type of the parameter
	 */
	public final void setType(String type) {
		this.type = type;
		notifyUpdateListeners();
	}

	@Override
	public Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}

	@Override
	public boolean canHaveAsName(String name) {
		return name.matches(REGEX_START_NO_CAPITAL);
	}

	/**
	 * Returns whether or not this Parameter object can have the stated type
	 * string as its type name
	 * 
	 * @param type
	 *            String to be checked
	 * @return boolean indicating wheter or not this Parameter can have the
	 *         given String as type
	 */
	public boolean canHaveAsType(String type) {
		return type.matches(REGEX_ALPHANUMERIC_UNDERSCORE);
	}
}
