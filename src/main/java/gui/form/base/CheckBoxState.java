package gui.form.base;

import gui.inputHandlers.clicks.MouseClick;

public abstract class CheckBoxState extends State {

	abstract void onClick(MouseClick click);
	public abstract boolean isChecked();
}
