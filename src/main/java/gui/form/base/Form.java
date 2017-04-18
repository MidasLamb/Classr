package gui.form.base;

import java.awt.Graphics;
import java.util.TreeSet;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;

/**
 * Form in which FormObjects can be placed.
 */
public class Form implements Typable, Clickable{
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
