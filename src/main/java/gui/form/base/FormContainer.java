package gui.form.base;

/**
 * Container of a Form
 * 
 * @author group11
 *
 * @param <T>
 *            class of the Form in this FormContainer
 */
public interface FormContainer<T extends Form> {

	/**
	 * Close the FormContainer
	 */
	public void close();

	/**
	 * Switch to the given Form
	 * 
	 * @param f
	 *            Form to be switched to
	 */
	public void switchTo(T f);

	/**
	 * Get an extra container
	 * 
	 * @return an extra container
	 */
	public FormContainer<T> getExtraContainer();
}
