package gui.base;

import java.awt.event.KeyEvent;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;

public abstract class InputBoxState extends State{
	abstract void onClick(MouseClick click);
	public abstract void handleKeyEvent(KeyEvent e);
}
