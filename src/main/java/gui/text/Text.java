package gui.text;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import gui.text.state.PassiveState;
import gui.text.state.TextState;
import inputHandlers.Typable;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;
import logicalobjects.StringVisitor;

public class Text implements Typable{
	private TextState state;
	private String text;

	public Text(String text, TextState startState) {
		this.setText(text);
		this.setState(startState);
		startState.setText(this);
	}
	
	public final void switchState(TextState state){
		this.setState(state);
		state.setText(this);
	}

	public final TextState getState() {
		return state;
	}

	private final void setState(TextState state) {
		this.state = state;
	}
	

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		this.getState().handleAsciiKey(key);
		
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		this.getState().handleFunctionKey(key);
		
	}
	
	public void draw(Graphics g, int x, int y){
		this.getState().draw(g, x, y);
	}
	
	public void addLetter(char c){
		this.setText(this.getText() + c);
	}
	
	public void deleteChar() {
		if (this.getText().length() > 0)
			this.setText(this.getText().substring(0, this.getText().length() - 1));
	}
	
	public int getTextWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(this.getText());
	}

	
	

}
