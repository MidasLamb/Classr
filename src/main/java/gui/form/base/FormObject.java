package gui.form.base;

import static gui.form.base.Constants.STANDARD_LABEL_PADDING;
import static gui.form.base.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;

/**
 * Object that can be added to a Form.
 */
public abstract class FormObject implements Comparable<FormObject> {

	private final int x, y, width, height;
	private boolean focused;
	private boolean focusable = true;

	/**
	 * Default constructor for a FormObject. Creates a new FormObject and sets
	 * its coordinates and dimensions.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public FormObject(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Compare the positions of a given FormObject with this FormObject.
	 */
	@Override
	public int compareTo(FormObject formObj) {
		int diffY = this.getY() - formObj.getY();
		if (diffY == 0) {
			// sort by x
			return this.getX() - formObj.getX();
		} else {
			return diffY;
		}
	}

	/**
	 * Called when a click is registered on this FormObject.
	 * 
	 * @param click
	 *            Registered MouseClick
	 */

	abstract void onClick(MouseClick click);
	
	public void onClickk(SingleClick click){
		onClick(click);
	}

	/**
	 * Draw this FormObject.
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics g);

	protected abstract void onAction();

	/**
	 * Checks if the received click is for this object and handles it if it is
	 * for this object
	 * 
	 * @param click
	 *            the click
	 */
	void handleClick(MouseClick click) {
		if (click.getX() >= getX() && click.getY() >= getY() && click.getX() <= getX() + getWidth()
				&& click.getY() <= getY() + getHeight())
			onClick(click);
	}

	/**
	 * Create a label for this FormObject at the specified position.
	 * 
	 * @param position
	 *            desired position of the label
	 * @param text
	 *            text that should be displayed by the label
	 * @return
	 */
	public Label createLabel(LabelPosition position, String text) {
		int x = 0, y = 0;
		switch (position) {
		case RIGHT:
			x = this.getX() + this.getWidth() + STANDARD_LABEL_PADDING;
			int midY = (this.getY() + this.getY() + this.getHeight()) / 2;
			y = midY - STANDARD_TEXT_HEIGHT / 2;
			break;
		case TOP:
			x = this.getX();
			y = this.getY() - STANDARD_TEXT_HEIGHT;
			break;
		}
		FormObject thisFormObject = this;
		return new Label(text, x, y) {
			@Override
			protected void onAction() {
				thisFormObject.onClick(new MouseClick(0, 0));
			}
		};
	}

	/**
	 * @return Returns true if this FormObject is focusable, false otherwise.
	 */
	boolean isFocusable() {
		return focusable;
	}

	/**
	 * Set the focusable property of this FormObject.
	 * 
	 * @param focusable
	 *            true if this FormObject is focusable, false otherwise.
	 */
	protected final void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	/**
	 * Set this FormObject's focused property.
	 * 
	 * @param focused
	 *            true if this FormObject is focused, false otherwise.
	 */
	void setFocused(boolean focused) {
		this.focused = focused;
	}

	/**
	 * Indicates the focused property of this FormObject.
	 * 
	 * @return Returns true if this FormObject is focused, false otherwise.
	 */
	boolean isFocused() {
		return this.focused;
	}

	/**
	 * @return The x-coordinate of this FormObject.
	 */
	int getX() {
		return x;
	}

	/**
	 * @return The y-coordinate of this FormObject.
	 */
	int getY() {
		return y;
	}

	/**
	 * @return The width of this FormObject.
	 */
	int getWidth() {
		return width;
	}

	/**
	 * @return The height of this FormObject.
	 */
	int getHeight() {
		return height;
	}

	/**
	 * Enumeration of possible label positions.
	 */
	static public enum LabelPosition {
		RIGHT, TOP;
	}
}
