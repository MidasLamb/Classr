package gui.form.base;


import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class that represents a Header in a MenuBar
 *
 */
public class MenuHeader extends Button {
	private MenuBar menuBar;
	private String name;
	private DropDownMenu<MenuItem> dropDownMenu;

	public MenuHeader(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		this.setDropDownMenu(new DropDownMenu<MenuItem>(x,y+this.getHeight(),width,500));//TODO hoogte
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DropDownMenu<MenuItem> getDropDownMenu() {
		return dropDownMenu;
	}

	public void setDropDownMenu(DropDownMenu<MenuItem> dropDownMenu) {
		this.dropDownMenu = dropDownMenu;
	}

	boolean isIn(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}
	
	/**
	 * Checks if c is between a and b
	 */
	final static boolean isBetween(int a, int b, int c) {
		return a <= c && b >= c;
	}
	
	@Override
	public void onClick(MouseClick click) {
		onAction();

	}
	
	
	@Override
	public void draw(Graphics g) {
		getState().draw(g);
		this.getDropDownMenu().draw(g);
	}

	/*
	 * @Override void draw(Graphics g) { g.fillRect(this.getX(), this.getY(),
	 * this.getWidth(), this.getHeight()); for (MenuItem item :
	 * this.getMenuItems()) { item.draw(g); } }
	 */

	@Override
	protected void onAction() {
		this.getDropDownMenu().toggle();
		// draw listbox beneath with MenuItems
	}
	
	protected boolean canBeEnabled() {
		return true;
	}

}
