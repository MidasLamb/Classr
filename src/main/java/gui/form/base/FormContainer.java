package gui.form.base;

/**
 * Container of a Form
 * 
 * @author group11
 *
 * @param <T>
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
	 */
	public void switchTo(T f);

	/**
	 * Get an extra container of this FormContainer
	 * 
	 * @return
	 */
	public FormContainer<T> getExtraContainer();
}
