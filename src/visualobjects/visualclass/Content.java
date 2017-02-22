package visualobjects.visualclass;

import java.awt.Graphics;

import visualobjects.VisualObject;

public class Content extends VisualObject {

	public Content(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Content(int x, int y) {
		super(x, y, 200, 100);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void show(Graphics g){
		
		if (this.isSelected()){
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

}
