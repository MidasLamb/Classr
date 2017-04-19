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

	private final int width, height;

	/**
	 * Create a new Form and set its dimensions.
	 * 
	 * @param width
	 * @param height
	 */
	public Form(int width, int height) {
		this.width = width;
		this.height = height;
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
			this.setFocusedObject(maybeFocused.get());
		} else {
			this.setFocusedObject(null);
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
			} else if (key.getKeyType().equals(FunctionKeyType.RIGHT) || key.getKeyType().equals(FunctionKeyType.DOWN)) {
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
	
	private FormObject getPreviousFormObject(FormObject current) {
		FormObject previous = getFormObjects().lower(current);
		while (previous != null && !previous.isFocusable()) {
			previous = getFormObjects().lower(previous);
		}
		return previous;
	}
	
	private FormObject getNextFormObject(FormObject current) {
		FormObject next = getFormObjects().higher(current);
		while (next != null && !next.isFocusable()) {
			next = getFormObjects().higher(next);
		}
		return next;
	}

	// Getters and setters

	private int getWidth() {
		return width;
	}

	private int getHeight() {
		return height;
	}

	private FormObject getFocusedObject() {
		return focusedObject;
	}

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
}
