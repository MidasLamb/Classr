package visualobjects;

import static main.Constants.CLASS_BODY_INITIAL_HEIGHT;
import static main.Constants.CLASS_WHITE_SPACE;
import static main.Constants.CLASS_WIDTH;
import static main.Constants.Z_PADDING_BOX;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import command.Command;
import command.Controller;
import command.CreateAttributeCommand;
import command.CreateMethodCommand;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.Method;

/**
 * The visualization of a logicalClass
 */
public class VisualClass extends ResizableAndMovableVisualObject{
	private PaddingBox<EditableTextWrapper> name;
	private AssociationHandle associationHandle;


	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param parent
	 *            the parent of this VisualObject
	 */
	public VisualClass(int x, int y, int z, int width, int height, VisualObject parent, Controller controller) {
		super(x, y, z, width, height, parent, controller);
		setLogicalObject(new LogicalClass());
		// TODO clean up this -1 to indicate nog max width exists.
		this.setName(new PaddingBox<EditableTextWrapper>(getX(), getY(), 0, new EditableTextWrapper(0, 0, 0, "Klasse",
				"^[A-Z][a-zA-Z0-9_]*", null, getLogicalObject(), -1, getController()), this, getLogicalObject(),
				getController()));
		this.getContainer().switchSelectedTo(this.getName().getContent());
		this.updateDimensions();

		this.getName().addDeleteListener(this);
		this.setAssociationHandle(
				new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight() / 2, 0, this, getController()));
		getLogicalObject().addUpdateListener(this);
	}

	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @param z
	 *            the z-coordinate
	 * @param parent
	 *            the parent of this VisualObject
	 */
	public VisualClass(int x, int y, int z, VisualObject parent, Controller controller) {
		this(x, y, z, CLASS_WIDTH, CLASS_BODY_INITIAL_HEIGHT, parent, controller);
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

		if (y - this.getY() > this.getHeight()) {
			this.setHeight(y - this.getY());
		}
	}

	/**
	 * Create a new attribute PaddingBox
	 * 
	 * @return PaddingBox of the attribute that was created
	 */
	public PaddingBox<TextWrapper> createAttribute() {
		Attribute attr = getLogicalObject().addAttribute();
		TextWrapper t = new TextWrapper(0, 0, 0, null, attr, getController());
		PaddingBox<TextWrapper> tbox = new PaddingBox<TextWrapper>(this.getX(), this.getY(), Z_PADDING_BOX, t, this,
				attr, getController());
		this.updateDimensions();
		getContainer().switchSelectedTo(tbox.getContent());
		tbox.getContent().openNewForm();
		return tbox;
	}

	/**
	 * Create a new method PaddingBox
	 * 
	 * @return PaddingBox of the method that was created
	 */
	public PaddingBox<TextWrapper> createMethod() {
		Method method = getLogicalObject().addMethod();
		TextWrapper t = new TextWrapper(0, 0, 0, null, method, getController());
		PaddingBox<TextWrapper> tbox = new PaddingBox<TextWrapper>(this.getX(), this.getY(), Z_PADDING_BOX, t, this,
				method, getController());
		this.updateDimensions();
		this.getContainer().switchSelectedTo(tbox.getContent());
		tbox.getContent().openNewForm();
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

	/**
	 * @return the attributes from this VisualClass
	 */
	private Collection<VisualObject> getAttributes() {
		Collection<Attribute> attr = ((LogicalClass) this.getLogicalObject()).getAttributes();
		return getChildren().stream().filter(x -> attr.contains(x.getLogicalObject())).collect(Collectors.toList());
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

	/**
	 * 
	 * @return the methods inside this VisualClass
	 */
	private Collection<VisualObject> getMethods() {
		Collection<Method> meth = ((LogicalClass) this.getLogicalObject()).getMethods();
		return getChildren().stream().filter(x -> meth.contains(x.getLogicalObject())).collect(Collectors.toList());
	}

	@Override
	public final void onDoubleClick(DoubleClick dc) {
		if (this.isInEmptyAttribute(dc.getX(), dc.getY())) {
			Command c = new CreateAttributeCommand(this);
			getController().executeCommand(c);
		} else if (this.isInEmptyMethod(dc.getX(), dc.getY())) {
			Command c = new CreateMethodCommand(this);
			getController().executeCommand(c);
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

	/**
	 * 
	 * @param object
	 *            the logicalObject to which this VisualObject is associated
	 */
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

	}

	/**
	 * @return the paddingBox inside which the className is stored
	 */
	public final PaddingBox<EditableTextWrapper> getName() {
		return name;
	}

	/**
	 * 
	 * @param paddingBox
	 *            the new paddingBox inside which the className needs to be
	 *            stored
	 */
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

	@Override
	public boolean isOnLeftSide(int x, int y) {
		if (!this.getAssociationHandle().isIn(x, y)) {
			return super.isOnLeftSide(x, y);
		} else {
			return false;
		}
	}

	private final AssociationHandle getAssociationHandle() {
		return associationHandle;
	}

	private final void setAssociationHandle(AssociationHandle associationHandle) {
		this.associationHandle = associationHandle;
	}

	@Override
	protected boolean isInMoveActivator(int x, int y) {
		return getName().isIn(x, y);
	}

	@Override
	public int getMinimumWidth() {
		// TODO also implement for other stuff.
		int minWidth = 0;
		if (this.getLogicalObject() != null) {
			for (VisualObject m : this.getMethods()) {
				if (m.getWidth() > minWidth) {
					minWidth = m.getWidth();
				}
			}

			for (VisualObject a : this.getAttributes()) {
				if (a.getWidth() > minWidth) {
					minWidth = a.getWidth();
				}
			}
		}
		if (this.getName() != null && this.getName().getWidth() > minWidth) {
			minWidth = this.getName().getWidth();
		}

		return minWidth;
	}

	@Override
	public int getMinimumHeight() {
		int y = 0;
		if (this.getName() != null)
			y += this.getName().getHeight();

		if (this.getLogicalObject() != null) {
			for (VisualObject t : this.getAttributes()) {
				y += t.getHeight();
			}
			for (VisualObject t : this.getMethods()) {
				y += t.getHeight();
			}
		}

		y += CLASS_WHITE_SPACE;
		y += CLASS_WHITE_SPACE;
		return y;
	}

	
	
	

}