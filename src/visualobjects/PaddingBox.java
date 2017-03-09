package visualobjects;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import static main.Constants.*;
import objects.Attribute;
import objects.LogicalObject;
import objects.Method;
import visualobjects.visualclass.VisualClass;

public class PaddingBox extends VisualObject {

	/**
	 * @param x
	 *            Coordinate on the x-axis of the top-left corner
	 * @param y
	 *            Coordinate on the y-axis of the top-left corner
	 * @param width
	 *            Width of the TextBox
	 * @param height
	 *            Height of the TextBox
	 * @param paddingLeft
	 *            Padding at the left side
	 * @param paddingRight
	 *            Padding at the right side
	 * @param paddingTop
	 *            Padding at the top
	 * @param paddingBottom
	 *            Padding at the bottom
	 * @param parent
	 *            VisualObject which is the parent of the object to be created
	 * @param standardstring
	 *            String that is shown as text by default
	 * @param object
	 *            Logical object of which this TextBox is the textual
	 *            representation
	 */
	public PaddingBox(int x, int y, int z, int width, int height, int paddingLeft, int paddingRight, int paddingTop,
			int paddingBottom, VisualObject parent, String standardstring, LogicalObject object) {
		super(x, y, -10, width, height, parent);
		setLogicalObject(object);
		this.setPaddingLeft(paddingLeft);
		this.setPaddingRight(paddingRight);
		this.setPaddingTop(paddingTop);
		this.setPaddingBottom(paddingBottom);
		this.setContent(new Text(this.getX() + this.getPaddingLeft(), this.getY() + this.getPaddingTop(),
				this.getZ() + 1, this, standardstring, getLogicalObject()));
		this.addChild(this.getContent());
	}

	/**
	 * @param x
	 *            Coordinate on the x-axis of the top-left corner
	 * @param y
	 *            Coordinate on the y-axis of the top-left corner
	 * @param width
	 *            Width of the TextBox
	 * @param height
	 *            Height of the TextBox
	 * @param padding
	 *            Size of padding at all sides
	 * @param parent
	 *            VisualObject which is the parent of the object to be created
	 * @param standardstring
	 *            String that is shown as text by default
	 * @param object
	 *            Logical object of which this TextBox is the textual
	 *            representation
	 */
	public PaddingBox(int x, int y, int z, int width, int height, int padding, VisualObject parent,
			String standardstring, LogicalObject object) {
		this(x, y, z, width, height, padding, padding, padding, padding, parent, standardstring, object);
	}

	public PaddingBox(int x, int y, int z, int padding, VisualObject parent, String standardstring,
			LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding,
				STANDARD_PADDING, parent, standardstring, object);
	}

	public PaddingBox(int x, int y, int z, int padding, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding,
				STANDARD_PADDING, parent, "", object);
	}

	public PaddingBox(int x, int y, int z, VisualObject parent, String standardstring, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING),
				STANDARD_PADDING, parent, standardstring, object);
	}

	public PaddingBox(int x, int y, int z, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING),
				STANDARD_PADDING, parent, "", object);
	}

	@Override
	public void onClick(SingleClick sc) {
		if (!this.isSelected() && !this.getContent().isSelected())
			this.getContainer().switchSelectedTo(this);
		else if (this.isSelected())
			this.getContainer().switchSelectedTo(this.getContent());
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		this.onClick(new SingleClick(dc.getX(), dc.getY()));
	}

	@Override
	public void setY(int y) {
		super.setY(y);
		if (getContent() != null)
			this.getContent().setY(y + this.getPaddingTop());
	}

	@Override
	public int getHeight() {
		return this.getContent().getHeight() + this.getPaddingTop() + this.getPaddingBottom();
	}

	@Override
	public int getWidth() {
		return this.getContent().getWidth() + this.getPaddingLeft() + this.getPaddingRight();
	}

	@Override
	protected void onDelete() {
		if (this.getLogicalObject() instanceof Attribute) {
			((Attribute) this.getLogicalObject()).getRealClass().deleteChild(this.getLogicalObject());
			((VisualClass) ((Attribute) this.getLogicalObject()).getRealClass().getVisualObject()).updateDimensions();
		}

		if (this.getLogicalObject() instanceof Method) {
			((Method) this.getLogicalObject()).getRealClass().deleteChild(this.getLogicalObject());
			((VisualClass) ((Method) this.getLogicalObject()).getRealClass().getVisualObject()).updateDimensions();
		}
	}

	// Getters and setters

	private int getPaddingTop() {
		return paddingTop;
	}

	private void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	private int paddingTop;

	private int getPaddingBottom() {
		return paddingBottom;
	}

	private void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	private int paddingBottom;

	private int getPaddingLeft() {
		return paddingLeft;
	}

	private void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	private int paddingLeft;

	private int getPaddingRight() {
		return paddingRight;
	}

	private void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	private int paddingRight;

	public VisualObject getContent() {
		return content;
	}

	private void setContent(VisualObject content) {
		this.content = content;
	}

	private VisualObject content;
}
