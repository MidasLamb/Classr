package gui.form.utility;

/**
 * Checks if the conditions of its Checkable objects are fulfilled.
 */
public interface Checker {

	/**
	 * Add a new Checkable constraint to this Checker
	 * 
	 * @param c
	 *            Checkable to be added.
	 */
	public void addCheckableConstraint(Checkable c);

	/**
	 * Check if all constraints are fulfilled.
	 */
	public void checkConstraints();
}
