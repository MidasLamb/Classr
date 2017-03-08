package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import main.Constants;
import mouse.clicks.DoubleClick;
import objects.Association;
import objects.Attribute;
import objects.Method;
import objects.RealClass;
import visualobjects.PaddingBox;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {

	public VisualClass(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		setLogicalObject(new RealClass());
		getLogicalObject().setVisualObject(this);
		this.setName(new PaddingBox(this.getX(), this.getY(), 5, this, "Nieuwe klasse", getLogicalObject()));
		this.addChild(this.getName());
		this.updateDimensions();

		// AH should be child of visualClass, because it has no logical
		// counterpart
		AssociationHandle ah = new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight() / 2, 0, this);
		this.addChild(ah);
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

		//g.fillRect(this.getX(), y, this.getWidth(), Constants.CLASS_WHITE_SPACE);
		y += Constants.CLASS_WHITE_SPACE;
		g.drawLine(this.getX(), y, this.getX() + this.getWidth(), y);
		
		for (VisualObject t : this.getMethods()) {
			y += t.getHeight();
		}
		//g.fillRect(this.getX(), y, this.getWidth(), Constants.CLASS_WHITE_SPACE);
	}

	/**
	 * @post calculates the height of this object and sets it
	 */
	public void updateDimensions() {
		// TODO update
		int y = this.getY();

		y += this.getName().getHeight();

		for (VisualObject t : this.getAttributes()) {
			t.setY(y);
			y += t.getHeight();
		}

		y += Constants.CLASS_WHITE_SPACE;

		for (VisualObject t : this.getMethods()) {
			t.setY(y);
			y += t.getHeight();
		}

		y += Constants.CLASS_WHITE_SPACE;

		this.setHeight(y - this.getY());
	}

	/**
	 * Create a new attribute PaddingBox
	 * 
	 * @return PaddingBox of the attribute that was created
	 */
	private PaddingBox createAttribute() {
		Attribute attr = getLogicalObject().addAttribute();
		PaddingBox t = new PaddingBox(this.getX(), this.getY(), Constants.Z_PADDING_BOX , this.getContainer(),"Nieuw Attribuut", attr);
		this.getContainer().addChild(t);
		attr.setVisualObject(t);
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
		PaddingBox t = new PaddingBox(this.getX(), this.getY(), Constants.Z_PADDING_BOX, this.getContainer(), "Nieuwe Methode", method);
		this.getContainer().addChild(t);
		method.setVisualObject(t);
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
		bottom += Constants.CLASS_WHITE_SPACE;

		return VisualClass.isBetween(left, right, x) && VisualClass.isBetween(top, bottom, y);
	}

	private Collection<VisualObject> getAttributes() {
		// TODO maybe change?
		Collection<Attribute> co = ((RealClass) this.getLogicalObject()).getAttributes();
		Collection<VisualObject> vo = new ArrayList<VisualObject>();
		for (Attribute a : co) {
			vo.add(a.getVisualObject());
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
		top += Constants.CLASS_WHITE_SPACE;
		bottom += Constants.CLASS_WHITE_SPACE;

		for (VisualObject t : this.getMethods()) {
			top += t.getHeight();
			bottom += t.getHeight();
		}

		bottom += Constants.CLASS_WHITE_SPACE;

		return isBetween(left, right, x) && isBetween(top, bottom, y);
	}

	private Collection<VisualObject> getMethods() {
		// TODO Auto-generated method stub
		Collection<Method> co = ((RealClass) this.getLogicalObject()).getMethods();
		Collection<VisualObject> vo = new ArrayList<VisualObject>();
		for (Method m : co) {
			vo.add(m.getVisualObject());
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
		if (v.equals(this.getName()))
			this.delete();
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
		Collection<Association> co = ((RealClass) this.getLogicalObject()).getAssociations();
		for (Association a : co) {
			((RealClass) this.getLogicalObject()).deleteAssociation(a);
			a.getVisualObject().delete();
		}
		
		Collection<Attribute> ca = ((RealClass) this.getLogicalObject()).getAttributes();
		for (Attribute a: ca){
			this.getContainer().removeChild(a.getVisualObject());
		}
		
		Collection<Method> cm = ((RealClass) this.getLogicalObject()).getMethods();
		for (Method m: cm){
			this.getContainer().removeChild(m.getVisualObject());
		}
	}
	
	//Getters and setters
	
	public PaddingBox getName() {
		return name;
	}

	private void setName(PaddingBox name) {
		this.name = name;
	}

	private PaddingBox name;

}