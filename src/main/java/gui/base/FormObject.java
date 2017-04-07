package gui.base;

import static gui.base.Constants.STANDARD_LABEL_PADDING;
import static gui.base.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Graphics;

import inputHandlers.clicks.MouseClick;

public abstract class FormObject implements Comparable<FormObject>{

	private final int x, y, width, height;
	
	public FormObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int compareTo(FormObject formObj) {
		int diffY = this.getY() - formObj.getY();
		if (diffY == 0) {
			// sort by x
			return this.getX() - formObj.getX();
		} else {
			return diffY;
		}
	}
	
	abstract void onClick(MouseClick click);
	
	abstract void draw(Graphics g);
	
	protected abstract void onAction();
	
	
	/**
	 * Checks if the received click is for this object and handles it if it is for this object
	 * @param 	click
	 * 			the click
	 */
	void handleClick(MouseClick click){
		if(click.getX() >= getX() && click.getY() >= getY() 
				&& click.getX() <= getX()+getWidth() && click.getY() <= getY()+getHeight())
			onClick(click);
	}
	
	public Label createLabel(LabelPosition position, String text) {
		int x = 0, y = 0;
		switch (position) {
			case RIGHT:
				x = this.getX() + this.getWidth() + STANDARD_LABEL_PADDING;
				int midY = (this.getY() + this.getY() + this.getHeight()) / 2;
				y = midY - STANDARD_TEXT_HEIGHT / 2;
				break;
			case TOP:
				x = this.getX();
				y = this.getY() - STANDARD_TEXT_HEIGHT;
				break;
		}
		FormObject thisFormObject = this;
		return new Label(text, x, y) {
			@Override
			protected void onAction() {
				thisFormObject.onClick(new MouseClick(0, 0));
			}
		};
	}
	
	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	int getWidth() {
		return width;
	}

	int getHeight() {
		return height;
	}
	
	static public enum LabelPosition {
		RIGHT, TOP;
	}
}
