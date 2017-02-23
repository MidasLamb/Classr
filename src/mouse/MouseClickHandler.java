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
			System.out.println("Mouse Pressed");
			this.isDown = true;
			
			if (System.currentTimeMillis() - this.lastClickTime < 100){
				if (Math.abs(e.getX() - this.lastClickX) < 10 &&
						Math.abs(e.getY() - this.lastClickY) < 10){
					//Doubleclick
					//container.select(e.getX(), e.getY(), MouseClick.DOUBLE_CLICK);
				} else {
					//container.select(e.getX(), e.getY(), MouseClick.CLICK);
				}
			} else {
				//container.select(e.getX(), e.getY(), MouseClick.CLICK);
			}
			this.lastClickTime = System.currentTimeMillis();
			
		}
		
		if (e.getID() == MouseEvent.MOUSE_RELEASED){
			System.out.println("Mouse Released");
			this.isDown = false;
			if (this.isBeingDragged){
				//container.select(e.getX(), e.getY(), MouseClick.DRAG);
			}
			this.isBeingDragged = false;
		}
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED){
			System.out.println("Mouse Drag");
			this.isBeingDragged = true;
		}
	}
	
	
}
