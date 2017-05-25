package gui.inputHandlers;

import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;

/**
 * Interface with methods to provide support for handling clicks.
 */
public interface Clickable {
	/**
	 * Implement to choose which behavior is expected on a single click.
	 * 
	 * @param click
	 *            SingleClick containing information about the position of the
	 *            click
	 */
	public void onClick(SingleClick click);

	/**
	 * Implement to choose the behavior that is expected on a double click.
	 * 
	 * @param click
	 *            DoubleClick containing information about the position of the
	 *            click
	 */
	public void onDoubleClick(DoubleClick click);

	/**
	 * Implement to choose the expected behavior when a mouse drag ends.
	 * 
	 * @param drag
	 *            Drag containing information about the positions of the drag
	 */
	public void onDragEnd(Drag drag);

	/**
	 * Implement to choose the expected behavior when a the mouse is being
	 * dragged.
	 * 
	 * @param drag
	 *            Drag containing information about the positions of the drag
	 */
	public void onDragUpdate(Drag drag);

}
