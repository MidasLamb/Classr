package gui;

import java.awt.Graphics;

public abstract class RadioButton extends FormObject {
	
	private State state;

	public RadioButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(new NotSelected());
	}
	
	public RadioButton(int x, int y) {
		super(x, y, 10, 10);
		setState(new NotSelected());
	}

	private class Selected extends State {

		@Override
		void draw(Graphics g) {
			g.fillOval(getX(), getY(), getWidth(), getHeight());
		}
		
	}
	
	private class NotSelected extends State {

		@Override
		void draw(Graphics g) {
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		}
		
	}
	
	void onClick(){
		if (getState() instanceof Selected)
			setState(new NotSelected());
		else 
			setState(new Selected());
		onAction();
	}
	
	public abstract void onAction();
	
	void draw(Graphics g){
		getState().draw(g);
	}
	
	private State getState() {
		return state;
	}

	private void setState(State state) {
		this.state = state;
	}

}
