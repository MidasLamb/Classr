package gui.form.base;

import java.awt.Graphics;
import java.util.Optional;
import java.util.TreeSet;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.FunctionTypable;
import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

/**
 * Form in which FormObjects can be placed.
 */
public class Form implements Typable, Clickable {
	private TreeSet<FormObject> formObjects = new TreeSet<>();
	private FormObject focusedObject;

	private int width, height;
	private final int startWidth, startHeight;

	/**
	 * Create a new Form and set its dimensions.
	 * 
	 * @param width
	 * @param height
	 */
	public Form(int width, int height) {
		this.width = width;
		this.height = height;
		this.startHeight = height;
		this.startWidth = width;
	}

	/**
	 * Add a new FormObject to this Form.
	 * 
	 * @param formObject
	 *            FormObject to be added.
	 */
	public void addFormObject(FormObject formObject) {
		getFormObjects().add(formObject);
	}

	/**
	 * @return TreeSet of all FormObjects in this Form.
	 */
	private TreeSet<FormObject> getFormObjects() {
		return formObjects;
	}

	/**
	 * Handle a click by passing it to all its FormObjects.
	 * 
	 * @param click
	 */
	public void handleClick(MouseClick click) {
		setFocusedObject(null);

		this.getFormObjects().forEach(formObject -> formObject.handleClick(click));

		Optional<FormObject> maybeFocused = getFormObjects().stream().filter(s -> s.isFocused()).findFirst();
		if (maybeFocused.isPresent()) {
			FormObject focused = maybeFocused.get();
			this.setFocusedObject(focused);

		} else {
			// Check for the children of formobjectswithchildren.
			maybeFocused = getFormObjects().stream().filter(s -> (s instanceof FormObjectWithChildren))
					.filter(x -> ((FormObjectWithChildren) x).getFocusedChild() != null).findFirst();
			if (maybeFocused.isPresent()) {
				FormObjectWithChildren focused = (FormObjectWithChildren) maybeFocused.get();
				this.setFocusedObject(focused.getFocusedChild());
			} else {
				this.setFocusedObject(null);
			}
		}
	}

	/**
	 * Draw this Form and its FormObjects.
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		this.getFormObjects().forEach(formObject -> formObject.draw(g));
	}

	/**
	 * Handle an ASCII keystroke.
	 * 
	 * @param key
	 *            AsciiKey to handle
	 */
	@Override
	public void handleAsciiKey(AsciiKey key) {
		getFormObjects().stream().filter(x -> x instanceof Typable).map(x -> (Typable) x)
				.forEach(x -> x.handleAsciiKey(key));
	}

	/**
	 * Handle a function keystroke.
	 * 
	 * @param key
	 *            FunctionKey to handle
	 */
	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (key.isArrowKey()) {
			// Handle key in form
			if (key.getKeyType().equals(FunctionKeyType.LEFT) || key.getKeyType().equals(FunctionKeyType.UP)) {
				FormObject previous;
				if (getFocusedObject() != null && ((previous = getPreviousFormObject(getFocusedObject())) != null)) {
					setFocusedObject(previous);
				}
			} else if (key.getKeyType().equals(FunctionKeyType.RIGHT)
					|| key.getKeyType().equals(FunctionKeyType.DOWN)) {
				FormObject next;
				if (getFocusedObject() != null && ((next = getNextFormObject(getFocusedObject())) != null)) {
					setFocusedObject(next);
				} else if (getFocusedObject() == null) {
					FormObject first = getFormObjects().first();
					if (first != null && !first.isFocusable()) {
						if ((first = getNextFormObject(first)) != null) {
							setFocusedObject(first);
						}
					} else {
						setFocusedObject(first);
					}
				}
			}
		} else {
			// Send keystroke to children
			getFormObjects().stream().filter(x -> x instanceof FunctionTypable).map(x -> (FunctionTypable) x)
					.forEach(x -> x.handleFunctionKey(key));
		}
	}

	/**
	 * Get the FormObject that comes before the given FormObject in this Form
	 * 
	 * @param current
	 *            FormObject of which the previous FormObject is wanted
	 * @return FormObject that comes before the given FormObject
	 */
	private FormObject getPreviousFormObject(FormObject current) {
		if (current instanceof FormObjectChild) {
			FormObjectChild foc = (FormObjectChild) current;
			if (foc.hasPreviousSibling())
				return foc.getPreviousSibling();
			else {
				current = foc.getParent();
			}
		}
		FormObject previous = getFormObjects().lower(current);
		while (previous != null && !previous.isFocusable()) {
			previous = getFormObjects().lower(previous);
		}
		if (previous instanceof FormObjectWithChildren) {
			FormObjectWithChildren fowc = (FormObjectWithChildren) previous;
			if (fowc.hasChildren())
				return fowc.getLastChild();
			else
				return getPreviousFormObject(previous);
		}
		return previous;
	}

	/**
	 * Get the FormObject that comes after the given FormObject in this Form
	 * 
	 * @param current
	 *            FormObject of which the next FormObject is wanted
	 * @return FormObject that comes after the given FormObject
	 */
	private FormObject getNextFormObject(FormObject current) {
		if (current instanceof FormObjectChild) {
			FormObjectChild foc = (FormObjectChild) current;
			if (foc.hasNextSibling())
				return foc.getNextSibling();
			else {
				current = foc.getParent();
			}
		}

		FormObject next = getFormObjects().higher(current);
		while (next != null && !next.isFocusable()) {
			next = getFormObjects().higher(next);
		}
		if (next instanceof FormObjectWithChildren) {
			FormObjectWithChildren fowc = (FormObjectWithChildren) next;
			if (fowc.hasChildren())
				return fowc.getFirstChild();
			else
				return getNextFormObject(next);
		}
		return next;
	}

	// Getters and setters
	/**
	 * @return width
	 */
	public int getWidth() {
		determineWidth();
		return width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		determineHeight();
		return height;
	}

	/**
	 * @return the focused object of this Form
	 */
	private FormObject getFocusedObject() {
		return focusedObject;
	}

	/**
	 * Set the focused object of this Form
	 * 
	 * @param focusedObject
	 *            FormObject to set as the focused object of this Form
	 */
	private void setFocusedObject(FormObject focusedObject) {
		if (this.getFocusedObject() != null) {
			this.getFocusedObject().setFocused(false);
		}
		this.focusedObject = focusedObject;
		if (focusedObject != null) {
			focusedObject.setFocused(true);
		}
	}

	@Override
	public void onClick(SingleClick click) {
		this.handleClick(click);

	}

	@Override
	public void onDoubleClick(DoubleClick click) {
		this.handleClick(click);

	}

	@Override
	public void onDragEnd(Drag drag) {

	}

	@Override
	public void onDragUpdate(Drag drag) {

	}

	/**
	 * Clears the focus.
	 */
	public void clearFocus() {
		this.setFocusedObject(null);
	}

	/**
	 * Determines the width of the Form.
	 */
	private void determineWidth() {
		int mostRightX = 0;
		for (FormObject fo : getFormObjects()) {
			mostRightX = Math.max(mostRightX, fo.getX() + fo.getWidth());
		}
		this.setWidth(Math.max(mostRightX + 5, getStartWidth()));
	}

	/**
	 * Determines the height of the Form
	 */
	private void determineHeight() {
		int mostBottomY = 0;
		for (FormObject fo : getFormObjects()) {
			mostBottomY = Math.max(mostBottomY, fo.getY() + fo.getHeight());
		}
		this.setHeight(Math.max(mostBottomY + 5, getStartHeight()));
	}

	/**
	 * @param width
	 *            the width to set
	 */
	private final void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	private final void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the startWidth
	 */
	private final int getStartWidth() {
		return startWidth;
	}

	/**
	 * @return the startHeight
	 */
	private final int getStartHeight() {
		return startHeight;
	}

}
