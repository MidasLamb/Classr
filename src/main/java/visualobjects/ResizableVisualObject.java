package visualobjects;

import gui.inputHandlers.clicks.Drag;

public abstract class ResizableVisualObject extends VisualObject {
	private boolean beingDraggedFromLeft;
	private boolean beingDraggedFromRight;
	private boolean beingDraggedFromTop;
	private boolean beingDraggedFromBottom;

	private boolean isBeingDragged;

	private int deadzone = 3;

	private int lastX;
	private int lastY;

	public ResizableVisualObject(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		this.setBeingDraggedFromBottom(false);
		this.setBeingDraggedFromLeft(false);
		this.setBeingDraggedFromRight(false);
		this.setBeingDraggedFromTop(false);
		this.setBeingDragged(false);
	}

	public boolean isOnLeftSide(int x, int y) {
		return (x >= this.getX() - deadzone && x <= this.getX() + deadzone);
	}

	public boolean isOnTopSide(int x, int y) {
		return (y >= this.getY() - deadzone && y <= this.getY() + deadzone);
	}

	public boolean isOnRightSide(int x, int y) {
		return (x >= this.getX() + this.getWidth() - deadzone && x <= this.getX() + this.getWidth() + deadzone);
	}

	public boolean isOnBottomSide(int x, int y) {
		return (y >= this.getY() + this.getHeight() - deadzone && y <= this.getY() + this.getHeight() + deadzone);
	}

	@Override
	public void onDragEnd(Drag drag) {
		super.onDragEnd(drag);
		this.setBeingDraggedFromBottom(false);
		this.setBeingDraggedFromLeft(false);
		this.setBeingDraggedFromRight(false);
		this.setBeingDraggedFromTop(false);
		this.setBeingDragged(false);
	}

	@Override
	public void onDragUpdate(Drag drag) {

		int x = drag.getStartX();
		int y = drag.getStartY();

		if (isBeingDraggedFromLeft() || isBeingDraggedFromBottom() || isBeingDraggedFromRight()
				|| isBeingDraggedFromTop()) {
			x = this.getLastX();
			y = this.getLastY();
		}

		this.setLastX(drag.getEndX());
		this.setLastY(drag.getEndY());

		int xDiff = drag.getEndX() - x;
		int yDiff = drag.getEndY() - y;

		int xChange = 0;
		int yChange = 0;
		int widthChange = 0;
		int heightChange = 0;

		if ((isOnLeftSide(x, y) && !this.isBeingDragged()) || isBeingDraggedFromLeft()) {
			xChange = xDiff;
			widthChange = -xDiff;
			if (!this.isBeingDragged())
				this.setBeingDraggedFromLeft(true);
		}

		if ((isOnTopSide(x, y)&& !this.isBeingDragged()) || isBeingDraggedFromTop()) {
			yChange = yDiff;
			heightChange = -yDiff;
			if (!this.isBeingDragged())
				this.setBeingDraggedFromTop(true);
		}

		if ((isOnRightSide(x, y) && !this.isBeingDragged()) || isBeingDraggedFromRight()) {
			widthChange = xDiff;
			if (!this.isBeingDragged())
				this.setBeingDraggedFromRight(true);
		}

		if ((isOnBottomSide(x, y) && !this.isBeingDragged()) || isBeingDraggedFromBottom()) {
			heightChange = yDiff;
			if (!this.isBeingDragged())
				this.setBeingDraggedFromBottom(true);
		}
		
		if (isBeingDraggedFromLeft() || isBeingDraggedFromBottom() || isBeingDraggedFromRight()
				|| isBeingDraggedFromTop()) {
			this.setBeingDragged(true);
		}

		this.changeX(xChange);
		this.changeY(yChange);
		this.changeChildrenX(xChange);
		this.changeChildrenY(yChange);

		this.changeWidth(widthChange);
		this.changeHeight(heightChange);
	}

	private void changeX(int x) {
		this.setX(this.getX() + x);
	}

	private void changeY(int y) {
		this.setY(this.getY() + y);
	}

	private void changeWidth(int width) {
		this.setWidth(this.getWidth() + width);
	}

	private void changeHeight(int height) {
		this.setHeight(this.getHeight() + height);
	}

	private void changeChildrenX(int x) {
		for (VisualObject v : this.getChildren()) {
			v.setX(v.getX() + x);
		}
	}

	private void changeChildrenY(int y) {
		for (VisualObject v : this.getChildren()) {
			v.setY(v.getY() + y);
		}
	}

	private final boolean isBeingDraggedFromLeft() {
		return beingDraggedFromLeft;
	}

	private final void setBeingDraggedFromLeft(boolean beingDraggedFromLeft) {
		this.beingDraggedFromLeft = beingDraggedFromLeft;
	}

	private final boolean isBeingDraggedFromRight() {
		return beingDraggedFromRight;
	}

	private final void setBeingDraggedFromRight(boolean beingDraggedFromRight) {
		this.beingDraggedFromRight = beingDraggedFromRight;
	}

	private final boolean isBeingDraggedFromTop() {
		return beingDraggedFromTop;
	}

	private final void setBeingDraggedFromTop(boolean beingDraggedFromTop) {
		this.beingDraggedFromTop = beingDraggedFromTop;
	}

	private final boolean isBeingDraggedFromBottom() {
		return beingDraggedFromBottom;
	}

	private final void setBeingDraggedFromBottom(boolean beingDraggedFromBottom) {
		this.beingDraggedFromBottom = beingDraggedFromBottom;
	}

	private final int getLastX() {
		return lastX;
	}

	private final void setLastX(int lastX) {
		this.lastX = lastX;
	}

	private final int getLastY() {
		return lastY;
	}

	private final void setLastY(int lastY) {
		this.lastY = lastY;
	}

	private final boolean isBeingDragged() {
		return isBeingDragged;
	}

	private final void setBeingDragged(boolean isBeingDragged) {
		this.isBeingDragged = isBeingDragged;
	}

}
