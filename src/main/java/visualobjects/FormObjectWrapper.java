package visualobjects;
import java.awt.Graphics;

import command.Controller;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.form.base.FormObject;
import gui.inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalVoid;

public class FormObjectWrapper<T extends FormObject> extends VisualObject<LogicalVoid> {
	T fo;
	public FormObjectWrapper(T fo, int x, int y, int z, int width, int height, VisualObject<?> parent, Controller controller) {
		super(x, y, z, width, height, parent, controller);
		setFormObject(fo);
	}
	private final T getFormObject() {
		return fo;
	}
	private final void setFormObject(T fo) {
		this.fo = fo;
	}
	@Override
	void draw(Graphics g) {
		this.getContainer().bringToFront(this);
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
	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return null;
	}
	

}
