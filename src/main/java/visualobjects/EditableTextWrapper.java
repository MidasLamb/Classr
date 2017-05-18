package visualobjects;

import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Collection;

import command.ChangeLogicalObjectNameCommand;
import command.Controller;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.text.Text;
import gui.text.state.EditableState;
import gui.text.state.PassiveState;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.LogicalObject;

/**
 * A wrapper for the GUI Text to adapt it to the application part.
 *
 */
public class EditableTextWrapper extends TextWrapper implements UpdateListener, UpdateSubject {
	private String standardString;
	private String regex;
	private Collection<UpdateListener> updateListeners;

	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param string
	 *            the text
	 * @param regex
	 *            the regex to which the text needs to match
	 * @param parent
	 *            the VisualObject which is the parent of this object
	 * @param object
	 *            the logicalObject which is linked to this text
	 */
	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object, Controller controller) {
		super(x, y, z, parent, object, controller);
		setLogicalObject(object);
		this.getLogicalObject().addUpdateListener(this);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState()));
		this.setStandardString(string);
		this.setRegex(regex);
		if (!this.getStandardString().matches(this.getRegex())) {
			throw new RuntimeException();
		}
		this.setUpdateListeners(new ArrayList<UpdateListener>());

	}

	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param string
	 *            the text
	 * @param regex
	 *            the regex to which the text needs to match
	 * @param parent
	 *            the VisualObject which is the parent of this object
	 * @param object
	 *            the logicalObject which is linked to this text
	 * @param maxWidth
	 *            the maxWidth that this text may have
	 */
	public EditableTextWrapper(int x, int y, int z, String string, String regex, VisualObject parent,
			LogicalObject object, int maxWidth, Controller controller) {
		super(x, y, z, parent, object, controller);
		setLogicalObject(object);
		this.getLogicalObject().addUpdateListener(this);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState(), maxWidth));
		this.setStandardString(string);
		this.setRegex(regex);
	}

	/**
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param string
	 *            the text
	 * @param parent
	 *            the VisualObject which is the parent of this object
	 * @param object
	 *            the logicalObject which is linked to this text
	 */
	public EditableTextWrapper(int x, int y, int z, String string, VisualObject parent, LogicalObject object,
			Controller controller) {
		this(x, y, z, string, ".*", parent, object, controller);
	}

	@Override
	public void determineColors(Graphics g) {
		if (!satisfiesRegex())
			this.setColor(Color.RED);
		else
			this.setColor(Color.BLACK);
		//TODO is this instanceof neccesary?
		if (this.isSelected() && (this.getTextObject().getState() instanceof EditableState)) {
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
		if (getTextObject().getState() instanceof PassiveState){
			StringVisitor strVis = new StringVisitor();
			return strVis.startVisit(this.getLogicalObject());
		} else {
			return new AttributedString(getLogicalObject().getName());
		}
	}

	/**
	 * @return The current displayed String
	 */
	public final String getCurrentDisplayedString() {
		return this.getTextObject().getText();
	}

	@Override
	void onClick(SingleClick sc) {
		if (this.isSelected()) {
			this.makeEditable();
		} else {
			this.getContainer().switchSelectedTo(this);
		}
	}

	public void setEditable() {
		this.makeEditable();
	}

	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		if (!b) {
			this.makeUneditable();
			this.quit();
		}

		this.getTextObject().setAttributedText(getText());
	}

	// @Override
	// void onDoubleClick(DoubleClick sc) {
	// this.getContainer().switchSelectedTo(this);
	// }

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
		if (this.getLogicalObject().canHaveAsName(this.getCurrentDisplayedString()))
			this.save();
		this.notifyUpdateListeners();
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
		case BACKSPACE:
			this.getTextObject().handleFunctionKey(key);
			if (this.getLogicalObject().canHaveAsName(getCurrentDisplayedString()))
				this.save();
			break;
		default:
			this.getTextObject().handleFunctionKey(key);
			break;
		}
		this.notifyUpdateListeners();

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
			// It should just reset to the previous
		} else {
			// this.getLogicalObject().setName(this.getCurrentDisplayedString());
			getController().executeCommand(
					new ChangeLogicalObjectNameCommand(getLogicalObject(), getCurrentDisplayedString()));
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
	private void exit() {
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
	 * 
	 * @param regex
	 *            the new regex
	 */
	private final void setRegex(String regex) {
		this.regex = regex;
	}

	/**
	 * Checks if the text satisfies the regex
	 * 
	 * @return true if the text matches the regex, false otherwise
	 */
	private final boolean satisfiesRegex() {
		return this.getCurrentDisplayedString().matches(this.getRegex());
	}

	@Override
	public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
		this.getTextObject().setAttributedText(getText());
	}

	@Override
	public void addUpdateListener(UpdateListener updateListener) {
		this.getUpdateListeners().add(updateListener);

	}

	@Override
	public void removeUpdateListener(UpdateListener updateListener) {
		this.getUpdateListeners().remove(updateListener);

	}

	@Override
	public void notifyUpdateListeners() {
		if (getUpdateListeners() != null) {
			getUpdateListeners().stream().forEach(x -> x.getNotifiedOfUpdate(this));
		}

	}

	private final Collection<UpdateListener> getUpdateListeners() {
		return updateListeners;
	}

	private final void setUpdateListeners(Collection<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
	}
	
	private final void makeEditable(){
		this.getTextObject().setAttributedText(new AttributedString(getLogicalObject().getName()));
		this.getTextObject().switchState(new EditableState());
	}
	
	private final void makeUneditable(){
		this.getTextObject().switchState(new PassiveState());
	}
}
