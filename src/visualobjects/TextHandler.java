package visualobjects;

import static main.Constants.MAX_TEXT_WIDTH;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import objects.LogicalObject;

public class TextHandler extends VisualObject {

	public TextHandler(int x, int y, int z, int width, int height, VisualObject parent, String standardstring,
			LogicalObject object) {
		super(x, y, z, width, height, parent);
		setLogicalObject(object);
		setStandardTextString(standardstring);
		setText(getStandardTextString());
		setIsStandardTextSet(true);
	}

	public TextHandler(int x, int y, int z, VisualObject parent, String standardstring, LogicalObject object) {
		// 50, 16 is the standard size of the font if the text is "New Text"
		this(x, y, z, 50, 16, parent, standardstring, object);
	}

	/**
	 * @post removes one letter from text
	 */
	public void removeLetter() {
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
	 * @post shows the text frame
	 */
	@Override
	public void draw(Graphics g) {
		// Limit the text size if it is to long
		cutTextMaxWidth(g);
		// Get and set the width/height based on font
		FontMetrics m = g.getFontMetrics();
		this.setWidth(m.stringWidth(this.getText()));
		this.setHeight(m.getHeight());

		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		g.drawString(this.getText(), this.getX(), this.getY() + this.getHeight());

		// If the text is selected draw the cursor
		if (this.isSelected())
			drawCursor(g);
	}

	/**
	 * 
	 * @param g
	 *            Graphics
	 * @post draws the cursor
	 */
	private void drawCursor(Graphics g) {
		g.drawLine(this.getX() + this.getWidth() + 1, this.getY(), this.getX() + this.getWidth() + 1,
				this.getY() + this.getHeight());
		g.drawLine(this.getX() + this.getWidth() + 2, this.getY(), this.getX() + this.getWidth() + 2,
				this.getY() + this.getHeight());
	}

	@Override
	public void handleKey(KeyEvent e) {
		// Get the key and put it in a string
		String s = Character.toString(e.getKeyChar());
		// Delete letter if you press the backspace
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || s.equals("\b")) {
			this.removeLetter();
			// Unselect this object
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.setSelected(false);
			// if it isn't an action key write it down
		} else if (!e.isActionKey() && e.getKeyCode() != KeyEvent.VK_SHIFT) {
			this.addLetter(s.charAt(0));
		}
	}

	/**
	 * 
	 * @param g
	 *            Graphics
	 * @post cuts the text so it's not larger than the box
	 */
	private void cutTextMaxWidth(Graphics g) {
		FontMetrics m = g.getFontMetrics();
		while (m.stringWidth(getText()) > MAX_TEXT_WIDTH) {
			removeLetter();
		}
	}

	@Override
	public void setSelected(boolean b) {
		boolean prev = this.isSelected();
		super.setSelected(b);
		if (this.isSelected() == false && prev) {
			if (this.getText().length() == 0) {
				this.setText(this.getStandardTextString());
				setIsStandardTextSet(true);
			}
		}

		if (this.isSelected() && prev == false) {
			if (isStandardTextSet()) {
				this.setText("");
				setIsStandardTextSet(false);
			}
		}
	}

	// Getters and setters

	private String getText() {
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

	private String standardTextString;

	private void setIsStandardTextSet(boolean isStandardText) {
		this.isStandardTextSet = isStandardText;
	}

	private boolean isStandardTextSet;
}
