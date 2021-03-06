package gui.form.base;

import static gui.form.base.Constants.STANDARD_CHECKBOX_HEIGHT;
import static gui.form.base.Constants.STANDARD_CHECKBOX_WIDTH;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

/**
 * TextBox that can be added to a Form
 */
public abstract class CheckBox extends FormObject implements FunctionTypable {

	private CheckBoxState state;

	/**
	 * Create a new CheckBox and set its coordinates and dimensions.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            width
	 * @param height
	 *            height
	 */
	public CheckBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(new NotSelected());
	}

	/**
	 * Create a new CheckBox and set its coordinates. Uses the default CheckBox
	 * dimensions.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public CheckBox(int x, int y) {
		this(x, y, STANDARD_CHECKBOX_WIDTH, STANDARD_CHECKBOX_HEIGHT);
	}

	/**
	 * A class representing that a CheckBox is selected
	 */
	private class Selected extends CheckBoxState {

		@Override
		void draw(Graphics g) {
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			Color color = g.getColor();
			if (isFocused()) {
				g.setColor(Color.BLUE);
				g.drawRect(getX(), getY(), getWidth(), getHeight());
			}
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {
			setState(new NotSelected());
		}

		@Override
		public boolean isChecked() {
			return true;
		}

	}

	/**
	 * A class representing that a CheckBox is not selected
	 */
	private class NotSelected extends CheckBoxState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			if (isFocused()) {
				g.setColor(Color.BLUE);
			}
			g.drawRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(color);
		}

		@Override
		void onClick(MouseClick click) {
			setState(new Selected());
		}

		@Override
		public boolean isChecked() {
			return false;
		}

	}

	@Override
	public void onClick(MouseClick click) {
		setFocused(true);

		getState().onClick(click);
		onAction();
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (this.isFocused()) {
			if (key.getKeyType().equals(FunctionKeyType.ENTER)) {
				onClick(new MouseClick(0, 0));
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		getState().draw(g);
	}

	/**
	 * Get the state of this CheckBox
	 * 
	 * @return state
	 */
	private CheckBoxState getState() {
		return state;
	}

	/**
	 * Set the state of this CheckBox
	 * 
	 * @param state
	 *            State to set
	 */
	private void setState(CheckBoxState state) {
		this.state = state;
	}

	/**
	 * @return True if this CheckBox is selected, false otherwise.
	 */
	public boolean isChecked() {
		return this.getState().isChecked();
	}

	/**
	 * Set this CheckBox as checked or unchecked.
	 * 
	 * @param b
	 *            boolean indicating whether b should be checked or not
	 */
	public void setChecked(boolean b) {
		if (b)
			this.setState(new Selected());
		else
			this.setState(new NotSelected());
	}

}
