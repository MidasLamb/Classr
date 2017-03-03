package visualobjects;

import java.awt.Graphics;

import main.Constants;
import mouse.MouseClickSort;
import mouse.clicks.SingleClick;
import visualobjects.visualclass.VisualClass;

public class TextBox extends VisualObject {
	private int paddingLeft;
	private int paddingRight;
	private int paddingTop;
	private int paddingBottom;
	
	private Text text;
	
	
	public TextBox(int x, int y, int width, int height, int paddingLeft, int paddingRight, int paddingTop, int paddingBottom, VisualObject parent){
		super(x, y, width, height, parent);
		this.setPaddingLeft(paddingLeft);
		this.setPaddingRight(paddingRight);
		this.setPaddingTop(paddingTop);
		this.setPaddingBottom(paddingBottom);
		
		this.setText(new Text(this.getX() + this.getPaddingLeft(),
				this.getY() + this.getPaddingTop(), this));
		
		this.addChild(this.getText());
	}
	
	public TextBox(int x, int y, int width, int height, int padding, VisualObject parent){
		this(x, y ,width, height, padding, padding, padding,padding, parent);
	}


	public TextBox(int x, int y, int width, int height, VisualObject parent) {
		this(x,y,width, height, 5, parent);
	}
	
	public TextBox(int x, int y, int padding, VisualObject parent){
		this(x,y, Constants.CLASS_WIDTH,Constants.STANDARD_TEXT_HEIGHT+padding+padding,parent);
	}
	
	public TextBox(int x, int y, VisualObject parent){
		this(x,y, Constants.CLASS_WIDTH,
				Constants.STANDARD_TEXT_HEIGHT+(2*Constants.STANDARD_PADDING),
				parent);
	}
	
	@Override
	public void onClick(SingleClick sc){
		if (!this.isSelected())
			this.getContainer().switchSelectedTo(this);
		else if (this.isSelected())
			this.getContainer().switchSelectedTo(this.getText());
	}


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

	public Text getText() {
		return text;
	}

	private void setText(Text text) {
		this.text = text;
	}
	
	@Override
	public void show(Graphics g){
		super.show(g);
		
	}
	
	@Override
	public void setY(int y){
		super.setY(y);
		this.getText().setY(y + this.getPaddingTop());
	}
}
