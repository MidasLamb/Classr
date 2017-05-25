package gui.form.base;

import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.MouseClick;

/**
 * Indicates the state of an InputBox.
 */
public abstract class InputBoxState extends State implements Typable {

	/**
	 * Actions executed when clicked on this InputBox
	 * 
	 * @param click
	 *            MouseClick containing details of the click
	 */
	abstract void onClick(MouseClick click);
}
