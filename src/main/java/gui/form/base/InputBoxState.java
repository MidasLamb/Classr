package gui.form.base;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;

public abstract class InputBoxState extends State implements Typable{
	abstract void onClick(MouseClick click);
}
