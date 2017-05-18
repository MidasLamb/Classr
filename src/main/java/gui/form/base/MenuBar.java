package gui.form.base;import java.awt.Graphics;import java.util.ArrayList;import gui.inputHandlers.clicks.MouseClick;/** * A class that represents a menu bar on which clickable menuHeaders reside. */public class MenuBar extends FormObject {	ArrayList<MenuHeader> menuHeaders;	/**	 * Default constructor for a MenuBar. Creates a new MenuBar and sets its	 * coordinates and dimensions and initializes its list of MenuHeaders.	 * 	 * @param x	 * @param y	 * @param width	 * @param height	 */	public MenuBar(int x, int y, int width, int height) {		super(x, y, width, height);		this.setMenuHeaders(new ArrayList<MenuHeader>());	}	/**	 * Adds a MenuHeader to its MenuHeaders.	 * 	 * @param header	 */	public void addMenuHeader(MenuHeader header) {		this.menuHeaders.add(header);		updateEnabled();	}	/**	 * Deletes a MenuHeader from its headers.	 * 	 * @param header	 */	public void deleteMenuHeader(MenuHeader header) {		this.menuHeaders.remove(header);	}	/**	 * Returns the list of MenuHeaders.	 * 	 * @return	 */	public ArrayList<MenuHeader> getMenuHeaders() {		return menuHeaders;	}	/**	 * Sets the list of MenuHeaders.	 * 	 * @param menuHeaders	 */	private void setMenuHeaders(ArrayList<MenuHeader> menuHeaders) {		this.menuHeaders = menuHeaders;	}	@Override	public boolean isIn(int x, int y) {		return isInSelf(x, y) || isInContent(x, y);	}	/**	 * Returns whether or not a coordinate at x,y is in this MenuBar itself.	 * 	 * @param x	 * @param y	 */	boolean isInSelf(int x, int y) {		return isBetween(this.getX(), this.getX() + this.getWidth(), x)				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);	}	/**	 * Returns whether or not a coordinate at x,y is in content of this MenuBar.	 * 	 * @param x	 * @param y	 */	boolean isInContent(int x, int y) {		for (MenuHeader header : this.getMenuHeaders()) {			if (header.isIn(x, y)) {				return true;			}		}		return false;	}	@Override	public void onClick(MouseClick click) {		for (MenuHeader header : this.getMenuHeaders()) {			if (header.isIn(click.getX(), click.getY())) {				header.onClick(click);			}		}	}	@Override	public void draw(Graphics g) {		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());		for (MenuHeader header : this.getMenuHeaders()) {			header.draw(g);		}	}	@Override	protected void onAction() {	}	/**	 * Updates the enabled state of its MenuHeaders.	 */	public void updateEnabled() {		for (MenuHeader header : this.getMenuHeaders()) {			header.setEnabled(header.canBeEnabled());		}	}}