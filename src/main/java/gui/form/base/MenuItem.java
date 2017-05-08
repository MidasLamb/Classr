package gui.form.base;

import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class representing a MenuItem in a drop down menu of a MenuHeader
 */
public class MenuItem extends FormObject {
	private MenuHeader menuHeader;
	private String name;

	public MenuItem(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public MenuHeader getMenuHeader() {
		return menuHeader;
	}

	public void setMenuHeader(MenuHeader menuHeader) {
		this.menuHeader = menuHeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onAction() {
		// TODO Auto-generated method stub

	}

}
