package gui.text;

import gui.text.state.PassiveState;

public class TextCreator {

	public static Text createDisplayText(String text){
		return new Text(text, new PassiveState());
	}
	
	public static Text createEditableText(String text){
		return null;
	}
}
