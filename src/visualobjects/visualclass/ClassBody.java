package visualobjects.visualclass;

import visualobjects.VisualObject;

import java.awt.Color;
import java.awt.Graphics;

import mouse.MouseClick;
import visualobjects.Text;

public class ClassBody extends VisualObject {
	private Class parent;
	private ContentPlaceholder cp;

	public ClassBody(int x, int y, int width, int height, Class parent) {
		super(x, y, width, height, parent);
		this.parent = parent;
		this.cp = new ContentPlaceholder(x, y, width, height, this);
		this.addChild(cp);
	}
	
	@Override
	public void show(Graphics g) {
		if (this.isSelected())
			g.setColor(Color.red);
		g.drawLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
		super.show(g);
	}

	public void addContent(Content c){
		this.addChild(c);
		this.cp.setY(cp.getY() + c.getHeight());
		this.updateHeight();
		this.parent.updateHeight();
	}
	
	public void updateHeight(){
		int h = 0;
		for (VisualObject v: this.getChildren())
			h += v.getHeight();
		this.setHeight(h);
		this.parent.updateHeight();
	}
	
	@Override
	public void setY(int y){
		super.setY(y);
		int h = 0;
		for (VisualObject v: this.getChildren()){
			if (!(v instanceof ContentPlaceholder)){
				v.setY(this.getY() + h);
				h += v.getHeight();
			}
		}
		
		this.cp.setY(this.getY() + h);
	}
	
	


}
