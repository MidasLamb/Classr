package gui.text.state;

import java.awt.Graphics;

import gui.inputHandlers.Typable;
import gui.text.Text;

/**
 * An abstract class for defining the state of a Text object
 */
public abstract class TextState implements Typable {
	private Text text;

	/**
	 * Sets the text object to which this state is linked
	 * 
	 * @param text
	 *            the text to which to set.
	 */
	public void setText(Text text) {
		this.text = text;
	}

	/**
	 * @return the text object to which this state is linked
	 */
	protected final Text getText() {
		return text;
	}

	/**
	 * Draws the text in the current state, given the graphics and coordinates
	 * where it needs to be drawn
	 * 
	 * @param g
	 *            the graphics
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 */
	public abstract void draw(Graphics g, int x, int y);

	/**
	 * Returns whether or not this state is an editable state.
	 * 
	 * @return Whether or not this state is an editable state.
	 */
	public abstract boolean isEditable();
}
