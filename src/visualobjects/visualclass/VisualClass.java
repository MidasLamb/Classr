package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
import objects.Attribute;
import objects.Methode;
import objects.RealClass;
import visualobjects.Association;
import visualobjects.Text;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {
	private final RealClass realClass;
	
	private Text name;
	private AssociationHandle associationHandle;
	private Collection<Association> associations;

	private Collection<Text> attributes;
	private Collection<Text> methods;

	public VisualClass(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.realClass = new RealClass();
		
		this.setAttributes(new ArrayList<Text>());
		this.setMethods(new ArrayList<Text>());
		this.setName(new Text(this.getX(), this.getY(), parent));
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
		
		for (Text t : this.getAttributes()){
			y += t.getHeight();
		}
		
		g.fillRect(this.getX(), y, this.getWidth(), 16);
		y += 16;
		//TODO magic number
		
		for (Text t: this.getMethods()){
			y += t.getHeight();
		}
		g.fillRect(this.getX(), y, this.getWidth(), 16);
		y += 16;
	}
	
	
	public VisualObject select(int x, int y, MouseClick mc){
		VisualObject vo = super.select(x, y, mc);
		if (mc.equals(MouseClick.CLICK)){
			if (this.getAssociationHandle().isIn(x, y)){
				return this.getAssociationHandle();
			}
			return vo;
		} else if (mc.equals(MouseClick.DOUBLE_CLICK)){
			if (vo == this){
				if (this.isInEmptyAttribute(x, y)){
					Text t = this.createAttribute();
					return t;
				}
				if (this.isInEmptyMethod(x, y)){
					Text t = this.createMethod();
					return t;
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
		
		for (Text t : this.getAttributes()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y += 16;
		//TODO magic number
		
		for (Text t: this.getMethods()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y += 16;
		
		this.setHeight(y - this.getY());
	}

	private Collection<Text> getAttributes() {
		return attributes;
	}

	private void setAttributes(Collection<Text> attributes) {
		this.attributes = attributes;
	}

	private Collection<Text> getMethods() {
		return methods;
	}

	private void setMethods(Collection<Text> methods) {
		this.methods = methods;
	}

	public RealClass getRealClass() {
		return realClass;
	}
	
	private void addAttribute(Text a){
		this.getAttributes().add(a);
		this.addChild(a);
	}
	
	private void addMethod(Text m){
		this.getMethods().add(m);
		this.addChild(m);
	}
	
	public Text createAttribute(){
		Text t = new Text(this.getX(),
				this.getY(), this);
		this.addAttribute(t);
		this.updateDimensions();
		return t;
	}
	
	public Text createMethod(){
		Text t = new Text(this.getX(),
				this.getY(), this);
		this.addMethod(t);
		this.updateDimensions();
		return t;
	}
	
	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}
	
	private boolean isInEmptyAttribute(int x, int y){
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();
		
		bottom += this.getName().getHeight();
		
		for (Text t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		bottom += 16;
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
		
		for (Text t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		top += 16;
		bottom += 16;
		//TODO magic number
		
		for (Text t: this.getMethods()){
			bottom += t.getHeight();
		}
		
		bottom += 16;
		//TODO magic number;
		
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

}
