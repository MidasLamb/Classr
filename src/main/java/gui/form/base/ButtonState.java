package gui.form.base;

import gui.inputHandlers.clicks.MouseClick;

public abstract class ButtonState extends State {

	abstract void onClick(MouseClick click);
	abstract boolean isFocusable();
	
}
