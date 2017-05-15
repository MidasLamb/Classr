package gui.form.base;

import java.awt.Graphics;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;

/**
 * A class representing a MenuItem in a drop down menu of a MenuHeader
 */
public class MenuItem extends FormObject implements Displayable, Clickable {
	private MenuHeader menuHeader;
	private String name;
	private boolean enabled;

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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void onClick(MouseClick click) {
		this.getMenuHeader().getDropDownMenu().setEnabled(false);
		this.onAction();
	}

	@Override
	public
	void draw(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	protected void onAction() {
		// TODO actually do stuff
	}

	@Override
	public String getDisplayableString() {
		return this.getName();
	}

	@Override
	public void onClick(SingleClick click) {
		this.onClick(click);
	}

	@Override
	public void onDoubleClick(DoubleClick click) {
	}

	@Override
	public void onDragEnd(Drag drag) {
	}

	@Override
	public void onDragUpdate(Drag drag) {
	}

}
