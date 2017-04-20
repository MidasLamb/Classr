package visualobjects;

import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedString;

import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.text.Text;
import gui.text.state.EditableState;
import gui.text.state.PassiveState;
import logicalobjects.LogicalObject;
import logicalobjects.StringVisitor;

/**
 * A wrapper for the GUI Text to adapt it to the application part.
 * 
 * @author midas
 *
 */
public class EditableTextWrapper extends TextWrapper {
	private String standardString;
	private String regex;

	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object) {
		super(x, y, z, parent, object);
		setLogicalObject(object);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState()));
		this.setStandardString(string);
		this.setRegex(regex);
		if (!this.getStandardString().matches(this.getRegex())) {
			throw new RuntimeException();
		}
	}

	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object, int maxWidth) {
		super(x, y, z, parent, object);
		setLogicalObject(object);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState(), maxWidth));
		this.setStandardString(string);
		this.setRegex(regex);
	}

	public EditableTextWrapper(int x, int y, int z, String string, VisualObject parent, LogicalObject object) {
		this(x, y, z, string, ".*", parent, object);
	}

	@Override
	public void determinColors(Graphics g) {
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

	private void quit() {
		if (!this.getLogicalObject().getName().matches(this.regex)) {
			this.getLogicalObject().setName(this.getStandardString());
			this.getTextObject().setAttributedText(getText());
		}
	}
	
	private void exit(){
		this.quit();
		this.getContainer().switchSelectedTo(null);
	}

	private void saveAndExit() {
		this.save();
		this.exit();
	}

	private final String getRegex() {
		return regex;
	}

	private final void setRegex(String regex) {
		this.regex = regex;
	}

	private final boolean satisfiesRegex() {
		return this.getCurrentDisplayedString().matches(this.getRegex());
	}
}
