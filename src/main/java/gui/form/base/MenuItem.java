package gui.form.base;

import static gui.form.base.Constants.STANDARD_FONTMETRICS;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class representing a MenuItem in a drop down menu of a MenuHeader
 */
public abstract class MenuItem extends FormObject implements Displayable {
	private final String name;
	private MenuItemState state;

	/**
	 * Default constructor for a MenuItem. Creates a new MenuItem and sets its
	 * name and dimensions.
	 * 
	 * @param width
	 *            the width of this item
	 * @param name
	 *            the name to display on the item.
	 */
	public MenuItem(String name, int width) {
		super(0, 0, width, STANDARD_FONTMETRICS.getHeight());
		this.name = name;
		this.setState(new Enabled());
	}

	@Override
	public void onClick(MouseClick click) {
		this.getState().onAction();
	}

	@Override
	public void draw(Graphics g) {
		this.getState().draw(g);
	}

	/**
	 * Determines the height of this MenuItem from the stated Graphics
	 * 
	 * @param g
	 *            The graphics object.
	 */
	public void determineHeight(Graphics g) {
		height = g.getFontMetrics().getHeight();
	}

	@Override
	public String getDisplayableString() {
		return this.name;
	}

	/**
	 * Get the state of this MenuItem
	 * 
	 * @return state of this MenuItem
	 */
	private MenuItemState getState() {
		return state;
	}

	/**
	 * Set the state of this MenuItem
	 * 
	 * @param state
	 *            MenuItemState to set
	 */
	private void setState(MenuItemState state) {
		this.state = state;
	}

	/**
	 * Enable or disable this MenuItem
	 * 
	 * @param b
	 *            boolean that indicates the state to set
	 */
	private void setEnabled(boolean b) {
		if (b) {
			this.setState(new Enabled());
		} else {
			this.setState(new Disabled());
		}
		;
	}

	/**
	 * The enabled state of a MenuItem
	 */
	private class Enabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, getHeight() - descent);
			g.setColor(c);
		}

		@Override
		public void onAction() {
			MenuItem.this.onAction();
		}
	}

	/**
	 * The disabled state of a MenuItem
	 */
	private class Disabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.LIGHT_GRAY);
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, getHeight() - descent);
			g.setColor(c);
		}

		@Override
		public void onAction() {
		}

	}

	/**
	 * @return true if this MenuItem can be enabled, false otherwise
	 */
	protected boolean canBeEnabled() {
		return true;
	}

	/**
	 * Update the state of this MenuItem
	 */
	void updateState() {
		this.setEnabled(this.canBeEnabled());
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	@Override
	public int getWidth() {
		return STANDARD_FONTMETRICS.stringWidth(getDisplayableString());
	}

}
