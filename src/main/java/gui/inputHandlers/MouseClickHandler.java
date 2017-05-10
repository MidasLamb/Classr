package gui.inputHandlers;

import static main.Constants.DOUBLECLICK_RANGE;
import static main.Constants.DOUBLECLICK_TRESHOLD;

import java.awt.event.MouseEvent;

import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;

public class MouseClickHandler {
	private long lastClickTime;
	private int lastClickXForDrag;
	private int lastClickYForDrag;
	
	private int lastClickXForDoubleClick;
	private int lastClickYForDoubleClick;
	
	private Clickable clickable;
	private boolean isBeingDragged;

	public MouseClickHandler(Clickable clickable) {
		setClickable(clickable);
	}

	public void handleInput(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_PRESSED) {
			
			// Save info about this click to know where a drag started.
			setLastClickXForDrag(e.getX());
			setLastClickYForDrag(e.getY());

		}

		if (e.getID() == MouseEvent.MOUSE_RELEASED) {
			

			if (isBeingDragged()) {
				getClickable().onDragEnd(new Drag(getLastClickXForDrag(), getLastClickYForDrag(), e.getX(), e.getY()));
			} else {
				if (isDoubleClick(e)) {
					// Let the container know that there was a double click
					getClickable().onDoubleClick(new DoubleClick(e.getX(), e.getY()));
				} else {
					// Let the container know that there was a single click
					getClickable().onClick(new SingleClick(e.getX(), e.getY()));
				}
			}
			setLastClickTime(System.currentTimeMillis());

			setLastClickXForDoubleClick(e.getX());
			setLastClickYForDoubleClick(e.getY());
			setBeingDragged(false);
		}

		if (e.getID() == MouseEvent.MOUSE_DRAGGED) {
			getClickable().onDragUpdate(new Drag(getLastClickXForDrag(), getLastClickYForDrag(), e.getX(), e.getY()));
			setBeingDragged(true);
		}
	}

	/**
	 * 
	 * @param e
	 *            The mouse event
	 * 
	 * @return True if the click is a double click otherwise false
	 */
	private boolean isDoubleClick(MouseEvent e) {
		return System.currentTimeMillis() - getLastClickTime() < DOUBLECLICK_TRESHOLD
				&& Math.abs(e.getX() - getLastClickXForDoubleClick()) < DOUBLECLICK_RANGE
				&& Math.abs(e.getY() - getLastClickYForDoubleClick()) < DOUBLECLICK_RANGE;
	}

	// Getters and setters

	private long getLastClickTime() {
		return lastClickTime;
	}

	private void setLastClickTime(long lastClickTime) {
		this.lastClickTime = lastClickTime;
	}

	private int getLastClickXForDrag() {
		return lastClickXForDrag;
	}

	private void setLastClickXForDrag(int lastClickX) {
		this.lastClickXForDrag = lastClickX;
	}

	private int getLastClickYForDrag() {
		return lastClickYForDrag;
	}

	private void setLastClickYForDrag(int lastClickY) {
		this.lastClickYForDrag = lastClickY;
	}

	private boolean isBeingDragged() {
		return isBeingDragged;
	}

	private void setBeingDragged(boolean isBeingDragged) {
		this.isBeingDragged = isBeingDragged;
	}

	private Clickable getClickable() {
		return clickable;
	}

	private void setClickable(Clickable clickable) {
		this.clickable = clickable;
	}

	private final int getLastClickXForDoubleClick() {
		return lastClickXForDoubleClick;
	}

	private final void setLastClickXForDoubleClick(int lastClickXForDoubleClick) {
		this.lastClickXForDoubleClick = lastClickXForDoubleClick;
	}

	private final int getLastClickYForDoubleClick() {
		return lastClickYForDoubleClick;
	}

	private final void setLastClickYForDoubleClick(int lastClickYForDoubleClick) {
		this.lastClickYForDoubleClick = lastClickYForDoubleClick;
	}

}
