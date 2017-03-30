package gui.base;

import static gui.base.Constants.*;

import java.awt.Color;
import java.awt.Graphics;

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
			g.setColor(Color.GREEN);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.BLACK);
			drawText(g);
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
			g.setColor(Color.RED);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.DARK_GRAY);
			drawText(g);
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {}
		
	}
	
	private void drawText(Graphics g){
		int height = getText().getAscent();
		int width = getText().getWidth();
		int middleX = getWidth()/2 + getX();
		int middleY = getHeight()/2 + getY();
		getText().draw(g, middleX-width/2, middleY-height);
	}
		
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
