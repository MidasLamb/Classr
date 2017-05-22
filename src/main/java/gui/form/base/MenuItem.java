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
	 * @param height
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
	 */
	public void determineHeight(Graphics g) {
		height = g.getFontMetrics().getHeight();
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

	private void setEnabled(boolean b) {
		if (b) {
			this.setState(new Enabled());
		} else {
			this.setState(new Disabled());
		}
		;
	}

	public class Enabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.BLACK);
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, getHeight() - descent);
			g.setColor(c);
		}

		public void onAction() {
			MenuItem.this.onAction();
		}
	}

	public class Disabled extends MenuItemState {

		@Override
		void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.LIGHT_GRAY);
			int descent = g.getFontMetrics().getDescent();
			g.drawString(getDisplayableString(), 0, getHeight() - descent);
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
	
	@Override
	public int getHeight(){
		return super.getHeight();
	}

}
