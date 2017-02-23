package mouse;

import java.awt.event.MouseEvent;

import visualobjects.Container;

public class MouseClickHandler {
	
	private long lastClickTime;
	private int lastClickX;
	private int lastClickY;
	private Container container;
	private boolean isDown;
	private boolean isBeingDragged;
	
	public MouseClickHandler(Container container){
		this.lastClickTime = 0;
		this.container = container;
		this.isDown = false;
		this.isBeingDragged = false;
		this.lastClickX = -1;
		this.lastClickY = -1;
	}
	
	public void handleKey(MouseEvent e){
		if (e.getID() == MouseEvent.MOUSE_PRESSED){
			this.isDown = true;
			
			if (System.currentTimeMillis() - this.lastClickTime < 200){
				if (Math.abs(e.getX() - this.lastClickX) < 20 &&
						Math.abs(e.getY() - this.lastClickY) < 20){
					//Doubleclick
					container.select(e.getX(), e.getY(), MouseClick.DOUBLE_CLICK);
				} else {
					container.select(e.getX(), e.getY(), MouseClick.CLICK);
				}
			} else {
				container.select(e.getX(), e.getY(), MouseClick.CLICK);
			}
			this.lastClickX = e.getX();
			this.lastClickY = e.getY();
			this.lastClickTime = System.currentTimeMillis();
			
		}
		
		if (e.getID() == MouseEvent.MOUSE_RELEASED){
			this.isDown = false;
			if (this.isBeingDragged){
				container.select(e.getX(), e.getY(), MouseClick.DRAG);
			}
			this.isBeingDragged = false;
		}
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED){
			this.isBeingDragged = true;
		}
	}
	
	
}
