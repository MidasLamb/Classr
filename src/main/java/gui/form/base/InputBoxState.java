package gui.form.base;

import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.MouseClick;

/**
 * Indicates the state of an InputBox.
 */
public abstract class InputBoxState extends State implements Typable{
	abstract void onClick(MouseClick click);
}
