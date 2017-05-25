package gui.form.base;

import gui.inputHandlers.clicks.MouseClick;

/**
 * Indicates the state of a CheckBox
 */
public abstract class CheckBoxState extends State {

	/**
	 * Actions executed when clicked on this Button
	 * 
	 * @param click
	 *            MouseClick containing details of the click
	 */
	abstract void onClick(MouseClick click);

	/**
	 * @return whether this CheckBox is checked or not
	 */
	public abstract boolean isChecked();
}
