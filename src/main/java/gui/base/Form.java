package gui.base;

import java.awt.Graphics;
import java.util.TreeSet;

import inputHandlers.Clickable;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.MouseClick;
import inputHandlers.clicks.SingleClick;

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
}
