package visualobjects;

import static main.Constants.CLASS_WIDTH;
import static main.Constants.STANDARD_PADDING;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import interfaces.DeleteListener;
import objects.LogicalObject;

public class PaddingBox extends VisualObject {
	private int paddingTop;
	private int paddingBottom;
	private int paddingLeft;
	private int paddingRight;
	private VisualObject content;

	/**
	 * @param x
	 *            Coordinate on the x-axis of the top-left corner
	 * @param y
	 *            Coordinate on the y-axis of the top-left corner
	 * @param z
	 *            Coordinate on the z-axis
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

		this.content.addDeleteListener(new DeleteListener() {

			@Override
			public void notifyDelete() {
				delete();

			}
		});
	}

	/**
	 * @param x
	 *            Coordinate on the x-axis of the top-left corner
	 * @param y
	 *            Coordinate on the y-axis of the top-left corner
	 * @param z
	 *            Coordinate on the z-axis
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
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding, STANDARD_PADDING, parent, standardstring,
				object);
	}

	public PaddingBox(int x, int y, int z, int padding, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding, STANDARD_PADDING, parent, "", object);
	}

	public PaddingBox(int x, int y, int z, VisualObject parent, String standardstring, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING), STANDARD_PADDING, parent,
				standardstring, object);
	}

	public PaddingBox(int x, int y, int z, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING), STANDARD_PADDING, parent, "", object);
	}

	@Override
	public final void onClick(SingleClick sc) {
		if (!this.isSelected() && !this.getContent().isSelected())
			this.getContainer().switchSelectedTo(this);
		else if (this.isSelected())
			this.getContainer().switchSelectedTo(this.getContent());
	}

	@Override
	public final void onDoubleClick(DoubleClick dc) {
		this.onClick(new SingleClick(dc.getX(), dc.getY()));
	}

	@Override
	public final void setY(int y) {
		super.setY(y);
		if (getContent() != null)
			this.getContent().setY(y + this.getPaddingTop());
	}

	@Override
	public final int getHeight() {
		return this.getContent().getHeight() + this.getPaddingTop() + this.getPaddingBottom();
	}

	@Override
	public final int getWidth() {
		return this.getContent().getWidth() + this.getPaddingLeft() + this.getPaddingRight();
	}

	// Getters and setters

	private int getPaddingTop() {
		return paddingTop;
	}

	private void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	private int getPaddingBottom() {
		return paddingBottom;
	}

	private void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	private int getPaddingLeft() {
		return paddingLeft;
	}

	private void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	private int getPaddingRight() {
		return paddingRight;
	}

	private void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	public final VisualObject getContent() {
		return content;
	}

	private void setContent(VisualObject content) {
		this.content = content;
	}
}
