package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import main.Constants;
import mouse.clicks.DoubleClick;
import mouse.clicks.Drag;
import mouse.clicks.SingleClick;
import objects.Attribute;
import objects.Method;
import objects.RealClass;
import visualobjects.TextBox;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {	

	public VisualClass(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		setLogicalObject(new RealClass(this));
		
		this.setAttributes(new HashSet<>());
		this.setMethods(new HashSet<>());
		this.setName(new TextBox(this.getX(), this.getY(), 5, this, "Nieuwe klasse", getLogicalObject()));
		this.addChild(this.getName());
		
		this.updateDimensions();
		this.setAssociations(new ArrayList<VisualAssociation>());
		this.setAssociationHandle(new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight()/2, this));
		this.addChild(this.getAssociationHandle());
	}

	public VisualClass(int x, int y, VisualObject parent){
		this(x,y, 100,200, parent);
	}
	
	@Override
	public void show(Graphics g){
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
		super.show(g);
	}
	
	/**
	 * @post calculates the height of this object
	 * 			and sets it
	 */
	private void updateDimensions(){
		int y = this.getY();
		
		y += this.getName().getHeight();
		
		for (TextBox t : this.getAttributes()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		
		for (TextBox t: this.getMethods()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		
		this.setHeight(y - this.getY());
	}
	
	private void addAttribute(TextBox a){
		this.getAttributes().add(a);
		this.addChild(a);
	}
	
	private void addMethod(TextBox m){
		this.getMethods().add(m);
		this.addChild(m);
	}
	
	private TextBox createAttribute(){
		Attribute attr = getLogicalObject().addAttributes();
		TextBox t = new TextBox(this.getX(),
				this.getY(), 5, this, attr);
		this.addAttribute(t);
		this.updateDimensions();
		return t;
	}
	
	private TextBox createMethod(){
		Method method = getLogicalObject().addMethod();
		TextBox t = new TextBox(this.getX(),
				this.getY(), 5, this, method);
		this.addMethod(t);
		this.updateDimensions();
		return t;
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
			top += t.getHeight();
			bottom += t.getHeight();
		}
		
		bottom += Constants.CLASS_WHITE_SPACE;
		
		return isBetween(left, right, x) 
				&& isBetween(top, bottom, y);
	}
	
	void addAssociation(VisualAssociation a){
		System.out.println("Added association");
		this.getAssociations().add(a);
		this.addChild(a);
	}
	
	void removeAssociation(VisualAssociation a){
		this.removeChild(a);
		this.getAssociations().remove(a);
	}

	@Override
	public boolean isIn(int x, int y){
		if (this.getAssociationHandle().isIn(x, y)){
			return true;
		}
		for (VisualAssociation a: this.getAssociations()){
			if (a.isIn(x, y))
				return true;
		}
		return super.isIn(x, y);
	}
	
	@Override
	public void delete(){
		super.delete();
		for (VisualAssociation a : this.getAssociations()){
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
		this.getLogicalObject().deleteChild(c.getLogicalObject());
		super.removeChild(c);
		
	}
	
	@Override
	public VisualObject select(int x, int y){
		if (this.getAssociationHandle().isIn(x, y)){
			return this.getAssociationHandle();
		}
		return super.select(x, y);
	}
	
	@Override
	public void onClick(SingleClick sc){
		if (this.getAssociationHandle().isIn(sc.getX(), sc.getY()))
			this.getAssociationHandle().onClick(sc);
		else
			super.onClick(sc);
	}
	
	@Override
	public void onDragEnd(Drag d){
		if (this.getAssociationHandle().isIn(d.getEndX(), d.getEndY()))
			this.getAssociationHandle().onDragEnd(d);
		else
			super.onDragEnd(d);
	}
	
	@Override
	public void onDoubleClick(DoubleClick dc){
		if (this.isInEmptyAttribute(dc.getX(), dc.getY())){
			TextBox t = this.createAttribute();
			this.getContainer().switchSelectedTo(t.getText());
			
		}
		if (this.isInEmptyMethod(dc.getX(), dc.getY())){
			TextBox t = this.createMethod();
			this.getContainer().switchSelectedTo(t.getText());
		}
	}
	
	@Override
	public void afterDeleteChild(VisualObject v){
		this.updateDimensions();
		if (v.equals(this.getName()))
			this.delete();
	}
	
	@Override
	public RealClass getLogicalObject() {
		return (RealClass) super.getLogicalObject();
	}

	public void setLogicalObject(RealClass object) {
		super.setLogicalObject(object);
	}
	
	//Getters and setters
	
	private AssociationHandle getAssociationHandle() {
		return associationHandle;
	}

	private void setAssociationHandle(AssociationHandle associationHandle) {
		this.associationHandle = associationHandle;
	}
	
	private AssociationHandle associationHandle;
	
	private Collection<VisualAssociation> getAssociations() {
		return associations;
	}

	private void setAssociations(Collection<VisualAssociation> associations) {
		this.associations = associations;
	}
	
	private Collection<VisualAssociation> associations;
	
	public TextBox getName() {
		return name;
	}

	public void setName(TextBox name) {
		this.name = name;
	}
	
	private TextBox name;
	
	private Collection<TextBox> getAttributes() {
		return attributes;
	}

	private void setAttributes(Collection<TextBox> attributes) {
		this.attributes = new HashSet<>(attributes);
	}
	
	private HashSet<TextBox> attributes;	
	
	private Collection<TextBox> getMethods() {
		return methods;
	}

	private void setMethods(Collection<TextBox> methods) {
		this.methods = new HashSet<>(methods);
	}
	
	private HashSet<TextBox> methods;

}
