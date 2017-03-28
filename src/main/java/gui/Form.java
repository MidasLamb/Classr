package gui;

import java.util.ArrayList;
import java.util.Comparator;

public class Form {
	private ArrayList<FormObject> formObjects = new ArrayList<>();

	public Form() {
		// TODO Auto-generated constructor stub
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
}
