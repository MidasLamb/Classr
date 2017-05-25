package gui.form.base;

/**
 * Interface for offering services to listen for changes
 * 
 * @author group11
 */
public interface ChangeSubject {

	/**
	 * Add a ChangeListener to this ChangeSubject
	 * 
	 * @param c
	 *            ChangeListener to add
	 */
	public void addChangeListener(ChangeListener c);

	/**
	 * Remove a ChangeListener from this ChangeSubject
	 * 
	 * @param c
	 *            ChangeListener to remove
	 */
	public void removeChangeListener(ChangeListener c);

	/**
	 * Notify the ChangeListeners for changes to this ChangeSubject
	 */
	void notifyChangeListeners();
}
