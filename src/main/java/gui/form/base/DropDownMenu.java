package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.form.base.ListBox;
import gui.form.base.ListBox.ListBoxElement;
import gui.inputHandlers.clicks.MouseClick;

/**
 * A class of DropDownMenus, extending a listbox, but being able to be disabled.
 *
 * @param <T>
 */
public class DropDownMenu<T extends Displayable> extends ListBox<MenuItem> {
	private boolean enabled;
	private DropDownMenuState state;

	/**
	 * Default constructor for a DropDownMenu. Creates a new DropDownMenu and
	 * sets its coordinates and dimensions and initializes its State and lsit of
	 * MenuItems.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
		// this.menuItems = new ArrayList<MenuItem>();
		this.setState(false);
		this.setElements(new ArrayList<ListBoxElement<MenuItem>>());
	}

	DropDownMenuState getState() {
		return state;
	}

	private void setState(boolean bool) {
		if (bool) {
			setState(new Enabled());
		} else {
			setState(new Disabled());
		}
	}

	private void setState(DropDownMenuState state) {
		this.state = state;
	}

	/**
	 * Adds a MenuItem to its list of MenuItems.
	 * 
	 * @param item
	 */
	public void addMenuItem(MenuItem item) {
		this.addElement(item);
		item.setDropDownMenu((DropDownMenu<MenuItem>) this);
	}

	/**
	 * Deletes a MenuItem from its list of MenuItems.
	 * 
	 * @param item
	 */
	void deleteMenuItem(MenuItem item) {
		item.setDropDownMenu(null);
		this.removeElement(item);
	}

	boolean isEnabled() {
		return enabled;
	}

	private void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Inverses the enabled state of this DropDownMenu
	 */
	void toggle() {
		this.setEnabled(!this.isEnabled());
		this.setState(this.isEnabled());
	}

	@Override
	public void onClick(MouseClick click) {
		getState().onClick(click);
	}

	@Override
	public void draw(Graphics g) {
		getState().draw(g);
	}

	@Override
	protected void onAction() {
	}

	@Override
	public boolean isIn(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}

	/**
	 * A class representing that a DropDownMenu is enabled
	 */
	private class Enabled extends DropDownMenuState {

		@Override
		void onClick(MouseClick click) {
			int x = getX();
			int y = getY();
			int sumOfVerticalMovement = 0;
			for (ListBoxElement<MenuItem> e : getListboxElements()) {
				e.height = e.getObject().getHeight();
				boolean isin = isInElement(e, click.getX(), click.getY(), x, y + sumOfVerticalMovement);
				if (isin) {
					e.getObject().onClick(click);
					return;
				}
				sumOfVerticalMovement += e.getObject().getHeight();
			}
		}

		@Override
		void draw(Graphics g) {
			int translatedX = getX();
			int translatedY = getY();
			
			g.translate(translatedX, translatedY);
			
			int boxHeight = 0;
			
			for (ListBoxElement<MenuItem> e : getListboxElements()){
				e.getObject().determineHeight(g);
				e.height = e.getObject().getHeight();
				boxHeight += e.getObject().getHeight();
			}
			g.drawRect(0, 0, getWidth(), boxHeight);
			Color c = g.getColor();
			g.setColor(Color.white);
			g.fillRect(1, 1, getWidth() - 1, boxHeight - 1);
			g.setColor(c);
			
			
			int sumOfVerticalTranslations = 0;
			
			
			for (ListBoxElement<MenuItem> e : getListboxElements()){
				e.getObject().draw(g);
				sumOfVerticalTranslations += e.getObject().getHeight();
				g.translate(0, e.getObject().getHeight());
			}
			g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
		}

	}

	/**
	 * A class representing that a DropDownMenu is disabled.
	 */
	private class Disabled extends DropDownMenuState {

		@Override
		void onClick(MouseClick click) {
		}

		@Override
		void draw(Graphics g) {
		}

	}

}
