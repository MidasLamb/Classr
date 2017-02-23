package visualobjects.visualclass;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import visualobjects.Text;
import visualobjects.VisualObject;

public class Class extends VisualObject {
	private Text name;
	private ClassBody attributes;
	private ClassBody methods;
	
	public Class(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	public Class(int x, int y) {
		super(x, y, 100, 0);
		// this.addChild(new AssociationHandle(x, y+20));
		this.name = new Text(this.getX()+10, this.getY()+10, this.getWidth()-20, 20);
		this.attributes =new ClassBody(this.getX(), this.getY()+20, this.getWidth(), 40, this);
		this.methods = new ClassBody(this.getX(), this.getY()+60, this.getWidth(), 40, this);
		this.addChild(this.name);
		this.addChild(this.attributes);
		this.addChild(this.methods);
		this.updateHeight();
		
	}
	
	public Text getText(){
		return this.name;
	}


	@Override
	public void show(Graphics g) {
		super.show(g);
		g.setColor(Color.red);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(Color.black);
	}
	
	
	/**
	 * Updates the height according to the height of the vertical children.
	 */
	public void updateHeight(){
		int h = 0;
		h += this.name.getHeight();
		h += this.attributes.getHeight();
		h += this.methods.getHeight();
		this.setHeight(h);
		
		this.attributes.setY(this.getY() + this.name.getHeight());
		this.methods.setY(this.getY() + this.name.getHeight() + this.attributes.getHeight());
	}


}
