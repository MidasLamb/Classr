package visualobjects;

import static main.Constants.CLASS_WIDTH;
import static main.Constants.STANDARD_PADDING;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Graphics;

import inputHandlers.clicks.SingleClick;
import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import logicalobjects.LogicalObject;

public class PaddingBox<T extends VisualObject> extends VisualObject {
	private int paddingTop;
	private int paddingBottom;
	private int paddingLeft;
	private int paddingRight;
	private T content;
	
	public PaddingBox(int x, int y, int z, int width, int height, int paddingLeft, int paddingRight, int paddingTop,
			int paddingBottom,T content, VisualObject parent, LogicalObject object) {
		super(x, y, -10, width, height, parent);
		setLogicalObject(object);
		this.setPaddingLeft(paddingLeft);
		this.setPaddingRight(paddingRight);
		this.setPaddingTop(paddingTop);
		this.setPaddingBottom(paddingBottom);
		this.setContent(content);
		VisualObject tp = content.getParent();
		if (tp != null)
			tp.removeChild(content);
		content.setParent(this);
		this.addChild(content);

		this.content.addDeleteListener(this);
	}
	
	public PaddingBox(int x, int y, int z, int width, int height, int padding,T content, VisualObject parent, LogicalObject object) {
		this(x, y, z, width, height, padding, padding, padding, padding,content, parent, object);
	}


	public PaddingBox(int x, int y, int z, int padding,T content, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding, STANDARD_PADDING,content, parent, object);
	}

	public PaddingBox(int x, int y, int z,T content, VisualObject parent, LogicalObject object) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING), STANDARD_PADDING,content, parent, object);
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
	
	@Override
	public void show(Graphics g) {
		g.translate(this.getX() + this.getPaddingLeft(), this.getY() + this.getPaddingTop());
		super.show(g);
		g.translate(-(this.getX() + this.getPaddingLeft()), -(this.getY() + this.getPaddingTop()));
		
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

	public final T getContent() {
		return content;
	}

	private void setContent(T content) {
		this.content = content;
	}
	
	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		if (b)
			this.getContainer().switchSelectedTo(this.getContent());
	}
	
	@Override
	void onClick(SingleClick sc){
		this.getContent().onClick(sc);
	}
}
