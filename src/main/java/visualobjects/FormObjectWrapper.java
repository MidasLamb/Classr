package visualobjects;
import java.awt.Graphics;

import command.Controller;
import gui.form.base.*;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;

public class FormObjectWrapper<T extends FormObject> extends VisualObject {
	T fo;
	public FormObjectWrapper(T fo, int x, int y, int z, int width, int height, VisualObject parent, Controller controller) {
		super(x, y, z, width, height, parent, controller);
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
	
	@Override
	void onClick(SingleClick click){
		T f = this.getFormObject();
		f.onClick(click);
	}
	
	@Override
	boolean isIn(int x, int y){
		return fo.isIn(x, y);
	}
	

}
