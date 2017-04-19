package gui.form.base;

import static gui.form.base.Constants.*;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

public abstract class RadioButton extends FormObject implements FunctionTypable {

	private RadioButtonState state;
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

	void draw(Graphics g) {
		getState().draw(g);
	}

	void setSelected(boolean selected) {
		if (selected)
			this.setState(new Selected());
		else
			this.setState(new NotSelected());
	}

	public boolean isSelected() {
		return getState() instanceof Selected;
	}

	private RadioButtonState getState() {
		return state;
	}

	private void setState(RadioButtonState state) {
		this.state = state;
	}

	public RadioButtonGroup getGroup() {
		return group;
	}

}
