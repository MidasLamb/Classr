package interfaces;

import java.awt.Graphics;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.Typable;

/**
 * An interface for classes that need to be displayed in the canvas window
 */
public interface CanvasContent extends Typable, Clickable {

	/**
	 * A function to draw the class
	 * 
	 * @param g
	 *            the graphics
	 */
	public void show(Graphics g);

	/**
	 * Get the width of this content.
	 * 
	 * @return width of the content
	 */
	public int getWidth();

	/**
	 * Get the height of this content.
	 * 
	 * @return height of the content.
	 */
	public int getHeight();

	/**
	 * Called when the focus from the container of this canvas content is
	 * switched.
	 */
	public void clearFocus();
}
