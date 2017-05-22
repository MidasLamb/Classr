package barBuilder;

import gui.form.base.MenuBar;
import gui.form.base.MenuHeader;
import gui.form.base.MenuItem;

/**
 * A class that builds a MenuBar
 * 
 * @param <T>
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
	
	private void setLastAddedHeader(MenuHeader header) {
		this.lastAddedHeader = header;
	}
	
	private MenuHeader getLastAddedHeader() {
		return this.lastAddedHeader;
	}
	
	void addMenuHeader(MenuHeader menuHeader) {
		this.getMenuBar().addMenuHeader(menuHeader);
		this.setLastAddedHeader(menuHeader);
	}
	
	void addMenuItemToLastAddedHeader(MenuItem item) {
		if (this.getLastAddedHeader() != null){
			this.lastAddedHeader.getDropDownMenu().addMenuItem(item);
		} else {
			throw new IllegalStateException("No MenuHeaders are added to this MenuBar.");
		}
	}

}
