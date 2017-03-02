package mouse;

import static main.Constants.*;
import java.awt.event.MouseEvent;
import visualobjects.Container;

public class MouseClickHandler {
	
	public MouseClickHandler(Container container){
		setLastClickTime(0);
		setContainer(container);
		setDown(false);
		setBeingDragged(false);
		setLastClickX(-1);
		setLastClickY(-1);
	}
	
	public void handleKey(MouseEvent e){
		if (e.getID() == MouseEvent.MOUSE_PRESSED){
			this.isDown = true;
			//Check if time until last click is lower than threshold
			if (System.currentTimeMillis() - getLastClickTime() < DOUBLECLICK_TRESHOLD){
				//Check if click is in range of last click
				if (Math.abs(e.getX() - getLastClickX()) < DOUBLECLICK_RANGE &&
						Math.abs(e.getY() - getLastClickY()) < DOUBLECLICK_RANGE){
					//Doubleclick
					getContainer().select(e.getX(), e.getY(), MouseClickSort.DOUBLE_CLICK);
				} else {
					getContainer().select(e.getX(), e.getY(), MouseClickSort.CLICK);
				}
			} else {
				getContainer().select(e.getX(), e.getY(), MouseClickSort.CLICK);
			}
			//save the data
			setLastClickX(e.getX());
			setLastClickY(e.getY());
			setLastClickTime(System.currentTimeMillis());
		}
		
		if (e.getID() == MouseEvent.MOUSE_RELEASED){
			setDown(false);
			if (isBeingDragged()){
				getContainer().select(e.getX(), e.getY(), MouseClickSort.DRAG);
			}
			setBeingDragged(false);
		}
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED){
			setBeingDragged(true);
		}
	}
	
	private long getLastClickTime() {
		return lastClickTime;
	}

	private void setLastClickTime(long lastClickTime) {
		this.lastClickTime = lastClickTime;
	}
	
	private long lastClickTime;

	private int getLastClickX() {
		return lastClickX;
	}

	private void setLastClickX(int lastClickX) {
		this.lastClickX = lastClickX;
	}
	
	private int lastClickX;

	private int getLastClickY() {
		return lastClickY;
	}

	private void setLastClickY(int lastClickY) {
		this.lastClickY = lastClickY;
	}
	
	private int lastClickY;

	private Container getContainer() {
		return container;
	}

	private void setContainer(Container container) {
		this.container = container;
	}
	
	private Container container;

	private boolean isDown() {
		return isDown;
	}

	private void setDown(boolean isDown) {
		this.isDown = isDown;
	}
	
	private boolean isDown;

	private boolean isBeingDragged() {
		return isBeingDragged;
	}

	private void setBeingDragged(boolean isBeingDragged) {
		this.isBeingDragged = isBeingDragged;
	}
	
	private boolean isBeingDragged;
	
	
}
