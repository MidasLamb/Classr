package gui.form.base;

/**
 * Interface for listening to changes
 * 
 * @author group11
 */
public interface ChangeListener {

	/**
	 * Notify changes to ChangeListener
	 * 
	 * @param c
	 *            subject that is changed
	 */
	public void getNotifiedOfChange(ChangeSubject c);
}
