package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.Typable;
import gui.text.Text;
import gui.text.state.EditableState;
import gui.text.state.PassiveState;
import inputHandlers.clicks.MouseClick;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

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
		this.setTextObject(new Text(text, new PassiveState()));
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
	void onClick(MouseClick click) {
		this.getTextObject().switchState(new EditableState());
		onAction();
	}

	@Override
	protected void draw(Graphics g) {
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		getTextObject().draw(g, this.getX(), this.getY());
	}


	protected final Text getTextObject() {
		return textObject;
	}

	private final void setTextObject(Text textObject) {
		this.textObject = textObject;
	}
	
	public String getText(){
		return this.getTextObject().getText();
	}

}
