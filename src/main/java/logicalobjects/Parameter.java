package logicalobjects;

import static main.Constants.REGEX_START_NO_CAPITAL;

/**
 * Parameter which can be added to methodes
 * 
 * @author group11
 */
public class Parameter extends LogicalObject {
	private String type;
	
	/**
	 * 
	 * @param 	name
	 * 			The name of the parameter
	 * @param 	type
	 * 			The type of the parameter
	 */
	public Parameter(String name, String type){
		this.setName(name);
		this.setType(type);
	}

	@Override
	protected void onDelete() {}

	/**
	 * Returns the type of the parameter
	 * @return the parameter type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * To set the type of the parameter
	 * @param 	type
	 * 			the new type of the parameter
	 */
	public final void setType(String type) {
		this.type = type;
	}


	@Override
	public Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}
	
	@Override
	boolean hasValidName() {
		return this.getName().matches(REGEX_START_NO_CAPITAL);
	}
}
