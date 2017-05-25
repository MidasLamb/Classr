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
	 *            the text to be displayed in the Button
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            width
	 * @param height
	 *            height
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
	 *            boolean to indicate whether Button needs to be enabled or
	 *            disabled
	 */
	public void setEnabled(boolean bool) {
		if (bool)
			setState(new Enabled());
		else {
			setState(new Disabled());
			this.setFocused(false);
		}
	}

	@Override
	public void onClick(MouseClick click) {
		setFocused(true);
		getState().onClick(click);
	}

	@Override
	public void draw(Graphics g) {
		getState().draw(g);
	}

	@Override
	boolean isFocusable() {
		return this.getState().isFocusable();
	}

	/*
	 * When Button is focused and enter key is typed, execute the onAction()
	 * method.
	 */
	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (this.isFocused()) {
			if (key.getKeyType().equals(FunctionKeyType.ENTER)) {
				this.onAction();
			}
		}
	}

	/**
	 * A class representing that a Button is enabled
	 */
	private class Enabled extends ButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(new Color(240, 240, 240));
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

	/**
	 * A class representing that a Button is disabled
	 */
	private class Disabled extends ButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			g.setColor(new Color(240, 240, 240));
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(Color.LIGHT_GRAY);
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

	/**
	 * Draw the text of this Button
	 * 
	 * @param g
	 *            the graphics
	 */
	protected void drawText(Graphics g) {
		getText().draw(g);
	}

	/**
	 * Get the state of this button
	 * 
	 * @return state of this Button
	 */
	protected ButtonState getState() {
		return state;
	}

	/**
	 * Set the state of this Button
	 * 
	 * @param state
	 *            the state to set
	 */
	private void setState(ButtonState state) {
		this.state = state;
	}

	/**
	 * Get the text Label of this Button
	 * 
	 * @return text Label of this Button
	 */
	private Label getText() {
		return text;
	}

}
