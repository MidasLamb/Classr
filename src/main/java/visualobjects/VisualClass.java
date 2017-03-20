package visualobjects;

import static main.Constants.CLASS_WHITE_SPACE;
import static main.Constants.Z_PADDING_BOX;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import inputHandlers.clicks.DoubleClick;
import objects.Attribute;
import objects.Method;
import objects.RealClass;

public class VisualClass extends VisualObject {
	private PaddingBox name;

	public VisualClass(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		setLogicalObject(new RealClass());
		this.setName(new PaddingBox(this.getX(), this.getY(), 5, this, "Nieuwe klasse", getLogicalObject()));
		this.updateDimensions();

		// AH should be child of visualClass, because it has no logical
		// counterpart

		AssociationHandle ah = new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight() / 2, 0, this);
	}

	public VisualClass(int x, int y, int z, VisualObject parent) {
		this(x, y, z, 100, 200, parent);
	}

	@Override
	public void draw(Graphics g) {
		this.updateDimensions();
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		int y = this.getY();

		y += this.getName().getHeight();

		g.drawLine(this.getX(), y, this.getX() + this.getWidth(), y);

		for (VisualObject t : this.getAttributes()) {
			y += t.getHeight();
		}

		// g.fillRect(this.getX(), y, this.getWidth(),
		// Constants.CLASS_WHITE_SPACE);
		y += CLASS_WHITE_SPACE;
		g.drawLine(this.getX(), y, this.getX() + this.getWidth(), y);

		for (VisualObject t : this.getMethods()) {
			y += t.getHeight();
		}
		// g.fillRect(this.getX(), y, this.getWidth(),
		// Constants.CLASS_WHITE_SPACE);
	}

	/**
	 * Calculates the height of this object and sets it
	 */
	public void updateDimensions() {
		// TODO update
		int y = this.getY();

		y += this.getName().getHeight();

		for (VisualObject t : this.getAttributes()) {
			t.setY(y);
			y += t.getHeight();
		}

		y += CLASS_WHITE_SPACE;

		for (VisualObject t : this.getMethods()) {
			t.setY(y);
			y += t.getHeight();
		}

		y += CLASS_WHITE_SPACE;

		this.setHeight(y - this.getY());
	}

	/**
	 * Create a new attribute PaddingBox
	 * 
	 * @return PaddingBox of the attribute that was created
	 */
	private PaddingBox createAttribute() {
		Attribute attr = getLogicalObject().addAttribute();
		PaddingBox t = new PaddingBox(this.getX(), this.getY(), Z_PADDING_BOX, this, "Nieuw Attribuut", attr);
		this.updateDimensions();
		return t;
	}

	/**
	 * Create a new method PaddingBox
	 * 
	 * @return PaddingBox of the method that was created
	 */
	private PaddingBox createMethod() {
		Method method = getLogicalObject().addMethod();
		PaddingBox t = new PaddingBox(this.getX(), this.getY(), Z_PADDING_BOX, this, "Nieuwe Methode", method);
		this.updateDimensions();
		return t;
	}

	/**
	 * @param x
	 *            Coordinate on the x-axis
	 * @param y
	 *            Coordinate on the y-axis
	 * @return Returns true if (x,y) is located in the empty attribute,
	 *         otherwise false
	 */
	private boolean isInEmptyAttribute(int x, int y) {
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();

		bottom += this.getName().getHeight();

		for (VisualObject t : this.getAttributes()) {
			top += t.getHeight();
			bottom += t.getHeight();
		}
		bottom += CLASS_WHITE_SPACE;

		return VisualClass.isBetween(left, right, x) && VisualClass.isBetween(top, bottom, y);
	}

	private Collection<VisualObject> getAttributes() {
		Collection<VisualObject> vo = new ArrayList<VisualObject>();
		// TODO hacky whacky
		for (VisualObject v : this.getChildren()) {
			if (v.getLogicalObject() instanceof Attribute)
				vo.add(v);
		}
		return vo;
	}

	/**
	 * @param x
	 *            Coordinate on the x-axis
	 * @param y
	 *            Coordinate on the y-axis
	 * @return Returns true if (x,y) is located in the empty method, otherwise
	 *         false
	 */
	private boolean isInEmptyMethod(int x, int y) {
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();

		bottom += this.getName().getHeight();

		for (VisualObject t : this.getAttributes()) {
			top += t.getHeight();
			bottom += t.getHeight();
		}
		top += CLASS_WHITE_SPACE;
		bottom += CLASS_WHITE_SPACE;

		for (VisualObject t : this.getMethods()) {
			top += t.getHeight();
			bottom += t.getHeight();
		}

		bottom += CLASS_WHITE_SPACE;

		return isBetween(left, right, x) && isBetween(top, bottom, y);
	}

	private Collection<VisualObject> getMethods() {
		Collection<VisualObject> vo = new ArrayList<VisualObject>();
		// TODO hacky whacky
		for (VisualObject v : this.getChildren()) {
			if (v.getLogicalObject() instanceof Method)
				vo.add(v);
		}
		return vo;
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		if (this.isInEmptyAttribute(dc.getX(), dc.getY())) {
			PaddingBox t = this.createAttribute();
			this.getContainer().switchSelectedTo(t.getContent());

		}
		if (this.isInEmptyMethod(dc.getX(), dc.getY())) {
			PaddingBox t = this.createMethod();
			this.getContainer().switchSelectedTo(t.getContent());
		}
		super.onDoubleClick(dc);
	}

	@Override
	protected void afterDeleteChild(VisualObject v) {
		this.updateDimensions();
	}

	@Override
	public RealClass getLogicalObject() {
		return (RealClass) super.getLogicalObject();
	}

	public void setLogicalObject(RealClass object) {
		super.setLogicalObject(object);
	}

	@Override
	public boolean isIn(int x, int y) {
		for (VisualObject vo : this.getChildren()) {
			if (vo.isIn(x, y))
				return true;
		}
		return super.isIn(x, y);
	}

	@Override
	protected void onDelete() {
		// TODO remove association

	}

	// Getters and setters

	public PaddingBox getName() {
		return name;
	}

	private void setName(PaddingBox name) {
		this.name = name;
	}

}