package gui.form.base;

/**
 * Interface for FormObjects that have children
 * 
 * @author group11
 */
public interface FormObjectWithChildren {

	/**
	 * @return the first child
	 */
	FormObject getFirstChild();

	/**
	 * @return the last child
	 */
	FormObject getLastChild();

	/**
	 * @return true if this FormObject has children, false otherwise
	 */
	boolean hasChildren();

	/**
	 * A function to return a focused child after clicking on this FormObject.
	 * 
	 * @return The child to focus after clicking, null if no such child exists.
	 */
	FormObject getClickedChild();

	/**
	 * Unfocuses all the children.
	 */
	void unfocusChildren();

	/**
	 * A function to return a focused child.
	 * 
	 * @return The child that is focused, null if so such child exists.
	 */
	FormObject getFocusedChild();
}
