package gui;

import java.awt.Graphics;

import inputHandlers.clicks.MouseClick;

public abstract class FormObject {

	private final int x, y, width, height;
	
	public FormObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	abstract void onClick(MouseClick click);
	
	abstract void draw(Graphics g);
	
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
}
