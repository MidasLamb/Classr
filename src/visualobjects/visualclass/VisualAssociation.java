package visualobjects.visualclass;

import java.awt.Graphics;

import objects.Association;
import visualobjects.PaddingBox;
import visualobjects.VisualObject;

public class VisualAssociation extends VisualObject {

	public VisualAssociation(VisualClass parent1, VisualClass parent2) {
		super(0, 0,0, 0, 0, parent1);
		this.p1 = parent1;
		this.p2 = parent2;
		Association ass = new Association(parent1.getLogicalObject(), parent2.getLogicalObject());
		this.setLogicalObject(ass);
		
		int centerX = getP1().getX() + (getP2().getX() - getP1().getX())/2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY())/2;
		//TODO fix z version.
		this.text = new PaddingBox(centerX ,centerY, 0,  this, ass);
		this.addChild(getText());
		this.getContainer().switchSelectedTo(this.getText().getContent());
	}
	
	
	@Override
	public void draw(Graphics g){
		g.drawLine(getP1().getX(), getP1().getY(), getP2().getX(), getP2().getY());
	}
	
	@Override 
	public boolean isIn(int x, int y){
		return getText().isIn(x, y);
	}
	
	
	@Override
	protected void afterDeleteChild(VisualObject v){
		if (v.equals(this.getText()))
			this.delete();
	}
	
	//Getters and setters
	
	private VisualClass getP1() {
		return p1;
	}
	
	private final VisualClass p1;

	private VisualClass getP2() {
		return p2;
	}
	
	private final VisualClass p2;
	
	public PaddingBox getText(){
		return this.text;
	}
	private final PaddingBox text;


}
