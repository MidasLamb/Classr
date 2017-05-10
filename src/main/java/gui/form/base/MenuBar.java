package gui.form.base;

import java.awt.Graphics;
import java.util.ArrayList;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class that represents a menu bar on which clickable menuHeaders reside.
 */
public class MenuBar extends FormObject {
	ArrayList<MenuHeader> menuHeaders;

	public MenuBar(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setMenuHeaders(new ArrayList<MenuHeader>());
	}

	public void addMenuHeader(MenuHeader header) {
		this.menuHeaders.add(header);
		header.setMenuBar(this);
	}

	public void deleteMenuHeader(MenuHeader header) {
		header.setMenuBar(null);
		this.menuHeaders.remove(header);
	}

	public ArrayList<MenuHeader> getMenuHeaders() {
		return menuHeaders;
	}

	private void setMenuHeaders(ArrayList<MenuHeader> menuHeaders) {
		this.menuHeaders = menuHeaders;
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	@Override
	public
	void draw(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		for (MenuHeader header : this.getMenuHeaders()) {
			header.draw(g);
		}

	}

	@Override
	protected void onAction() {}

}
