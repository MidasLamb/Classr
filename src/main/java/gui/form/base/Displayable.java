package gui.form.base;

/**
 * Interface for displaying a textual representation of an object to put in a
 * ListBox
 * 
 * @author group11
 */
public interface Displayable {

	/**
	 * @return textual representation
	 */
	public String getDisplayableString();

	/**
	 * @return height of the textual representation
	 */
	public int getHeight();

	/**
	 * @return width of the textual representation
	 */
	public int getWidth();
}
