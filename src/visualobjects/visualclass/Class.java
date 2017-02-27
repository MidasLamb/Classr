package visualobjects.visualclass;

import static main.Constants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
import visualobjects.Association;
import visualobjects.Text;
import visualobjects.VisualObject;

public class Class extends VisualObject {
	private Text name;
	private ClassBody attributes;
	private ClassBody methods;
	private AssociationHandle ah;
	private Collection<Association> associations;
	
	public Class(int x, int y, int width, int height, VisualObject p) {
		super(x, y, width, height, p);

	}
	
	public Class(int x, int y, VisualObject p) {
		super(x, y, CLASS_WIDTH, 0, p);
		
		this.associations = new ArrayList<Association>();
		
		this.name = new Text(this.getX()+TEXT_MARGIN, this.getY(), this.getWidth()-2*TEXT_MARGIN, TEXT_HEIGHT, this);
		this.attributes =new ClassBody(this.getX(), this.getY(), this.getWidth(), 40, this);
		this.methods = new ClassBody(this.getX(), this.getY(), this.getWidth(), 40, this);
		this.addChild(this.name);
		this.addChild(this.attributes);
		this.addChild(this.methods);
		this.updateHeight();
		
		this.ah = new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight() / 2, this);
		this.addChild(ah);
		
	}
	
	public Text getText(){
		return this.name;
	}


	@Override
	public void show(Graphics g) {
		if (this.isSelected())
			g.setColor(Color.red);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.show(g);
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
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc){
		if (this.ah.isIn(x, y)){
			return this.ah.select(x, y, mc);
		}
		for (Association a: this.associations){
			if (a.isIn(x, y))
				return a.select(x, y, mc);
		}
		
		VisualObject vo =  super.select(x, y, mc);
		
		if (vo != null && (vo.equals(this.name) || vo.equals(this))){
			if (this.isSelected()){
				return this.name;
			} else {
				return this;
			}
		}
		return vo;
			
	}
	
	@Override
	public boolean isIn(int x, int y){
		if (this.ah.isIn(x, y)){
			return true;
		}
		for (Association a: this.associations){
			if (a.isIn(x, y))
				return true;
		}
		return super.isIn(x, y);
	}
	
	public void addAssociation(Association a){
		this.associations.add(a);
		this.addChild(a);
	}
	
	public void removeAssociation(Association a){
		this.removeChild(a);
		this.associations.remove(a);
	}
	


}
