package visualobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import mouse.clicks.DoubleClick;
import mouse.clicks.Drag;
import mouse.clicks.SingleClick;
import objects.Logical_objects;

public abstract class VisualObject{

	public VisualObject(int x,int y,int width, int height, VisualObject parent) {
		setChildren(new ArrayList<VisualObject>());
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setParent(parent);
	}
	
	/**
	 * 
	 * @param 	g
	 * 			Graphics g
	 * @post shows this visual object and his children
	 * 			shows this object red if it is selected
	 */
	public void show(Graphics g) {
		if (this.isSelected())
			g.setColor(Color.red);
		this.draw(g);
		for(VisualObject v: this.getChildren()){
			v.show(g);
		}
		g.setColor(Color.black);
	}
	
	protected void draw(Graphics g){
		
	}

	/**
	 * @post Deletes this visual object
	 */
	public void delete() {
		setChildren(new ArrayList<VisualObject>());
		getParent().removeChild(this);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The x-coordinate of the pixel that is selected
	 * @param 	y
	 * 			The y-coordinate of the pixel that is selected
	 * @return	the visual object that is in this region
	 * 				it can be one of the children or this object itself
	 */
	public VisualObject select(int x, int y) {
		for (VisualObject v: this.getChildren()){
			if (v.isIn(x, y)){
				return v.select(x, y);
			}
		}
		return this;
	}
	
	/**
	 * 
	 * @param 	sc
	 * 			The single click object
	 * @post	triggers the onClick function of the child object that is clicked
	 */
	public void onClick(SingleClick sc){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(sc.getX(), sc.getY())){
				v.onClick(sc);
				//Return is required because association text would get two 
				//	clicks when one is pressed, because it get's it from both parents
				return; 
			}
		}
	}
	
	/**
	 * 
	 * @param 	dc
	 * 			The double click object
	 * @post	triggers the onDoubleClick function of the child object that is clicked
	 */
	public void onDoubleClick(DoubleClick dc){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(dc.getX(), dc.getY())){
				v.onDoubleClick(dc);
			}
		}
	}
	
	/**
	 * 
	 * @param 	d
	 * 			The drag object
	 * @post	triggers the onDragEnd function of the child where the dragging starts
	 */
	public void onDragStart(Drag d){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(d.getStartX(), d.getStartY())){
				v.onDragStart(d);
			}
		}
	}
	
	/**
	 * 
	 * @param 	d
	 * 			The drag object
	 * @post	triggers the onDragEnd function of the child where there is dragged too
	 */
	public void onDragEnd(Drag d){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(d.getEndX(), d.getEndY())){
				v.onDragEnd(d);
			}
		}
	}

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the t-coordinate
	 * @return true if this object is at the given coordinates
	 * 			otherwise false
	 */
	public boolean isIn(int x, int y){
		return isBetween(this.getX(), this.getX() + this.getWidth(), x) 
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}
	
	/**
	 * checks if c is between a en b
	 */
	protected static boolean isBetween(int a, int b, int c) {
	    return a <= c && 
	    		b >= c;
	}
	
	/**
	 * 
	 * @param 	e
	 * 			Key event
	 * @post	if delete is pressed this object will delete itself
	 */
	public void handleKey(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
			this.delete();
	}
	
	/**
	 * 
	 * @return the container where this object is in
	 */
	public Container getContainer(){
		VisualObject v = this;
		while (v != null && !(v instanceof Container))
			v = v.getParent();
		return (Container) v;
	}
	
	/**
	 * 
	 * @param 	child
	 * 			The child that is deleted
	 * @post	does nothing
	 */
	protected void afterDeleteChild(VisualObject child){
		
	}
	
	//Getters and setters
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	private int x;
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	private int y;
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	private int width;

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	private int height;
	
	private void setParent(VisualObject parent) {
		this.parent = parent;
	}
	
	public VisualObject getParent(){
		return this.parent;
	}

	private VisualObject parent;

	
	private void setChildren(Collection<VisualObject> children){
		this.children = children;
	}
	
	protected Collection<VisualObject> getChildren(){
		return new ArrayList<VisualObject>(this.children);
	}
	
	protected void addChild(VisualObject c){
		this.children.add(c);
	}
	
	public void removeChild(VisualObject c){
		this.children.remove(c);
		this.afterDeleteChild(c);
	}
	
	private Collection<VisualObject> children;

	public void setSelected(boolean b){
		this.isSelected = b;
	}

	public boolean isSelected() {
		return isSelected;
	}
	
	private boolean isSelected;
	
	public Logical_objects getLogicalObject() {
		return logicalObject;
	}

	public void setLogicalObject(Logical_objects object) {
		this.logicalObject = object;
	}	
	
	private Logical_objects logicalObject;
	
}
