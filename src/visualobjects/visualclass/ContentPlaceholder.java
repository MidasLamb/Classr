package visualobjects.visualclass;

import java.awt.Graphics;

import visualobjects.VisualObject;

public class ContentPlaceholder extends VisualObject {
	private ClassBody parent;

	public ContentPlaceholder(int x, int y, int width, int height, ClassBody parent) {
		super(x, y, width, height);
		this.parent = parent;
		// TODO Auto-generated constructor stub
	}
	
	public ContentPlaceholder(int x, int y, ClassBody parent) {
		super(x,y,parent.getWidth(), 40);
		this.parent = parent;
	}
	
	@Override
	public void show(Graphics g){
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		
	}
	
	@Override
	public VisualObject select(int x, int y) {
		Content c = new Content(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		parent.addContent(c);
		return c;
	}


}
