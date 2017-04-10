package gui.base;

import java.awt.Color;
import java.awt.Graphics;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public abstract class InputBox extends FormObject implements Typable{

	private InputBoxState state;
	private String text;
	
	public InputBox(String text, int x, int y, int width, int height){
		super(x, y, width, height);
		setState(new PassiveState());
		this.text = text;
	}

	public InputBox(int x, int y, int width, int height) {
		this("", x,y,width,height);
	}
	
	private class TypeState extends InputBoxState{

		@Override
		void draw(Graphics g) {
			Color prev = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(getText(), getX(), getY() + getHeight());
			g.setColor(prev);
		}

		@Override
		void onClick(MouseClick click) {}

		@Override
		public void handleAsciiKey(AsciiKey key) {
			addLetter(key.getValue());	
			onAction();
		}

		@Override
		public void handleFunctionKey(FunctionKey key) {
			switch(key.getKeyType()){
			case BACKSPACE:
				deleteChar();
				onAction();
				break;
			case ENTER:
				setState(new PassiveState());
				break;
			case ESCAPE:
				setState(new PassiveState());
				break;
			}
			
		}
		
	}
	
	private void addLetter(char s){
		this.setText(this.getText() + s);
	}
	
	private void deleteChar(){
		this.setText(this.getText().substring(0, this.getText().length() - 1));
	}
	
	private class PassiveState extends InputBoxState{

		@Override
		void draw(Graphics g) {
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(getText(), getX(), getY() + getHeight());
		}

		@Override
		void onClick(MouseClick click) {
			setState(new TypeState());
		}

		@Override
		public void handleAsciiKey(AsciiKey key) {}

		@Override
		public void handleFunctionKey(FunctionKey key) {}
		
	}
	
	@Override
	void handleClick(MouseClick click){
		if(click.getX() >= getX() && click.getY() >= getY() 
				&& click.getX() <= getX()+getWidth() && click.getY() <= getY()+getHeight())
			onClick(click);
		else 
			setState(new PassiveState());
	}
	
	@Override
	public void handleAsciiKey(AsciiKey key){
		getState().handleAsciiKey(key);
	}
	
	@Override
	public void handleFunctionKey(FunctionKey key){
		getState().handleFunctionKey(key);
	}

	@Override
	void onClick(MouseClick click) {
		getState().onClick(click);
	}

	@Override
	protected void draw(Graphics g) {
		getState().draw(g);		
	}

	private InputBoxState getState() {
		return state;
	}

	private void setState(InputBoxState state) {
		this.state = state;
	}

	public final String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

}
