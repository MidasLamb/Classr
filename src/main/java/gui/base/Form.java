package gui.base;

import java.awt.Graphics;
import java.util.TreeSet;

import inputHandlers.clicks.MouseClick;

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
