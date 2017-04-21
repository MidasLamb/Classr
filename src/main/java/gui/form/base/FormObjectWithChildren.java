package gui.form.base;

public interface FormObjectWithChildren {
	FormObject getFirstChild();
	FormObject getLastChild();
	boolean hasChildren();
	/**
	 * A function to return a focused child after clicking on this FormObject.
	 * @return The child to focus after clicking, null if no such child exists.
	 */
	FormObject getClickedChild();
	
	/**
	 * Unfocuses all the children.
	 */
	void unfocusChildren();
	
	/**
	 * A function to return a focused child.
	 * @return The child that is focused, null if so such child exists.
	 */
	FormObject getFocusedChild();
}
