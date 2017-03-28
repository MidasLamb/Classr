package gui;

import java.awt.Graphics;

public abstract class FormObject {

	private final int x, y, width, height;
	
	public FormObject(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	abstract void onClick();
	
	abstract void draw(Graphics g);
	
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
