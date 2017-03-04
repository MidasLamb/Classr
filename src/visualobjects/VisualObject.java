package visualobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import mouse.clicks.DoubleClick;
import mouse.clicks.Drag;
import mouse.clicks.MouseClick;
import mouse.clicks.SingleClick;

public abstract class VisualObject{
	private int x;
	private int y;
	private int width;
	private int height;
	private VisualObject parent;
	
	private boolean isSelected;
	
	private Collection<VisualObject> children;
	private Collection<VisualObject> removeQueue;
	
	public VisualObject(int x,int y,int width, int height, VisualObject parent) {
		children = new ArrayList<VisualObject>();
		removeQueue = new ArrayList<VisualObject>();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = parent;
	}
	
	public void edit(){
		
	}
	
	public void show(Graphics g) {
		if (this.isSelected())
			g.setColor(Color.red);
		for(VisualObject v: this.getChildren()){
			v.show(g);
		}
		g.setColor(Color.black);
	}

	
	public void delete() {
		this.children = new ArrayList<VisualObject>();
		this.parent.removeChild(this);
	}
	
	public VisualObject select(int x, int y) {
		for (VisualObject v: this.getChildren()){
			if (v.isIn(x, y)){
				return v.select(x, y);
			}
		}
		return this;
	}
	
	public void onClick(SingleClick mc){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(mc.getX(), mc.getY())){
				v.onClick(mc);
				return; //Return is required because association text would get two clicks when one is pressed, because it get's it from both parents
			}
		}
	}
	
	public void onDoubleClick(DoubleClick dc){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(dc.getX(), dc.getY())){
				v.onDoubleClick(dc);
			}
		}
	}
	
	public void onDragEnd(Drag d){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(d.getEndX(), d.getEndY())){
				v.onDragEnd(d);
			}
		}
	}
	
	public void onDragStart(Drag d){
		for (VisualObject v: this.getChildren()){
			if (v.isIn(d.getStartX(), d.getStartY())){
				v.onDragStart(d);
			}
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	protected Collection<VisualObject> getChildren(){
		return this.children;
	}

	public boolean isIn(int x, int y){
		return isBetween(this.getX(), this.getX() + this.getWidth(), x) 
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}
	
	/**
	 * Checks if c is between a and b
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static boolean isBetween(int a, int b, int c) {
	    return a <= c && 
	    		b >= c;
	}
	
	protected void addChild(VisualObject c){
		this.children.add(c);
	}
	
	public void removeChild(VisualObject c){
		this.children.remove(c);
		this.afterDeleteChild(c);
	}
	
	private void queueForRemove(VisualObject c){
		this.removeQueue.add(c);
	}
	
	public void setIsSelected(boolean b){
		this.isSelected = b;
	}


	public boolean isSelected() {
		return isSelected;
	}
	
	public void handleKey(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
			this.delete();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public VisualObject getParent(){
		return this.parent;
	}
	
	public Container getContainer(){
		VisualObject v = this;
		while (v != null && !(v instanceof Container))
			v = v.getParent();
		return (Container) v;
	}
	
	protected void afterDeleteChild(VisualObject v){
		
	}
	
	
	
}
