package visualobjects;
import java.awt.Graphics;

import gui.form.base.*;

public class FormObjectWrapper<T extends FormObject> extends VisualObject {
	T fo;
	public FormObjectWrapper(T fo, int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		this.fo = fo;
	}
	private final T getFormObject() {
		return fo;
	}
	private final void setFormObject(T fo) {
		this.fo = fo;
	}
	@Override
	void draw(Graphics g) {
		this.getFormObject().draw(g);
	}
	
	

}
