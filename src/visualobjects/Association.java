package visualobjects;

import java.awt.Color;
import java.awt.Graphics;

import mouse.MouseClick;
import visualobjects.visualclass.Class;

public class Association extends VisualObject {
	private Text text;
	private Class p1;
	private Class p2;

	public Association(Class parent1, Class parent2) {
		super(0, 0, 0, 0, null);
		this.p1 = parent1;
		this.p2 = parent2;
		this.p1.addAssociation(this);
		this.p2.addAssociation(this);
		
		int centerX = p1.getX() + (p2.getX() - p1.getX())/2;
		int centerY = p1.getY() + (p2.getY() - p1.getY())/2;
		
		this.text = new Text(centerX ,centerY, this);
		this.addChild(this.text);
		
	}
	
	@Override
	public void show(Graphics g){
		if (this.isSelected()){
			g.setColor(Color.red);
		}
		super.show(g);
		g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
		g.setColor(Color.black);
	}
	
	public Text getText(){
		return this.text;
	}
	
	@Override 
	public boolean isIn(int x, int y){
		return this.text.isIn(x, y);
	}
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc){
		if (mc.equals(MouseClick.DOUBLE_CLICK) || this.isSelected()){
			return this.text.select(x, y, mc);
		}
		if (mc.equals(MouseClick.CLICK)){
			return this;
		}
		return null;
	}


}
