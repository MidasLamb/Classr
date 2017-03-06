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
import visualobjects.PaddingBox;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {	

	public VisualClass(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		setLogicalObject(new RealClass(this));
		
		this.setAttributes(new HashSet<>());
		this.setMethods(new HashSet<>());
		this.setName(new PaddingBox(this.getX(), this.getY(), 5, this, "Nieuwe klasse", getLogicalObject()));
		this.addChild(this.getName());
		
		this.updateDimensions();
		this.setAssociations(new ArrayList<VisualAssociation>());
		//TODO Fix z values;
		this.setAssociationHandle(new AssociationHandle(this.getX() - 5, this.getY() + this.getHeight()/2, 0, this));
		this.addChild(this.getAssociationHandle());
	}

	public VisualClass(int x, int y, int z, VisualObject parent){
		this(x,y, z, 100,200, parent);
	}
	
	@Override
	public void draw(Graphics g){
		this.updateDimensions();
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		int y = this.getY();
		
		y += this.getName().getHeight();
		
		for (PaddingBox t : this.getAttributes()){
			y += t.getHeight();
		}
		
		g.fillRect(this.getX(), y, this.getWidth(),  Constants.CLASS_WHITE_SPACE);
		y +=  Constants.CLASS_WHITE_SPACE;

		
		for (PaddingBox t: this.getMethods()){
			y += t.getHeight();
		}
		g.fillRect(this.getX(), y, this.getWidth(),  Constants.CLASS_WHITE_SPACE);
	}
	
	/**
	 * @post calculates the height of this object
	 * 			and sets it
	 */
	private void updateDimensions(){
		int y = this.getY();
		
		y += this.getName().getHeight();
		
		for (PaddingBox t : this.getAttributes()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		
		for (PaddingBox t: this.getMethods()){
			t.setY(y);
			y += t.getHeight();
		}
		
		y +=  Constants.CLASS_WHITE_SPACE;
		
		this.setHeight(y - this.getY());
	}
	
	
	/**
	 * Add a PaddingBox to the attributes
	 * @param 	a
	 * 			PaddingBox to be added to attributes
	 */
	private void addAttribute(PaddingBox a){
		this.getAttributes().add(a);
		this.addChild(a);
	}
	
	
	/**
	 * Add a PaddingBox to the methods
	 * @param 	m
	 * 			PaddingBox to be added to methods
	 */
	private void addMethod(PaddingBox m){
		this.getMethods().add(m);
		this.addChild(m);
	}
	
	/**
	 * Create a new attribute PaddingBox
	 * @return	PaddingBox of the attribute that was created
	 */
	private PaddingBox createAttribute(){
		Attribute attr = getLogicalObject().addAttribute();
		PaddingBox t = new PaddingBox(this.getX(),
				this.getY(), 5, this, attr);
		this.addAttribute(t);
		this.updateDimensions();
		return t;
	}
	
	
	/**
	 * Create a new method PaddingBox
	 * @return	PaddingBox of the method that was created
	 */
	private PaddingBox createMethod(){
		Method method = getLogicalObject().addMethod();
		PaddingBox t = new PaddingBox(this.getX(),
				this.getY(), 5, this, method);
		this.addMethod(t);
		this.updateDimensions();
		return t;
	}
	
	/**
	 * @param 	x
	 * 			Coordinate on the x-axis
	 * @param 	y
	 * 			Coordinate on the y-axis
	 * @return	Returns true if (x,y) is located in the empty attribute, otherwise false
	 */
	private boolean isInEmptyAttribute(int x, int y){
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();
		
		bottom += this.getName().getHeight();
		
		for (PaddingBox t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		bottom +=  Constants.CLASS_WHITE_SPACE;
		
		return VisualClass.isBetween(left, right, x) 
				&& VisualClass.isBetween(top, bottom, y);
	}
	
	
	/**
	 * @param 	x
	 * 			Coordinate on the x-axis
	 * @param 	y
	 * 			Coordinate on the y-axis
	 * @return	Returns true if (x,y) is located in the empty method, otherwise false
	 */
	private boolean isInEmptyMethod(int x, int y){
		int left = this.getX();
		int right = this.getX() + this.getWidth();
		int top = this.getY() + this.getName().getHeight();
		int bottom = this.getY();
		
		
		bottom += this.getName().getHeight();
		
		for (PaddingBox t : this.getAttributes()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		top +=  Constants.CLASS_WHITE_SPACE;
		bottom +=  Constants.CLASS_WHITE_SPACE;
		
		for (PaddingBox t: this.getMethods()){
			top += t.getHeight();
			bottom += t.getHeight();
		}
		
		bottom += Constants.CLASS_WHITE_SPACE;
		
		return isBetween(left, right, x) 
				&& isBetween(top, bottom, y);
	}
	
	/**
	 * Add a VisualAssociation to the list of associations
	 * @param 	a
	 * 			VisualAssociation to be added to the associations
	 */
	void addAssociation(VisualAssociation a){
		this.getAssociations().add(a);
		this.addChild(a);
	}
	
	/**
	 * Remove a VisualAssociation from the list of associations
	 * @param 	a
	 * 			VisualAssociation to be removed from the associations
	 */
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
			this.getLogicalObject().deleteChild(c.getLogicalObject());
		}
		if (this.getMethods().contains(c)){
			this.getMethods().remove(c);
			this.getLogicalObject().deleteChild(c.getLogicalObject());
		}
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
			PaddingBox t = this.createAttribute();
			this.getContainer().switchSelectedTo(t.getContent());
			
		}
		if (this.isInEmptyMethod(dc.getX(), dc.getY())){
			PaddingBox t = this.createMethod();
			this.getContainer().switchSelectedTo(t.getContent());
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
	
	public PaddingBox getName() {
		return name;
	}

	public void setName(PaddingBox name) {
		this.name = name;
	}
	
	private PaddingBox name;
	
	private Collection<PaddingBox> getAttributes() {
		return attributes;
	}

	private void setAttributes(Collection<PaddingBox> attributes) {
		this.attributes = new HashSet<>(attributes);
	}
	
	private HashSet<PaddingBox> attributes;	
	
	private Collection<PaddingBox> getMethods() {
		return methods;
	}

	private void setMethods(Collection<PaddingBox> methods) {
		this.methods = new HashSet<>(methods);
	}
	
	private HashSet<PaddingBox> methods;

}