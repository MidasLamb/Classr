package visualobjects.visualclass;

import java.awt.Graphics;
import visualobjects.Text;
import visualobjects.VisualObject;

public class Class extends VisualObject {
	private String name;
	
	public Class(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Class(int x, int y) {
		super(x, y, 100, 200);
		// this.addChild(new AssociationHandle(x, y+20));
		this.addChild(new Text(this.getX()+10, this.getY()+10, this.getWidth()-20, 20));
		this.addChild(new ClassBody(this.getX(), this.getY()+20, this.getWidth(), 40));
		this.addChild(new ClassBody(this.getX(), this.getY()+60, this.getWidth(), 40));
	}


	@Override
	public void show(Graphics g) {
		super.show(g);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	


}
