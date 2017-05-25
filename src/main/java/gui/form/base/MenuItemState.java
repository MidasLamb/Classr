package gui.form.base;

/**
 * Indicates the state of a MenuItem
 *
 */
public abstract class MenuItemState extends State {

	/**
	 * Called when action on this MenuItem occurs
	 */
	public abstract void onAction();
}
