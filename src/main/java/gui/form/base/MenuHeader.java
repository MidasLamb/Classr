package gui.form.base;

import java.awt.Graphics;
import java.util.ArrayList;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class that represents a Header in a MenuBar
 *
 */
public class MenuHeader extends Button {
	private MenuBar menuBar;
	private ArrayList<MenuItem> menuItems;
	private String name;
	private boolean droppedDown;

	

	public MenuHeader(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		this.setMenuItems(new ArrayList<MenuItem>());
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(MenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public void addMenuItem(MenuItem item) {
		this.menuItems.add(item);
		item.setMenuHeader(this);
	}

	public void deleteMenuItem(MenuItem item) {
		item.setMenuHeader(null);
		this.menuItems.remove(item);
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isDroppedDown() {
		return droppedDown;
	}

	public void setDroppedDown(boolean droppedDown) {
		this.droppedDown = droppedDown;
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	/*@Override
	void draw(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		for (MenuItem item : this.getMenuItems()) {
			item.draw(g);
		}
	}*/

	@Override
	protected void onAction() {
		// TODO Auto-generated method stub
		// dropped down true
		// draw listbox beneath with MenuItems

	}

}
