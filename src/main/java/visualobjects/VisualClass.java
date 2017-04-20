package visualobjects;

import static main.Constants.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.Method;

public class VisualClass extends VisualObject {
	private PaddingBox<EditableTextWrapper> name;

	public VisualClass(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		setLogicalObject(new LogicalClass());
		this.setName(new PaddingBox<EditableTextWrapper>(getX(), getY(), 0, 
				new EditableTextWrapper(0,0,0,"Klasse", "^[A-Z][a-zA-Z0-9_]*",
						null,getLogicalObject(), MAX_TEXT_WIDTH), this, getLogicalObject()));
		this.getContainer().switchSelectedTo(this.getName().getContent());
		this.updateDimensions();

		this.getName().addDeleteListener(this);
		AssociationHandle ah = new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight() / 2, 0, this);
	}

	public VisualClass(int x, int y, int z, VisualObject parent) {
		this(x, y, z, CLASS_WIDTH, CLASS_BODY_INITIAL_HEIGHT, parent);
	}

	@Override
	public final void draw(Graphics g) {
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
	public final void updateDimensions() {
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
	private PaddingBox<TextWrapper> createAttribute() {
		Attribute attr = getLogicalObject().addAttribute();
		TextWrapper t = new TextWrapper(0,0,0, null, attr);
		PaddingBox<TextWrapper> tbox = new PaddingBox<TextWrapper>(this.getX(), this.getY(), Z_PADDING_BOX, t,  this, attr);
		this.updateDimensions();
		return tbox;
	}

	/**
	 * Create a new method PaddingBox
	 * 
	 * @return PaddingBox of the method that was created
	 */
	private PaddingBox<TextWrapper> createMethod() {
		Method method = getLogicalObject().addMethod();
		TextWrapper t = new TextWrapper(0,0,0, null, method);
		PaddingBox<TextWrapper> tbox = new PaddingBox<TextWrapper>(this.getX(), this.getY(), Z_PADDING_BOX, t, this, method);
		this.updateDimensions();
		return tbox;
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
	public final void onDoubleClick(DoubleClick dc) {
		if (this.isInEmptyAttribute(dc.getX(), dc.getY())) {
			PaddingBox<TextWrapper> t = this.createAttribute();
			this.getContainer().switchSelectedTo(t.getContent());
			t.getContent().openNewForm();

		} else if (this.isInEmptyMethod(dc.getX(), dc.getY())) {
			PaddingBox<TextWrapper> t = this.createMethod();
			this.getContainer().switchSelectedTo(t.getContent());
			t.getContent().openNewForm();
		} else
			super.onDoubleClick(dc);
	}

	@Override
	protected final void afterDeleteChild(VisualObject v) {
		this.updateDimensions();
	}

	@Override
	public final LogicalClass getLogicalObject() {
		return (LogicalClass) super.getLogicalObject();
	}

	public final void setLogicalObject(LogicalClass object) {
		super.setLogicalObject(object);
	}

	@Override
	public final boolean isIn(int x, int y) {
		for (VisualObject vo : this.getChildren()) {
			if (vo.isIn(x, y))
				return true;
		}
		return super.isIn(x, y);
	}

	@Override
	protected final void onDelete() {
		// TODO remove association

	}

	// Getters and setters

	public final PaddingBox<EditableTextWrapper> getName() {
		return name;
	}

	private void setName(PaddingBox<EditableTextWrapper> paddingBox) {
		this.name = paddingBox;
	}

	@Override
	protected void onClick(SingleClick sc) {
		if (!this.isSelected() && !this.getName().getContent().isSelected()) {
			if (this.getName().isIn(sc.getX(), sc.getY()))
				this.getContainer().switchSelectedTo(this);
			else
				super.onClick(sc);
		}

		else if (this.isSelected()) {
			if (this.getName().isIn(sc.getX(), sc.getY()))
				this.getContainer().switchSelectedTo(this.getName().getContent());
			else
				super.onClick(sc);
		}

	}

}