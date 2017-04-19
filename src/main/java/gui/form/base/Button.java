package gui.form.base;

import static gui.form.base.Constants.*;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

/**
 * Button that can be added to a Form
 */
public abstract class Button extends FormObject implements FunctionTypable {

	private final Label text;
	private ButtonState state;

	/**
	 * Create a new Button and set its text, coordinates and dimensions.
	 * 
	 * @param text
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Button(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = new Label(text, x + STANDARD_BUTTON_TEXT_PADDING, y + STANDARD_BUTTON_TEXT_PADDING);
		setEnabled(true);
	}

	/**
	 * Enable or disable the Button
	 * 
	 * @param bool
	 */
	public void setEnabled(boolean bool) {
		if (bool)
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

	@Override
	boolean isFocusable() {
		return this.getState().isFocusable();
	}
	
	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (this.isFocused()) {
			if (key.getKeyType().equals(FunctionKeyType.ENTER)) {
				this.onAction();
			}
		}
	}

	private class Enabled extends ButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(Color.GREEN);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.BLACK);
			drawText(g);
			if (isFocused()) {
				g.setColor(Color.BLUE);
				g.drawRect(getX(), getY(), getWidth(), getHeight());
			}
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {
			onAction();
		}

		@Override
		boolean isFocusable() {
			return true;
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
		void onClick(MouseClick click) {
		}

		@Override
		boolean isFocusable() {
			return false;
		}

	}

	private void drawText(Graphics g) {
		int height = getText().getAscent();
		int width = getText().getWidth();
		int middleX = getWidth() / 2 + getX();
		int middleY = getHeight() / 2 + getY();

		getText().draw(g);
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
