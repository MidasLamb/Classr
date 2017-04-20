package gui.form.utility;

/**
 * Constraint that needs to be checked.
 */
public interface Checkable {
	/**
	 * @return true if the constraint is fulfilled, false otherwise
	 */
	public boolean check();

	/**
	 * Add a Checker to this Checkable
	 * 
	 * @param c
	 *            Checker to be added
	 */
	void addChecker(Checker c);
}
