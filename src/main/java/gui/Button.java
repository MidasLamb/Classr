package gui;

import java.awt.Color;
import java.awt.Graphics;

import static gui.Constants.*;

import inputHandlers.clicks.MouseClick;

public abstract class Button extends FormObject {
	
	private final Label text;
	private ButtonState state;

	public Button(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = new Label(text, x + STANDARD_BUTTON_TEXT_PADDING, 
				y + STANDARD_BUTTON_TEXT_PADDING);
		setEnabled(true);
	}
	
	public void setEnabled(boolean bool){
		if(bool)
			setState(new Enabled());
		else
			setState(new Disabled());			
	}

	@Override
	void onClick(MouseClick click) {
		getState().onClick(click);
	}

	@Override
	void draw(Graphics g) {
		getState().draw(g);
	}
	
	private class Enabled extends ButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.BLACK);
			getText().draw(g);
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {
			onAction();
		}
		
	}
	
	private class Disabled extends ButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.DARK_GRAY);
			getText().draw(g);
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {}
		
	}
	
	abstract void onAction();
	
	private ButtonState getState() {
		return state;
	}

	private void setState(ButtonState state) {
		this.state = state;
	}

	private Label getText() {
		return text;
	}

}
