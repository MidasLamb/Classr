package visualobjects;

import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedString;

import command.Controller;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.text.Text;
import gui.text.state.EditableState;
import gui.text.state.PassiveState;
import logicalobjects.LogicalObject;

/**
 * A wrapper for the GUI Text to adapt it to the application part.
 *
 */
public class EditableTextWrapper extends TextWrapper {
	private String standardString;
	private String regex;

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	string
	 * 			the text
	 * @param 	regex
	 * 			the regex to which the text needs to match
	 * @param 	parent
	 * 			the VisualObject which is the parent of this object
	 * @param 	object
	 * 			the logicalObject which is linked to this text
	 */
	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object, Controller controller) {
		super(x, y, z, parent, object, controller);
		setLogicalObject(object);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState()));
		this.setStandardString(string);
		this.setRegex(regex);
		if (!this.getStandardString().matches(this.getRegex())) {
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	string
	 * 			the text
	 * @param 	regex
	 * 			the regex to which the text needs to match
	 * @param 	parent
	 * 			the VisualObject which is the parent of this object
	 * @param 	object
	 * 			the logicalObject which is linked to this text
	 * @param 	maxWidth
	 * 			the maxWidth that this text may have
	 */
	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object, int maxWidth, Controller controller) {
		super(x, y, z, parent, object, controller);
		setLogicalObject(object);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState(), maxWidth));
		this.setStandardString(string);
		this.setRegex(regex);
	}

	/**
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	string
	 * 			the text
	 * @param 	parent
	 * 			the VisualObject which is the parent of this object
	 * @param 	object
	 * 			the logicalObject which is linked to this text
	 */
	public EditableTextWrapper(int x, int y, int z, String string, VisualObject parent, LogicalObject object, Controller controller) {
		this(x, y, z, string, ".*", parent, object, controller);
	}

	@Override
	public void determineColors(Graphics g) {
		if (!satisfiesRegex())
			this.setColor(Color.RED);
		else
			this.setColor(Color.BLACK);

		if (this.isSelected()) {
			if (!satisfiesRegex())
				this.forceColor(Color.RED);
			else
				this.forceColor(Color.BLACK);
		}
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public void draw(Graphics g) {

		this.getTextObject().draw(g, this.getX(), this.getY());
	}

	/**
	 * @return Returns the text of the Logical Object
	 */
	protected AttributedString getText() {
		StringVisitor strVis = new StringVisitor();
		return strVis.startVisit(this.getLogicalObject());
	}

	/**
	 * @return The current displayed String
	 */
	public final String getCurrentDisplayedString() {
		return this.getTextObject().getText();
	}

	@Override
	void onClick(SingleClick sc) {
		this.getContainer().switchSelectedTo(this);
	}

	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		if (b) {
			this.getTextObject().switchState(new EditableState());
		} else {
			this.getTextObject().switchState(new PassiveState());
			this.quit();
		}

		this.getTextObject().setAttributedText(getText());
	}

	@Override
	void onDoubleClick(DoubleClick sc) {
		this.getContainer().switchSelectedTo(this);
	}

	@Override
	int getWidth() {
		return STANDARD_FONTMETRICS.stringWidth(getString());
	}

	@Override
	int getHeight() {
		return STANDARD_TEXT_HEIGHT;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		this.getTextObject().handleAsciiKey(key);
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		switch (key.getKeyType()) {
		case ENTER:
			if (this.satisfiesRegex()) {
				this.saveAndExit();
				this.getTextObject().handleFunctionKey(key);
			}
			break;
		case ESCAPE:
			this.getTextObject().handleFunctionKey(key);
			this.exit();
			break;
		case DELETE:
			this.getTextObject().handleFunctionKey(key);
			if (!(getTextObject().getState() instanceof EditableState))
				this.getLogicalObject().delete();
			break;
		default:
			this.getTextObject().handleFunctionKey(key);
			break;
		}
	}

	private final String getStandardString() {
		return standardString;
	}

	private final void setStandardString(String standardString) {
		this.standardString = standardString;
	}

	/**
	 * Saves the currently displayed text to the logical object if the Regex is
	 * satisfied and length is > 0.
	 */
	private void save() {
		if (!this.getLogicalObject().getName().matches(this.regex) && !this.satisfiesRegex()) {
			this.getLogicalObject().setName(this.getStandardString());
		} else if (!this.satisfiesRegex()) {
			//It should just reset to the previous
		} else {
			this.getLogicalObject().setName(this.getCurrentDisplayedString());
		}
		this.getTextObject().setAttributedText(getText());

	}

	/**
	 * If the string doesn't match the regex, the string will be discarded
	 */
	private void quit() {
		if (!this.getLogicalObject().getName().matches(this.regex)) {
			this.getLogicalObject().setName(this.getStandardString());
			this.getTextObject().setAttributedText(getText());
		}
	}
	
	/**
	 * To safely deselect this object
	 */
	private void exit(){
		this.quit();
		this.getContainer().switchSelectedTo(null);
	}

	/**
	 * Saves the text if it matches the constraints and exits
	 */
	private void saveAndExit() {
		this.save();
		this.exit();
	}

	/**
	 * @return the regex of this text
	 */
	private final String getRegex() {
		return regex;
	}

	/**
	 * Sets the regex for this text
	 * @param 	regex
	 * 			the new regex
	 */
	private final void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * Checks if the text satisfies the regex
	 * @return	true if the text matches the regex, false otherwise
	 */
	private final boolean satisfiesRegex() {
		return this.getCurrentDisplayedString().matches(this.getRegex());
	}
}
