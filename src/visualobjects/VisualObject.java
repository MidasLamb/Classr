package visualobjects;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

public abstract class VisualObject{
	private int x;
	private int y;
	private int width;
	private int height;
	
	private Collection<VisualObject> children;
	
	public VisualObject(int x,int y,int width, int height) {
		children = new ArrayList<VisualObject>();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public abstract void edit();
	
	public void show(Graphics g) {
		for(VisualObject v: this.getChildren()){
			v.show(g);
		}
	}
	public void delete() {
		
	}
	
	public VisualObject select(int x, int y) {
		
		return null;
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
	
	
}
