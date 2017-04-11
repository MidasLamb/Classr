package gui.base;

import java.awt.Graphics;
import java.util.TreeSet;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

/**
 * Form in which FormObjects can be placed.
 */
public class Form {
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

	private TreeSet<FormObject> getFormObjects() {
		return formObjects;
	}

	/**
	 * Handle a click by passing it to all its FormObjects.
	 * 
	 * @param click
	 */
	public void handleClick(MouseClick click) {
		this.getFormObjects().forEach(formObject -> formObject.handleClick(click));
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
	public void handleAsciiKey(AsciiKey key) {
		getFormObjects().stream().filter(x -> x instanceof Typable).map(x -> (Typable) x)
				.forEach(x -> x.handleAsciiKey(key));
	}

	/**
	 * Handle a function keystroke.
	 * 
	 * @param key
	 */
	public void handleFunctionKey(FunctionKey key) {
		getFormObjects().stream().filter(x -> x instanceof Typable).map(x -> (Typable) x)
				.forEach(x -> x.handleFunctionKey(key));
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
		this.focusedObject = focusedObject;
	}
}
