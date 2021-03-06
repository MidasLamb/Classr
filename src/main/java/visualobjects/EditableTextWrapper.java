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
import command.DeleteVisualObjectCommand;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
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
public class EditableTextWrapper<L extends LogicalObject> extends TextWrapper<L>
		implements UpdateListener, UpdateSubject, Editable {
	private Collection<UpdateListener> updateListeners;
	private boolean wrongName;

	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param parent
	 *            the VisualObject which is the parent of this object
	 * @param object
	 *            the logicalObject which is linked to this text
	 * @param controller
	 *            the controller for this object.
	 */
	public EditableTextWrapper(int x, int y, int z, VisualObject<?> parent, L object, Controller controller) {
		super(x, y, z, parent, object, controller);
		setLogicalObject(object);
		this.getLogicalObject().addUpdateListener(this);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState()));
		this.setUpdateListeners(new ArrayList<UpdateListener>());
		this.getTextObject().setAttributedText(getText());
		this.setWrongName(false);
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		if (this.isSelected() && (this.getTextObject().isEditable()) || this.isWrongName()) {
			if (!getLogicalObject().canHaveAsName(getCurrentDisplayedString()))
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
		}
		this.getTextObject().draw(g, this.getX(), this.getY());
		g.setColor(c);
	}

	/**
	 * @return Returns the text of the Logical Object
	 */
	protected AttributedString getText() {
		if (getTextObject().getState() instanceof PassiveState) {
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
		return this.getTextObject().getTextAsString();
	}

	@Override
	void onClick(SingleClick sc) {
		if (this.isSelected()) {
			this.makeEditable();
		} else {
			this.getContainer().switchSelectedTo(this);
		}
	}

	@Override
	public void setEditable() {
		this.makeEditable();
	}

	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		if (!b) {
			this.makeUneditable();
			if (getLogicalObject().canHaveAsName(getCurrentDisplayedString()))
				this.getTextObject().setAttributedText(getText());

		} else {

		}
	}

	@Override
	int getWidth() {
		return Math.max(STANDARD_FONTMETRICS.stringWidth(getString()),
				STANDARD_FONTMETRICS.stringWidth(getCurrentDisplayedString()));
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
		setWrongName(!getLogicalObject().canHaveAsName(getCurrentDisplayedString()));
		this.notifyUpdateListeners();
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		switch (key.getKeyType()) {
		case ENTER:
		case ESCAPE:
			this.getTextObject().handleFunctionKey(key);
			this.exit();
			break;
		case DELETE:
			this.getTextObject().handleFunctionKey(key);
			if (!(getTextObject().getState() instanceof EditableState))
				getController().executeCommand(new DeleteVisualObjectCommand(this));
			break;
		case BACKSPACE:
			this.getTextObject().handleFunctionKey(key);
			if (this.getLogicalObject().canHaveAsName(getCurrentDisplayedString()))
				this.save();
			setWrongName(!getLogicalObject().canHaveAsName(getCurrentDisplayedString()));
			break;
		default:
			this.getTextObject().handleFunctionKey(key);
			break;
		}
		this.notifyUpdateListeners();

	}

	/**
	 * Saves the currently displayed text to the logical object if the
	 * conditions on the name of the logical object are satisfied.
	 */
	private void save() {

		if (this.getLogicalObject().canHaveAsName(getCurrentDisplayedString())) {
			getController().executeCommand(
					new ChangeLogicalObjectNameCommand(getLogicalObject(), getCurrentDisplayedString()));
		}
		this.getTextObject().setAttributedText(getText());

	}

	/**
	 * To safely deselect this object
	 */
	private void exit() {
		this.getContainer().switchSelectedTo(null);
	}

	@Override
	public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
		if (updateSubject.equals(getLogicalObject())) {
			this.getTextObject().setAttributedText(getText());
			this.setWrongName(false);
		}
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

	/**
	 * Returns the update listeners
	 * 
	 * @return the update listeners
	 */
	private final Collection<UpdateListener> getUpdateListeners() {
		return updateListeners;
	}

	/**
	 * Sets the update listeners
	 * 
	 * @param updateListeners
	 *            the new update listeners
	 */
	private final void setUpdateListeners(Collection<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
	}

	/**
	 * Makes the text back editable
	 */
	private final void makeEditable() {
		this.getContainer().switchSelectedTo(this);
		if (!this.isWrongName()) {
			this.getTextObject().setAttributedText(new AttributedString(getLogicalObject().getName()));
		}
		this.getTextObject().switchState(new EditableState());

	}

	/**
	 * Puts the editable text in passiveState, so you cannot edit it anymore
	 */
	private final void makeUneditable() {
		this.getTextObject().switchState(new PassiveState());
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public boolean isEditable() {
		return getTextObject().isEditable();
	}

	/**
	 * @return whether or not the name is wrong.
	 */
	private final boolean isWrongName() {
		return wrongName;
	}

	/**
	 * @param wrongName
	 *            the wrongName to set
	 */
	private final void setWrongName(boolean wrongName) {
		this.wrongName = wrongName;
	}
}
