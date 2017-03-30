package gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;

public class InputBox extends FormObject implements Typable {

	private InputBoxState state;
	private String text;

	public InputBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(state);
	}
	
	private class TypeState extends InputBoxState{

		@Override
		public void HandleKeyEvent(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		void draw(Graphics g) {
			
		}

		@Override
		void onClick(MouseClick click) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private void trimText(Graphics g){
	}
	
	private void deleteChar(){
		
	}
	
	private class PassiveState extends InputBoxState{

		@Override
		public void HandleKeyEvent(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		void draw(Graphics g) {
			
		}

		@Override
		void onClick(MouseClick click) {
			setState(new TypeState());
		}
		
	}

	@Override
	void onClick(MouseClick click) {
		getState().onClick(click);
	}

	@Override
	void draw(Graphics g) {
		getState().draw(g);		
	}

	@Override
	public void HandleKeyEvent(KeyEvent e) {
		getState().HandleKeyEvent(e);
	}

	private InputBoxState getState() {
		return state;
	}

	private void setState(InputBoxState state) {
		this.state = state;
	}

	private String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}

}
