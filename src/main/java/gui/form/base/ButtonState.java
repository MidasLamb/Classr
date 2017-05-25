package gui.form.base;

import gui.inputHandlers.clicks.MouseClick;

/**
 * Indicates the state of a Button
 */
public abstract class ButtonState extends State {

	/**
	 * Method executed when clicked on this Button
	 * 
	 * @param click
	 *            MouseClick containing details of the click
	 */
	abstract void onClick(MouseClick click);

	/**
	 * @return whether or not this button is focusable
	 */
	abstract boolean isFocusable();
}
