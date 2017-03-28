package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

public class Form extends FormObject {
	private ArrayList<FormObject> formObjects = new ArrayList<>();

	public Form(int x, int y, int width, int height) {
		super(x, y, width, height);
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

	@Override
	void onClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void draw(Graphics g) {
		for (FormObject fo : this.formObjects) {
			fo.draw(g);
		}
	}
}
