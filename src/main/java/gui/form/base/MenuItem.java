package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;

/**
 * A class representing a MenuItem in a drop down menu of a MenuHeader
 */
// TODO WRONG!
public abstract class MenuItem extends FormObject implements Displayable {
	private DropDownMenu<MenuItem> dropDownMenu;
	private final String name;

	public MenuItem(String name, int width, int height) {
		super(0, 0, width, height);
		this.name = name;
	}

	DropDownMenu<MenuItem> getDropDownMenu() {
		return dropDownMenu;
	}

	void setDropDownMenu(DropDownMenu<MenuItem> dropDownMenu) {
		this.dropDownMenu = dropDownMenu;
	}

	@Override
	public void onClick(MouseClick click) {
		this.getDropDownMenu().toggle();
		this.onAction();
	}

	@Override
	public void draw(Graphics g) {

		Color c = g.getColor();
		if (this.isFocused())
			g.setColor(Color.BLUE);
		height = g.getFontMetrics().getHeight();
		int descent = g.getFontMetrics().getDescent();
		g.drawString(getDisplayableString(), 0, height - descent);
		g.setColor(c);
	}

	@Override
	public String getDisplayableString() {
		return this.name;
	}

	@Override
	public boolean isIn(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}

}
