package barBuilder;

import gui.form.base.MenuBar;
import gui.form.base.MenuHeader;
import gui.form.base.MenuItem;

/**
 * A class that builds a MenuBar
 * 
 * @param <T>
 *            The class of which menubar to build.
 */
public abstract class BarBuilder<T extends MenuBar> {

	private T menuBar;
	private MenuHeader lastAddedHeader;

	/**
	 * The function you should implement to build a MenuBar.
	 */
	abstract void buildBar();

	/**
	 * Get the MenuBar of this BarBuilder
	 * 
	 * @return the MenuBar of this BarBuilder
	 */
	public T getMenuBar() {
		if (this.menuBar == null)
			this.buildBar();
		return this.menuBar;
	}

	/**
	 * Set the MenuBar of this BarBuilder
	 * 
	 * @param menuBar
	 *            menuBar to be set
	 */
	void setMenuBar(T menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * Set the last added MenuHeader to the given header
	 * 
	 * @param header
	 *            header to set as last added MenuHeader
	 */
	private void setLastAddedHeader(MenuHeader header) {
		this.lastAddedHeader = header;
	}

	/**
	 * Get the last added MenuHeader
	 * 
	 * @return last added MenuHeader
	 */
	private MenuHeader getLastAddedHeader() {
		return this.lastAddedHeader;
	}

	/**
	 * Add a MenuHeader and set it as last added MenuHeader
	 * 
	 * @param menuHeader
	 *            MenuHeader to add
	 */
	void addMenuHeader(MenuHeader menuHeader) {
		this.getMenuBar().addMenuHeader(menuHeader);
		this.setLastAddedHeader(menuHeader);
	}

	/**
	 * Add a MenuItem to the last added MenuHeader
	 * 
	 * @param item
	 *            MenuItem to add to the last added MenuHeader
	 */
	void addMenuItemToLastAddedHeader(MenuItem item) {
		if (this.getLastAddedHeader() != null) {
			this.lastAddedHeader.getDropDownMenu().addMenuItem(item);
		} else {
			throw new IllegalStateException("No MenuHeaders are added to this MenuBar.");
		}
	}

}
