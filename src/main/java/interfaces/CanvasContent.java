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
	 * @param 	g
	 * 			the graphics
	 */
	public void show(Graphics g);
}
