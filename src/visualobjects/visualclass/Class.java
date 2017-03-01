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
	private Collection<Association> associations;
	
	public Class(int x, int y, int width, int height, VisualObject p) {
		super(x, y, width, height, p);

	}
	
	public Class(int x, int y, VisualObject p) {
		super(x, y, CLASS_WIDTH, 0, p);
		
		this.associations = new ArrayList<Association>();
		
		setName(new Text(this.getX()+TEXT_MARGIN, this.getY(), this.getWidth()-2*TEXT_MARGIN, TEXT_HEIGHT, this));
		this.setAttributes(new ClassBody(this.getX(), this.getY(), this.getWidth(), CLASS_BODY_INITIAL_HEIGHT, this));
		this.setMethods(new ClassBody(this.getX(), this.getY(), this.getWidth(), CLASS_BODY_INITIAL_HEIGHT, this));
		this.addChild(this.getName());
		this.addChild(this.getAttributes());
		this.addChild(this.getMethods());
		this.updateHeight();
		
		this.setAssHandle(new AssociationHandle(this.getX() - ASSOCIATIONHANDLE_SIZE/2, this.getY() + this.getHeight() / 2, this));
		this.addChild(getAssHandle());
		
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
		h += this.getName().getHeight();
		h += this.getAttributes().getHeight();
		h += this.getMethods().getHeight();
		this.setHeight(h);
		
		this.getAttributes().setY(this.getY() + this.getName().getHeight());
		this.getMethods().setY(this.getY() + this.getName().getHeight() + this.getAttributes().getHeight());
	}
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc){
		if (this.getAssHandle().isIn(x, y)){
			return this.getAssHandle().select(x, y, mc);
		}
		for (Association a: this.associations){
			if (a.isIn(x, y))
				return a.select(x, y, mc);
		}
		
		VisualObject vo =  super.select(x, y, mc);
		
		if (vo != null && (vo.equals(this.getName()) || vo.equals(this))){
			if (this.isSelected()){
				return this.getName();
			} else {
				return this;
			}
		}
		return vo;
			
	}
	
	@Override
	public boolean isIn(int x, int y){
		if (this.getAssHandle().isIn(x, y)){
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
	
	public Text getName() {
		return name;
	}

	private void setName(Text name) {
		this.name = name;
	}
	
	private Text name;
	
	private ClassBody getAttributes() {
		return attributes;
	}

	private void setAttributes(ClassBody attributes) {
		this.attributes = attributes;
	}
	
	private ClassBody attributes;

	private ClassBody getMethods() {
		return methods;
	}

	private void setMethods(ClassBody methods) {
		this.methods = methods;
	}

	private ClassBody methods;
	
	private AssociationHandle getAssHandle() {
		return assHandle;
	}

	private void setAssHandle(AssociationHandle assHandle) {
		this.assHandle = assHandle;
	}
	
	private AssociationHandle assHandle;


}
