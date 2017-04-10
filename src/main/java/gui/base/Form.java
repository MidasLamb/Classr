package gui.base;

import java.awt.Graphics;
import java.util.TreeSet;

import inputHandlers.Typable;
import inputHandlers.clicks.MouseClick;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public class Form{
	private TreeSet<FormObject> formObjects = new TreeSet<>();
	private FormObject focusedObject;

	private final int width, height;
	
	public Form(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void addFormObject(FormObject formObject) {
		getFormObjects().add(formObject);
	}
	
	private TreeSet<FormObject> getFormObjects() {
		return formObjects;
	}
	
	public void handleClick(MouseClick click){
		this.getFormObjects().forEach(formObject -> formObject.handleClick(click));
	}
	
	public void draw(Graphics g){
		this.getFormObjects().forEach(formObject -> formObject.draw(g));
	}
	
	public void handleAsciiKey(AsciiKey key) {
		getFormObjects().stream().filter(x -> x instanceof Typable)
			.map(x -> (Typable) x).forEach(x -> x.handleAsciiKey(key));
	}

	public void handleFunctionKey(FunctionKey key) {
		getFormObjects().stream().filter(x -> x instanceof Typable)
		.map(x -> (Typable) x).forEach(x -> x.handleFunctionKey(key));
	}
	
	//Getters and setters

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
