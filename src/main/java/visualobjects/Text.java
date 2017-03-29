package visualobjects;

import static main.Constants.MAX_TEXT_WIDTH;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import inputHandlers.clicks.SingleClick;
import objects.LogicalObject;

public class Text extends VisualObject {
	private String standardTextString;
	private boolean isStandardTextSet;
	private boolean editable;

	public Text(int x, int y, int z, int width, int height, VisualObject parent, String standardstring,
			LogicalObject object) {
		super(x, y, z, width, height, parent);
		setLogicalObject(object);
		setStandardTextString(standardstring);
		setText(getStandardTextString());
		setIsStandardTextSet(true);
	}

	public Text(int x, int y, int z, VisualObject parent, String standardstring, LogicalObject object) {
		// 50, 16 is the standard size of the font if the text is "New Text"
		this(x, y, z, 50, 16, parent, standardstring, object);
	}

	/**
	 * Removes one character from text if there is a character to remove
	 */
	private void removeLetter() {
		if (this.getText().length() > 0)
			this.setText(getText().substring(0, getText().length() - 1));
	}

	/**
	 * 
	 * @param c
	 *            add character to the text
	 */
	private void addLetter(char c) {
		this.setText(this.getText() + c);
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public final void draw(Graphics g) {
		// Limit the text size if it is to long
		cutTextMaxWidth(g);
		// Get and set the width/height based on font
		FontMetrics m = g.getFontMetrics();
		this.setWidth(m.stringWidth(this.getText()));
		this.setHeight(m.getHeight());

		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		Color s = g.getColor();
		if (this.isStandardTextSet() && !this.hasSelectedAncestor())
			g.setColor(Color.gray);
		g.drawString(this.getText(), this.getX(), this.getY() + this.getHeight());

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

	/**
	 * Cuts the text so it's not larger than the box
	 * 
	 * @param g
	 *            Graphics
	 */
	private void cutTextMaxWidth(Graphics g) {
		FontMetrics m = g.getFontMetrics();
		while (m.stringWidth(getText()) > MAX_TEXT_WIDTH) {
			removeLetter();
		}
	}

	final String getText() {
		return getLogicalObject().getName();
	}

	private void setText(String text) {
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
}
