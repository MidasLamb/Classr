package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

public class Form {
	private ArrayList<FormObject> formObjects = new ArrayList<>();

	private final int x, y, width, height;
	
	public Form(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	private void sortFormObjects() {
		this.formObjects.sort(new Comparator<FormObject>() {

			@Override
			public int compare(FormObject o1, FormObject o2) {
				int diffY = o1.getY() - o2.getY();
				if (diffY == 0) {
					// sort by x
					return o1.getX() - o2.getX();
				} else {
					return diffY;
				}
			}
		});
	}

	private void addFormObject(FormObject formObject) {
		this.formObjects.add(formObject);
	}
	
	private ArrayList<FormObject> getFormObjects() {
		return new ArrayList<>(this.formObjects);
	}
	
	void draw(Graphics g) {
		for (FormObject fo : this.formObjects) {
			fo.draw(g);
		}
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
