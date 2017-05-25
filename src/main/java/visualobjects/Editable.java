package visualobjects;

/**
 * Interface for editable objects
 */
public interface Editable {

	/**
	 * Make the object editable
	 */
	public void setEditable();

	/**
	 * @return true if is editable, false otherwise
	 */
	public boolean isEditable();
}
