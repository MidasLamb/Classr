package visualobjects.visualclass;

import java.awt.Color;
import java.awt.Graphics;

import mouse.MouseClick;
import visualobjects.VisualObject;

public class ContentPlaceholder extends VisualObject {
	private ClassBody parent;

	public ContentPlaceholder(int x, int y, int width, int height, ClassBody parent) {
		super(x, y, width, height, parent);
		this.parent = parent;
		// TODO Auto-generated constructor stub
	}
	
	public ContentPlaceholder(int x, int y, ClassBody parent) {
		super(x,y,parent.getWidth(), 40, parent);
		this.parent = parent;
	}
	
	@Override
	public void show(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		g.drawString("Click to add", this.getX(), this.getY() + this.getHeight()/2);
		
	}
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc) {
		if (mc.equals(MouseClick.DOUBLE_CLICK)){
			Content c = new Content(this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.parent);
			parent.addContent(c);
			return c.getText();
		}
		if (mc.equals(MouseClick.CLICK)){
			return parent;
		}
		return null;
	}


}
