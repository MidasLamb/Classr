package gui;

import inputHandlers.clicks.MouseClick;

public abstract class ButtonState extends State {

	abstract void onClick(MouseClick click);
	
}