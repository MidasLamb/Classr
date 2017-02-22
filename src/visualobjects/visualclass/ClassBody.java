package visualobjects.visualclass;

import visualobjects.VisualObject;
import java.awt.Graphics;
import visualobjects.Text;

public class ClassBody extends VisualObject {
	private Class parent;
	private ContentPlaceholder cp;

	public ClassBody(int x, int y, int width, int height, Class parent) {
		super(x, y, width, height);
		this.parent = parent;
		this.cp = new ContentPlaceholder(x, y, width, height, this);
		this.addChild(cp);
	}
	
	@Override
	public void show(Graphics g) {
		super.show(g);
		g.drawLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
	}

	public void addContent(Content c){
		this.addChild(c);
		this.cp.setY(cp.getY() + c.getHeight());
		this.updateHeight();
		parent.updateHeight();
	}
	
	public void updateHeight(){
		int h = 0;
		for (VisualObject v: this.getChildren())
			h += v.getHeight();
		this.setHeight(h);
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
