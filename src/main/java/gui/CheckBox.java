package gui;

import java.awt.Graphics;

import static gui.Constants.*;

import inputHandlers.clicks.MouseClick;

public abstract class CheckBox extends FormObject {
	
	private CheckBoxState state;

	public CheckBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(new NotSelected());
	}
	
	public CheckBox(int x, int y) {
		super(x, y, STANDARD_CHECKBOX_WIDTH, STANDARD_CHECKBOX_HEIGHT);
		setState(new NotSelected());
	}

	private class Selected extends CheckBoxState {

		@Override
		void draw(Graphics g) {
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}

		@Override
		void onClick(MouseClick click) {
			setState(new NotSelected());			
		}
		
	}
	
	private class NotSelected extends CheckBoxState {

		@Override
		void draw(Graphics g) {
			g.drawRect(getX(), getY(), getWidth(), getHeight());
		}

		@Override
		void onClick(MouseClick click) {
			setState(new Selected());
		}
		
	}
	
	void onClick(MouseClick click){
		getState().onClick(click);
		onAction();
	}
	
	public abstract void onAction();
	
	void draw(Graphics g){
		getState().draw(g);
	}
	
	private CheckBoxState getState() {
		return state;
	}

	private void setState(CheckBoxState state) {
		this.state = state;
	}

}
