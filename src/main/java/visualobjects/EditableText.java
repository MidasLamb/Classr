package visualobjects;

import static main.Constants.MAX_TEXT_WIDTH;
import static main.Constants.STANDARD_FONTMETRICS;

import java.awt.Color;
import java.awt.Graphics;

import inputHandler.keys.AsciiKey;
import inputHandler.keys.FunctionKey;
import inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalObject;

public class EditableText extends Text {
	private String standardTextString;
	private boolean isStandardTextSet;
	
	public EditableText(int x, int y, int z, VisualObject parent, String standardstring,
			LogicalObject object) {
		super(x, y, z, parent, object);
		setStandardTextString(standardstring);
		setString(getStandardTextString());
		setIsStandardTextSet(true);
	}

	/**
	 * Removes one character from text if there is a character to remove
	 */
	private void removeLetter() {
		if (getLogicalObject().getName().length() > 0)
			this.setString(getLogicalObject().getName().substring(0, getLogicalObject().getName().length() - 1));
	}

	/**
	 * 
	 * @param c
	 *            add character to the text if the text is not already too long
	 */
	private void addLetter(char c) {
		if (STANDARD_FONTMETRICS.stringWidth(getString() + c) <= MAX_TEXT_WIDTH)
			this.setString(getLogicalObject().getName() + c);
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public final void draw(Graphics g) {
		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		Color s = g.getColor();
		if (this.isStandardTextSet() && !this.hasSelectedAncestor())
			g.setColor(Color.gray);
		g.drawString(getText().getIterator(), this.getX(), this.getY() + this.getHeight());

		if (this.isSelected())
			this.drawCursor(g);

		if (this.isStandardTextSet() && !this.hasSelectedAncestor())
			g.setColor(s);
	}

	/**
	 * Draws the cursor
	 * 
	 * @param g
	 *            Graphics
	 */
	private void drawCursor(Graphics g) {
		g.drawLine(this.getX() + this.getWidth() + 1, this.getY(), this.getX() + this.getWidth() + 1,
				this.getY() + this.getHeight());
		g.drawLine(this.getX() + this.getWidth() + 2, this.getY(), this.getX() + this.getWidth() + 2,
				this.getY() + this.getHeight());
	}

	private void setString(String text) {
		getLogicalObject().setName(text);
	}

	private boolean isStandardTextSet() {
		return isStandardTextSet;
	}

	private String getStandardTextString() {
		return standardTextString;
	}

	private void setStandardTextString(String standardTextString) {
		this.standardTextString = standardTextString;
	}

	private void setIsStandardTextSet(boolean isStandardText) {
		this.isStandardTextSet = isStandardText;
	}
	
	@Override
	public void handleAsciiKey(AsciiKey key){
		this.addLetter(key.getValue());
	}
	
	@Override
	public void handleFunctionKey(FunctionKey key){
		switch(key.getKeyType()){
		case BACKSPACE:
			this.removeLetter();
			break;
		case ENTER:
			this.getContainer().switchSelectedTo(null);
			break;
		case ESCAPE:
			this.getContainer().switchSelectedTo(null);
			break;
		case DELETE:
			this.getLogicalObject().delete();
			break;
		default:
			break;
		}
	}
	
	@Override
	void onClick(SingleClick sc){
		this.getContainer().switchSelectedTo(this);
	}
	
	@Override
	public final void setSelected(boolean b) {
		boolean prev = this.isSelected();
		super.setSelected(b);
		if (this.isSelected() == false && prev) {
			if (getString().length() == 0) {
				this.setString(this.getStandardTextString());
				setIsStandardTextSet(true);
			}
		}

		if (this.isSelected() && prev == false) {
			if (isStandardTextSet()) {
				this.setString("");
				setIsStandardTextSet(false);
			}
		}
	}
}
