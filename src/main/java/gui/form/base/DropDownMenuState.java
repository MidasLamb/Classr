package gui.form.base;

import gui.inputHandlers.clicks.MouseClick;

/**
 * An abstract class representing whether a DropDownMenu is Enabled or Disabled.
 */
public abstract class DropDownMenuState extends State {

	/**
	 * Defines the actions to be taken when a click is registered on the
	 * DropDownMenu.
	 * 
	 * @param click
	 */
	abstract void onClick(MouseClick click);

}
