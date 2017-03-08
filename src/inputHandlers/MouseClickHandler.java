package inputHandlers;

import static main.Constants.*;
import java.awt.event.MouseEvent;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;
import visualobjects.Container;

public class MouseClickHandler {
	
	public MouseClickHandler(Container container){
		setContainer(container);
	}
	
	public void handleInput(MouseEvent e){
		if (e.getID() == MouseEvent.MOUSE_PRESSED){
			if (isDoubleClick(e)){
				//Let the container know that there was a double click					
				getContainer().onDoubleClick(new DoubleClick(e.getX(), e.getY()));
			} else {				
				//Let the container know that there was a single click	
				getContainer().onClick(new SingleClick(e.getX(), e.getY()));
			}
			//Save info about this click
			setLastClickX(e.getX());
			setLastClickY(e.getY());
			setLastClickTime(System.currentTimeMillis());
		}
		
		if (e.getID() == MouseEvent.MOUSE_RELEASED){
			if (isBeingDragged()){
				getContainer().onDragEnd(new Drag(getLastClickX(), getLastClickY(), e.getX(), e.getY()));
			}
			setBeingDragged(false);
		}
		
		if (e.getID() == MouseEvent.MOUSE_DRAGGED){
			setBeingDragged(true);
		}
	}

	/**
	 * 
	 * @param 	e
	 * 			The mouse event
	 * 			
	 * @return True if the click is a double click otherwise false
	 */
	private boolean isDoubleClick(MouseEvent e) {
		return System.currentTimeMillis() - getLastClickTime() < DOUBLECLICK_TRESHOLD 
				&& Math.abs(e.getX() - getLastClickX()) < DOUBLECLICK_RANGE &&
					Math.abs(e.getY() - getLastClickY()) < DOUBLECLICK_RANGE;
	}
	
	//Getters and setters
	
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

	private boolean isBeingDragged() {
		return isBeingDragged;
	}

	private void setBeingDragged(boolean isBeingDragged) {
		this.isBeingDragged = isBeingDragged;
	}
	
	private boolean isBeingDragged;
	
	
}
