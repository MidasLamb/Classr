package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import main.Constants;
import mouse.MouseClickSort;
import objects.Attribute;
import objects.Methode;
import objects.RealClass;
import visualobjects.Text;
import visualobjects.TextBox;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {
	private final RealClass realClass;
	
	private TextBox name;
	private AssociationHandle associationHandle;
	private Collection<Association> associations;

	private Collection<TextBox> attributes;
	private Collection<TextBox> methods;

	public VisualClass(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.realClass = new RealClass();
		
		this.setAttributes(new ArrayList<TextBox>());
		this.setMethods(new ArrayList<TextBox>());
		this.setName(new TextBox(this.getX(), this.getY(), 5, parent));
		this.addChild(this.getName());
		
		this.updateDimensions();
		this.setAssociations(new ArrayList<Association>());
		this.setAssociationHandle(new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight()/2, this));
		this.addChild(this.getAssociationHandle());
		
		
	}

	public VisualClass(int x, int y, VisualObject parent){
		this(x,y, 100,200, parent);
	}
	
	
	
	public void show(Graphics g){
		super.show(g);
		this.updateDimensions();
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		int y = this.getY();
		
		
		y += this.getName().getHeight();
		
		for (TextBox t : this.getAttributes()){
			y += t.getHeight();
		}
		
		g.fillRect(this.getX(), y, this.getWidth(),  Constants.CLASS_WHITE_SPACE);
		y +=  Constants.CLASS_WHITE_SPACE;

		
		for (TextBox t: this.getMethods()){
			y += t.getHeight();
		}
		g.fillRect(this.getX(), y, this.getWidth(),  Constants.CLASS_WHITE_SPACE);
	}
	
	
	public VisualObject select(int x, int y, MouseClickSort mc){
		VisualObject vo = super.select(x, y, mc);
		if (mc.equals(MouseClickSort.CLICK)){
			if (this.getAssociationHandle().isIn(x, y)){
				return this.getAssociationHandle();
			}
			if (vo.equals(this.getName())){
				if (this.isSelected())
					return this.getName().getText();
				return this;
			}
			if (vo instanceof TextBox){
				vo.select(x, y, mc);
			}
			return vo;
		} else if (mc.equals(MouseClickSort.DOUBLE_CLICK)){
			if (vo == this){
				if (this.isInEmptyAttribute(x, y)){
					TextBox t = this.createAttribute();
					return t.getText();
				}
				if (this.isInEmptyMethod(x, y)){
					TextBox t = this.createMethod();
					return t.getText();
				}
					
			} else {
				return vo;
			}
		}
		
		return null;
	}
	
	public void updateDimensions(){
		int y = this.getY();
		
		y += this.getName().getHeight();
		
		for (TextBox t : this.getAttributes()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		//TODO magic number
		
		for (TextBox t: this.getMethods()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		
		this.setHeight(y - this.getY());
	}

	private Collection<TextBox> getAttributes() {
		return attributes;
	}

	private void setAttributes(Collection<TextBox> attributes) {
		this.attributes = attributes;
	}

	private Collection<TextBox> getMethods() {
		return methods;
	}

	private void setMethods(Collection<TextBox> methods) {
		this.methods = methods;
	}

	public RealClass getRealClass() {
		return realClass;
	}
	
	private void addAttribute(TextBox a){
		this.getAttributes().add(a);
		this.addChild(a);
	}
	
	private void addMethod(TextBox m){
		this.getMethods().add(m);
		this.addChild(m);
	}
	
	public TextBox createAttribute(){
		TextBox t = new TextBox(this.getX(),
				this.getY(), 5, this);
		this.addAttribute(t);
		this.updateDimensions();
		return t;
	}
	
	public TextBox createMethod(){
		TextBox t = new TextBox(this.getX(),
				this.getY(), 5, this);
		this.addMethod(t);
		this.updateDimensions();
		return t;
	}
	
	public TextBox getName() {
		return name;
	}

	public void setName(TextBox name) {
		this.name = name;
	}
	
	private boolean isInEmptyAttribute(int x, int y){
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();
		
		bottom += this.getName().getHeight();
		
		for (TextBox t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		bottom +=  Constants.CLASS_WHITE_SPACE;
		//TODO magic number
		
		return VisualClass.isBetween(left, right, x) 
				&& VisualClass.isBetween(top, bottom, y);
	}
	
	private boolean isInEmptyMethod(int x, int y){
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();
		
		bottom += this.getName().getHeight();
		
		for (TextBox t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		top +=  Constants.CLASS_WHITE_SPACE;
		bottom +=  Constants.CLASS_WHITE_SPACE;
		
		for (TextBox t: this.getMethods()){
			bottom += t.getHeight();
		}
		
		bottom += Constants.CLASS_WHITE_SPACE;
		
		return VisualClass.isBetween(left, right, x) 
				&& VisualClass.isBetween(top, bottom, y);
	}
	
	private AssociationHandle getAssociationHandle() {
		return associationHandle;
	}

	private void setAssociationHandle(AssociationHandle associationHandle) {
		this.associationHandle = associationHandle;
	}
	
	public void addAssociation(Association a){
		this.getAssociations().add(a);
		this.addChild(a);
	}
	
	public void removeAssociation(Association a){
		this.removeChild(a);
		this.getAssociations().remove(a);
	}

	private Collection<Association> getAssociations() {
		return associations;
	}

	private void setAssociations(Collection<Association> associations) {
		this.associations = associations;
	}

	@Override
	public boolean isIn(int x, int y){
		if (this.getAssociationHandle().isIn(x, y)){
			return true;
		}
		for (Association a: this.getAssociations()){
			if (a.isIn(x, y))
				return true;
		}
		return super.isIn(x, y);
	}
	
	@Override
	public void delete(){
		super.delete();
		for (Association a : this.getAssociations()){
			a.deleteFromOther(this);;
		}
	}
	
	@Override
	public void removeChild(VisualObject c){
		if (this.getAttributes().contains(c)){
			this.getAttributes().remove(c);
		}
		if (this.getMethods().contains(c)){
			this.getMethods().remove(c);
		}
		
		
		super.removeChild(c);
		
	}

}
