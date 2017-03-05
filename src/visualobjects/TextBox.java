package visualobjects;

import main.Constants;
import mouse.clicks.SingleClick;
import objects.Logical_objects;

public class TextBox extends VisualObject {
	
	public TextBox(int x, int y, int width, int height, int paddingLeft, int paddingRight, 
			int paddingTop, int paddingBottom, VisualObject parent, String standardstring, Logical_objects object){
		super(x, y, width, height, parent);
		setLogicalObject(object);
		this.setPaddingLeft(paddingLeft);
		this.setPaddingRight(paddingRight);
		this.setPaddingTop(paddingTop);
		this.setPaddingBottom(paddingBottom);
		this.setText(new TextHandler(this.getX() + this.getPaddingLeft(),
				this.getY() + this.getPaddingTop(), this, standardstring, getLogicalObject()));
		this.addChild(this.getText());
	}
	
	public TextBox(int x, int y, int width, int height, int padding, 
			VisualObject parent, String standardstring, Logical_objects object){
		this(x, y ,width, height, padding, padding, padding, padding, parent, standardstring, object);
	}
	
	public TextBox(int x, int y, int padding, VisualObject parent, String standardstring, Logical_objects object){
		this(x,y, Constants.CLASS_WIDTH,Constants.STANDARD_TEXT_HEIGHT+padding+padding,
				Constants.STANDARD_PADDING,parent, standardstring, object);
	}
	
	public TextBox(int x, int y, VisualObject parent, String standardstring, Logical_objects object){
		this(x,y, Constants.CLASS_WIDTH,
				Constants.STANDARD_TEXT_HEIGHT+(2*Constants.STANDARD_PADDING),Constants.STANDARD_PADDING,
				parent, standardstring, object);
	}
	
	@Override
	public void onClick(SingleClick sc){
		if (!this.isSelected() && !this.getText().isSelected())
			this.getContainer().switchSelectedTo(this);
		else if (this.isSelected())
			this.getContainer().switchSelectedTo(this.getText());
	}
	
	@Override
	public void setY(int y){
		super.setY(y);
		if(getText() != null)
			this.getText().setY(y + this.getPaddingTop());
	}
	
	//Getters and setters

	private int getPaddingTop() {
		return paddingTop;
	}


	private void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}
	
	private int paddingTop;


	@SuppressWarnings("unused")
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


	@SuppressWarnings("unused")
	private int getPaddingRight() {
		return paddingRight;
	}


	private void setPaddingRight(int paddingRight) {
		this.paddingRight = paddingRight;
	}
	
	private int paddingRight;

	public TextHandler getText() {
		return text;
	}

	private void setText(TextHandler text) {
		this.text = text;
	}

	private TextHandler text;
}
