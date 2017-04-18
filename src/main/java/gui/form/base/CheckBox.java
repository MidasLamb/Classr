package gui.form.base;

import static gui.form.base.Constants.*;

import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * TextBox that can be added to a Form
 */
public abstract class CheckBox extends FormObject {

	private CheckBoxState state;

	/**
	 * Create a new CheckBox and set its coordinates and dimensions.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
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
	 * @param y
	 */
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

		@Override
		public boolean isChecked() {
			return true;
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

		@Override
		public boolean isChecked() {
			return false;
		}

	}

	void onClick(MouseClick click) {
		getState().onClick(click);
		onAction();
	}

	void draw(Graphics g) {
		getState().draw(g);
	}

	private CheckBoxState getState() {
		return state;
	}

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
	 */
	public void setChecked(boolean b) {
		if (b)
			this.setState(new Selected());
		else
			this.setState(new NotSelected());
	}

}
