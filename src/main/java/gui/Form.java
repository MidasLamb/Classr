package gui;

import java.awt.Graphics;
import java.util.TreeSet;

import inputHandlers.clicks.MouseClick;

public class Form {
	private TreeSet<FormObject> formObjects = new TreeSet<>();

	private final int x, y, width, height;
	
	public Form(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
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
	
	private int getX() {
		return x;
	}

	private int getY() {
		return y;
	}

	private int getWidth() {
		return width;
	}

	private int getHeight() {
		return height;
	}
}
