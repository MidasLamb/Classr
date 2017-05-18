package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedString;

import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.text.Text;
import gui.text.state.EditableState;
import gui.text.state.PassiveState;

/**
 * InputBox that can be added to a Form.
 */
public abstract class InputBox extends FormObject implements Typable {

	private Text textObject;

	/**
	 * Create a new InputBox and set its text, coordinates and dimensions.
	 * 
	 * @param text
	 *            text that is filled in
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 * @param height
	 */
	public InputBox(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setTextObject(new Text(text, new PassiveState(), width));
	}

	/**
	 * Create a new InputBox and set its coordinates and dimensions.
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public InputBox(int x, int y, int width, int height) {
		this("", x, y, width, height);
	}

	@Override
	void handleClick(MouseClick click) {
		if (click.getX() >= getX() && click.getY() >= getY() && click.getX() <= getX() + getWidth()
				&& click.getY() <= getY() + getHeight())
			onClick(click);
		else
			this.getTextObject().switchState(new PassiveState());
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		this.getTextObject().handleAsciiKey(key);
		onAction();
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		this.getTextObject().handleFunctionKey(key);
		onAction();
	}

	@Override
	public void onClick(MouseClick click) {
		this.setFocused(true);

		this.getTextObject().switchState(new EditableState());
		onAction();
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		if (this.isFocused()) {
			g.setColor(Color.BLUE);
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(c);
		getTextObject().draw(g, this.getX(), this.getY());
	}

	@Override
	void setFocused(boolean focused) {
		super.setFocused(focused);
		if (focused) {
			this.getTextObject().switchState(new EditableState());
		} else {
			this.getTextObject().switchState(new PassiveState());
		}
		onAction();
	}

	/**
	 * @return Text object that contains the value of this InputBox.
	 */
	protected final Text getTextObject() {
		return textObject;
	}

	private final void setTextObject(Text textObject) {
		this.textObject = textObject;
	}

	/**
	 * @return Text entered in this InputBox.
	 */
	public String getText() {
		return this.getTextObject().getText();
	}

	public void setText(String string) {
		this.getTextObject().setAttributedText(new AttributedString(string));
	}

}
