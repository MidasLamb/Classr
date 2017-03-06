package visualobjects.visualclass;

import java.awt.Graphics;

import objects.Association;
import visualobjects.TextBox;
import visualobjects.VisualObject;

public class VisualAssociation extends VisualObject {

	public VisualAssociation(VisualClass parent1, VisualClass parent2) {
		super(0, 0, 0, 0, parent1);
		this.p1 = parent1;
		this.p2 = parent2;
		Association ass = new Association(parent1.getLogicalObject(), parent2.getLogicalObject());
		this.p1.addAssociation(this);
		getP2().addAssociation(this);
		
		int centerX = getP1().getX() + (getP2().getX() - getP1().getX())/2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY())/2;
		
		this.text = new TextBox(centerX ,centerY, this, ass);
		this.addChild(getText());
		this.getContainer().switchSelectedTo(this.getText().getText());
	}
	
	public void deleteFromOther(VisualClass t){
		if (t.equals(this.p1)){
			getP2().removeAssociation(this);
		}else if (t.equals(getP2())){
			getP1().removeAssociation(this);
		}
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
	public void delete(){
		getP1().removeAssociation(this);
		getP2().removeAssociation(this);
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
	
	public TextBox getText(){
		return this.text;
	}
	private final TextBox text;


}
