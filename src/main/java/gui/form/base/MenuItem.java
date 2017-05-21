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
public abstract class MenuItem extends FormObject implements Displayable {
	private DropDownMenu<MenuItem> dropDownMenu;
	private final String name;
	private MenuItemState state;

	/**
	 * Default constructor for a MenuItem. Creates a new MenuItem and sets its
	 * name and dimensions.
	 * 
	 * @param width
	 * @param height
	 */
	public MenuItem(String name, int width, int height) {
		super(0, 0, width, height);
		this.name = name;
		this.setState(new Enabled());
	}

	DropDownMenu<MenuItem> getDropDownMenu() {
		return dropDownMenu;
	}

	void setDropDownMenu(DropDownMenu<MenuItem> dropDownMenu) {
		this.dropDownMenu = dropDownMenu;
	}

	@Override
	public void onClick(MouseClick click) {
		this.getState().onAction();
	}

	@Override
	public void draw(Graphics g) {
		this.getState().draw(g);
	}

	@Override
	public String getDisplayableString() {
		return this.name;
	}

	public MenuItemState getState() {
		return state;
	}

	public void setState(MenuItemState state) {
		this.state = state;
	}
	
	private void setEnabled(boolean b){
		if (b){
			this.setState(new Enabled());
		}
		else {
			this.setState(new Disabled());
		};
	}

	public class Enabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.GREEN);
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, height - descent);
			g.setColor(c);
		}

		public void onAction() {
			getDropDownMenu().toggle();
			MenuItem.this.onAction();
		}
	}

	public class Disabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, height - descent);
			g.setColor(c);
		}

		public void onAction() {
		}

	}

	protected boolean canBeEnabled() {
		return true;
	}
	
	void updateState() {
		this.setEnabled(this.canBeEnabled());
	}

}
