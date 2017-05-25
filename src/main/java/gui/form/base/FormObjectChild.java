package gui.form.base;

/**
 * Interface for children of a FormObject
 * 
 * @author group11
 */
public interface FormObjectChild {

	/**
	 * @return next sibling of the FormObject
	 */
	FormObject getNextSibling();

	/**
	 * @return previous sibling of the FormObject
	 */
	FormObject getPreviousSibling();

	/**
	 * @return true if the FormObject has a next sibling, false otherwise
	 */
	boolean hasNextSibling();

	/**
	 * @return true if the FormObject has a previous sibling, false otherwise
	 */
	boolean hasPreviousSibling();

	/**
	 * @return parent
	 */
	FormObject getParent();
}
