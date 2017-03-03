package visualobjects.visualclass;

import java.awt.Graphics;

import mouse.MouseClickSort;
import visualobjects.Container;
import visualobjects.Text;
import visualobjects.TextBox;
import visualobjects.VisualObject;

public class Association extends VisualObject {
	private TextBox text;
	private VisualClass p1;
	private VisualClass p2;

	public Association(VisualClass parent1, VisualClass parent2) {
		super(0, 0, 0, 0, parent1);
		this.p1 = parent1;
		this.p2 = parent2;
		this.p1.addAssociation(this);
		this.p2.addAssociation(this);
		
		int centerX = p1.getX() + (p2.getX() - p1.getX())/2;
		int centerY = p1.getY() + (p2.getY() - p1.getY())/2;
		
		this.text = new TextBox(centerX ,centerY, this);
		this.addChild(this.text);
		
	}
	
	@Override
	public void show(Graphics g){
		super.show(g);
		g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
	}
	
	public TextBox getText(){
		return this.text;
	}
	
	@Override 
	public boolean isIn(int x, int y){
		return this.text.isIn(x, y);
	}
	
	@Override
	public void delete(){
		this.p1.removeAssociation(this);
		this.p2.removeAssociation(this);
	}

	public void deleteFromOther(VisualClass t){
		if (t.equals(this.p1)){
			this.p2.removeAssociation(this);
		}else if (t.equals(this.p2)){
			this.p1.removeAssociation(this);
		}
	}
	
	@Override
	protected void afterDeleteChild(VisualObject v){
		if (v.equals(this.getText()))
			this.delete();
	}


}
