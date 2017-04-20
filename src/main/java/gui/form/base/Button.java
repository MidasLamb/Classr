package gui.form.base;

import static gui.form.base.Constants.STANDARD_BUTTON_TEXT_PADDING;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.clicks.MouseClick;
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
		setFocused(true);
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

	/*
	 * When Button is focused and enter key is typed, execute the onAction() method.
	 */
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
