package gui.form.base;

import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class that represents a Header in a MenuBar
 *
 */
public class MenuHeader extends Button {
	private String name;
	private DropDownMenu<MenuItem> dropDownMenu;

	public MenuHeader(String name, int x, int y, int width, int height) {
		super(name, x, y, width, height);
		this.setDropDownMenu(new DropDownMenu<MenuItem>(x, y + this.getHeight(), width, 500));
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

	@Override
	public boolean isIn(int x, int y) {
		if (this.getDropDownMenu().isEnabled()) {
			return isInSelf(x, y) || isInDropDown(x, y);
		} else {
			return isInSelf(x, y);
		}
	}

	boolean isInSelf(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}

	boolean isInDropDown(int x, int y) {
		return this.getDropDownMenu().isIn(x, y);
	}

	@Override
	public void onClick(MouseClick click) {
		if (this.isInSelf(click.getX(), click.getY())) {
			onAction();
		} else if (this.isInDropDown(click.getX(), click.getY())) {
			this.getDropDownMenu().onClick(click);
		}
	}

	@Override
	public void draw(Graphics g) {
		getState().draw(g);
		this.getDropDownMenu().draw(g);
	}

	@Override
	protected void onAction() {
		this.getDropDownMenu().toggle();
	}

	protected boolean canBeEnabled() {
		return true;
	}

}
