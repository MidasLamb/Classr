package visualobjects.visualclass;

import java.awt.Graphics;

import visualobjects.VisualObject;

public class Class extends VisualObject {
	private String name;
	
	public Class(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void show(Graphics g) {
		super.show(g);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}


}
