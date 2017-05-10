package gui.form.base;

import static gui.form.base.Constants.STANDARD_RADIOBUTTON_HEIGHT;
import static gui.form.base.Constants.STANDARD_RADIOBUTTON_WIDTH;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

/**
 * RadioButton that can be added to a Form
 */
public abstract class RadioButton extends FormObject implements FunctionTypable {

	private RadioButtonState state;
	private final RadioButtonGroup group;

	/**
	 * Create a new RadioButton and set its RadioButtonGroup, coordinates and
	 * dimensions.
	 * 
	 * @param group
	 *            RadioButtonGroup to which this RadioButton belongs
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            Width of this RadioButton
	 * @param height
	 *            Height of this RadioButton
	 */
	public RadioButton(RadioButtonGroup group, int x, int y, int width, int height) {
		super(x, y, width, height);
		setState(new NotSelected());
		this.group = group;
	}

	/**
	 * Create a new RadioButton with the default dimensions and set its
	 * RadioButtonGroup and coordinates.
	 * 
	 * @param group
	 *            RadioButtonGroup to which this RadioButton belongs
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public RadioButton(RadioButtonGroup group, int x, int y) {
		super(x, y, STANDARD_RADIOBUTTON_WIDTH, STANDARD_RADIOBUTTON_HEIGHT);
		setState(new NotSelected());
		this.group = group;
	}

	private class Selected extends RadioButtonState {

		@Override
		void draw(Graphics g) {
			g.fillOval(getX(), getY(), getWidth(), getHeight());
			Color color = g.getColor();
			if (isFocused()) {
				g.setColor(Color.BLUE);
				g.drawOval(getX(), getY(), getWidth(), getHeight());
			}
			g.setColor(color);
		}

	}

	private class NotSelected extends RadioButtonState {

		@Override
		void draw(Graphics g) {
			Color color = g.getColor();
			if (isFocused()) {
				g.setColor(Color.BLUE);
			}
			g.drawOval(getX(), getY(), getWidth(), getHeight());
			g.setColor(color);
		}

	}

	@Override
	void onClick(MouseClick click) {
		this.setFocused(true);

		this.getGroup().radioButtonIsClicked(this);
		onAction();
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (this.isFocused()) {
			if (key.getKeyType().equals(FunctionKeyType.ENTER)) {
				this.getGroup().radioButtonIsClicked(this);
				onAction();
			}
		}
	}

	@Override
	public
	void draw(Graphics g) {
		getState().draw(g);
	}

	/**
	 * Set the selected property of this RadioButton.
	 * 
	 * @param selected
	 *            true if this RadioButton is selected, false otherwise
	 */
	void setSelected(boolean selected) {
		if (selected)
			this.setState(new Selected());
		else
			this.setState(new NotSelected());
	}

	/**
	 * Indicates if this RadioButton is selected.
	 * 
	 * @return true if this RadioButton is selected, false otherwise
	 */
	public boolean isSelected() {
		return getState() instanceof Selected;
	}

	private RadioButtonState getState() {
		return state;
	}

	private void setState(RadioButtonState state) {
		this.state = state;
	}

	/**
	 * Get the RadioButtonGroup to which this RadioButton belongs.
	 * 
	 * @return RadioButtonGroup to which this RadioButton belongs
	 */
	public RadioButtonGroup getGroup() {
		return group;
	}

}
