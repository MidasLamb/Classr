package objects;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public abstract class LogicalObject {

	private String name;

	/**
	 * Returns the name of this LogicalObject
	 * 
	 * @return the name of this LogicalObject
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this LogicalObject
	 * 
	 * @param name
	 *            the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public abstract void delete();
}
