package visualobjects;

import static main.Constants.CLASS_WIDTH;
import static main.Constants.STANDARD_PADDING;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Graphics;

import command.Controller;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalObject;

/**
 * A box in which the content can be padded.
 * @param 	<T>
 * 			The VisualObject that the paddingBox contains
 */
public class PaddingBox<T extends VisualObject> extends VisualObject {
	private int paddingTop;
	private int paddingBottom;
	private int paddingLeft;
	private int paddingRight;
	private T content;
	
	/**
	 *
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	width
	 * 			the width
	 * @param 	height
	 * 			the height
	 * @param 	paddingLeft
	 * 			the padding on the left	
	 * @param 	paddingRight
	 * 			the padding on the right
	 * @param 	paddingTop
	 * 			the padding on the top
	 * @param 	paddingBottom
	 * 			the padding on the bottom
	 * @param 	content
	 * 			the content that needs to be padded
	 * @param 	parent
	 * 			the parent object for this object
	 * @param 	object
	 * 			the corresponding logicalObject
	 */
	public PaddingBox(int x, int y, int z, int width, int height, int paddingLeft, int paddingRight, int paddingTop,
			int paddingBottom,T content, VisualObject parent, LogicalObject object, Controller controller) {
		super(x, y, -10, width, height, parent, controller);
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
		content.setX(0);
		content.setY(0);
		this.content.addDeleteListener(this);
	}
	
	/**
	 * 
	 *
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	width
	 * 			the width
	 * @param 	height
	 * 			the height
	 * @param 	padding
	 * 			the wanted padding left, right, top en down
	 * @param 	content
	 * 			the content that needs to be padded
	 * @param 	parent
	 * 			the parent object for this object
	 * @param 	object
	 * 			the corresponding logicalObject
	 */
	public PaddingBox(int x, int y, int z, int width, int height, int padding,T content, VisualObject parent, LogicalObject object, Controller controller) {
		this(x, y, z, width, height, padding, padding, padding, padding,content, parent, object, controller);
	}

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	padding
	 * 			the wanted padding left, right, top en down
	 * @param 	content
	 * 			the content that needs to be padded
	 * @param 	parent
	 * 			the parent object for this object
	 * @param 	object
	 * 			the corresponding logicalObject
	 */
	public PaddingBox(int x, int y, int z, int padding,T content, VisualObject parent, LogicalObject object, Controller controller) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + padding + padding, STANDARD_PADDING,content, parent, object, controller);
	}

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	content
	 * 			the content that needs to be padded
	 * @param 	parent
	 * 			the parent object for this object
	 * @param 	object
	 * 			the corresponding logicalObject
	 */
	public PaddingBox(int x, int y, int z,T content, VisualObject<?> parent, LogicalObject object, Controller controller) {
		this(x, y, z, CLASS_WIDTH, STANDARD_TEXT_HEIGHT + (2 * STANDARD_PADDING), STANDARD_PADDING,content, parent, object, controller);
	}

	@Override
	public final int getHeight() {
		return this.getContent().getHeight() + this.getPaddingTop() + this.getPaddingBottom();
	}

	@Override
	public final int getWidth() {
		return this.getContent().getWidth() + this.getPaddingLeft() + this.getPaddingRight();
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

	@Override
	void onDoubleClick(DoubleClick dc){
		this.getContent().onDoubleClick(dc);
	}
	
	@Override
	public void show(Graphics g) {
		g.translate(this.getX() + this.getPaddingLeft(), this.getY() + this.getPaddingTop());
		super.show(g);
		g.translate(-(this.getX() + this.getPaddingLeft()), -(this.getY() + this.getPaddingTop()));
		
	}
	
	@Override
	void onDelete(){
		this.getContent().delete();
	}
	
	// Getters and setters

	/**
	 * @return the top padding
	 */
	private int getPaddingTop() {
		return paddingTop;
	}

	/**
	 * @param 	paddingTop
	 * 			the new top padding
	 */
	private void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	/**
	 * @return the bottom padding
	 */
	private int getPaddingBottom() {
		return paddingBottom;
	}

	/**
	 * @param 	paddingBottom
	 * 			the new paddingBottom
	 */
	private void setPaddingBottom(int paddingBottom) {
		this.paddingBottom = paddingBottom;
	}

	/**
	 * @return the left padding
	 */
	private int getPaddingLeft() {
		return paddingLeft;
	}

	/**
	 * 
	 * @param 	paddingLeft
	 * 			the new left padding
	 */
	private void setPaddingLeft(int paddingLeft) {
		this.paddingLeft = paddingLeft;
	}

	/**
	 * 
	 * @return the right padding
	 */
	private int getPaddingRight() {
		return paddingRight;
	}

	/**
	 * @param 	paddingRight
	 * 			the new right padding
	 */
	private void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}

	/**
	 * @return the content that is in the paddingBox
	 */
	public final T getContent() {
		return content;
	}

	/**
	 * 
	 * @param 	content
	 * 			the content that needs to be in the paddingbox
	 */
	private void setContent(T content) {
		this.content = content;
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return null;
	}
}
