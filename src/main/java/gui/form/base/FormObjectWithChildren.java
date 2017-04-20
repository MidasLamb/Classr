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
}
