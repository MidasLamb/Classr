package gui.base;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;

public abstract class InputBox extends FormObject{

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
		public void handleKeyEvent(KeyEvent e) {
			// Get the key and put it in a string
			String s = Character.toString(e.getKeyChar());
			// Delete letter if you press the backspace
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || s.equals("\b")) {
				deleteChar();
				// Unselect this object
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				setState(new PassiveState());
				// if it isn't an action key write it down
			} else if (!e.isActionKey() && e.getKeyCode() != KeyEvent.VK_SHIFT && e.getKeyCode() != KeyEvent.VK_DELETE) {
				addLetter(s.charAt(0));
			}
			
		}

		@Override
		void draw(Graphics g) {
			Color prev = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(getText(), getX(), getY() + getHeight());
			g.setColor(prev);
		}

		@Override
		void onClick(MouseClick click) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void addLetter(char s){
		this.setText(this.getText() + s);
	}
	private void trimText(Graphics g){
	}
	
	private void deleteChar(){
		this.setText(this.getText().substring(0, this.getText().length() - 1));
	}
	
	private class PassiveState extends InputBoxState{

		@Override
		public void handleKeyEvent(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		void draw(Graphics g) {
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.drawString(getText(), getX(), getY() + getHeight());
		}

		@Override
		void onClick(MouseClick click) {
			setState(new TypeState());
		}
		
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
	void onClick(MouseClick click) {
		getState().onClick(click);
	}

	@Override
	protected void draw(Graphics g) {
		getState().draw(g);		
	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		getState().handleKeyEvent(e);
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
