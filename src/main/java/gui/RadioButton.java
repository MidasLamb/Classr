package gui;

import java.awt.Graphics;

import inputHandlers.clicks.MouseClick;

import static gui.Constants.*;

public abstract class RadioButton extends FormObject {
	
	private State state;
	private final RadioButtonGroup group;

	public RadioButton(RadioButtonGroup group, int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(new NotSelected());
		this.group = group;
	}
	
	public RadioButton(RadioButtonGroup group, int x, int y) {
		super(x, y, STANDARD_RADIOBUTTON_WIDTH, STANDARD_RADIOBUTTON_HEIGHT);
		setState(new NotSelected());
		this.group = group;
	}

	private class Selected extends RadioButtonState {

		@Override
		void draw(Graphics g) {
			g.fillOval(getX(), getY(), getWidth(), getHeight());
		}
		
	}
	
	private class NotSelected extends RadioButtonState {

		@Override
		void draw(Graphics g) {
			g.drawOval(getX(), getY(), getWidth(), getHeight());
		}
		
	}
	
	void onClick(MouseClick click){
		this.getGroup().radioButtonIsClicked(this);
		onAction();
	}
	
	public abstract void onAction();
	
	void draw(Graphics g){
		getState().draw(g);
	}
	
	void setSelected(boolean selected){
		if(selected)
			this.setState(new Selected());
		else
			this.setState(new NotSelected());
	}
	
	public boolean isSelected(){
		return getState() instanceof Selected;
	}
	
	private State getState() {
		return state;
	}

	private void setState(State state) {
		this.state = state;
	}

	public RadioButtonGroup getGroup() {
		return group;
	}

}
