package visualobjects;

import java.awt.Graphics;

import command.Controller;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.form.base.FormObject;
import gui.inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalVoid;

/**
 *
 * @param <T>
 */
public class FormObjectWrapper<T extends FormObject> extends VisualObject<LogicalVoid> {
	T fo;

	public FormObjectWrapper(T fo, int x, int y, int z, int width, int height, VisualObject<?> parent,
			Controller controller) {
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
		int translateX = this.getX();
		int translateY = this.getY();
		g.translate(translateX, translateY);
		this.getFormObject().draw(g);
		g.translate(-translateX, -translateY);
	}

	@Override
	void onClick(SingleClick click) {
		click.translate(this.getX(), this.getY());
		T f = this.getFormObject();
		f.onClick(click);
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return visitor.visit(this);
	}
}
