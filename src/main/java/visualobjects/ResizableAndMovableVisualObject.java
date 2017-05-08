package visualobjects;

import gui.inputHandlers.clicks.Drag;

public abstract class ResizableAndMovableVisualObject extends VisualObject {
	private boolean beingResizedFromLeft;
	private boolean beingResizedFromRight;
	private boolean beingResizedFromTop;
	private boolean beingResizedFromBottom;

	private boolean isBeingResized;

	private boolean isBeingMoved;

	private int deadzone = 3;

	private int lastX;
	private int lastY;

	public ResizableAndMovableVisualObject(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x, y, z, width, height, parent);
		this.setBeingResizedFromBottom(false);
		this.setBeingResizedFromLeft(false);
		this.setBeingResizedFromRight(false);
		this.setBeingResizedFromTop(false);
		this.setBeingResized(false);

		this.setBeingMoved(false);
	}

	public boolean isOnLeftSide(int x, int y) {
		return (x >= this.getX() - deadzone && x <= this.getX() + deadzone)
				&& (y > this.getY() - deadzone && y < this.getY() + this.getHeight() + deadzone);
	}

	public boolean isOnTopSide(int x, int y) {
		return (y >= this.getY() - deadzone && y <= this.getY() + deadzone)
				&& (x > this.getX() - deadzone && x < this.getX() + this.getWidth() + deadzone);
	}

	public boolean isOnRightSide(int x, int y) {
		return (x >= this.getX() + this.getWidth() - deadzone && x <= this.getX() + this.getWidth() + deadzone)
				&& (y > this.getY() - deadzone && y < this.getY() + this.getHeight() + deadzone);
	}

	public boolean isOnBottomSide(int x, int y) {
		return (y >= this.getY() + this.getHeight() - deadzone && y <= this.getY() + this.getHeight() + deadzone)
				&& (x > this.getX() - deadzone && x < this.getX() + this.getWidth() + deadzone);
	}

	@Override
	public void onDragEnd(Drag drag) {
		super.onDragEnd(drag);
		this.setBeingResizedFromBottom(false);
		this.setBeingResizedFromLeft(false);
		this.setBeingResizedFromRight(false);
		this.setBeingResizedFromTop(false);
		this.setBeingResized(false);
		this.setBeingMoved(false);
	}

	protected abstract boolean isInMoveActivator(int x, int y);

	private void handleResize(Drag drag) {

		int x = drag.getStartX();
		int y = drag.getStartY();

		if (isBeingResizedFromLeft() || isBeingResizedFromBottom() || isBeingResizedFromRight()
				|| isBeingResizedFromTop()) {
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

		if ((isOnLeftSide(x, y) && !this.isBeingResized()) || isBeingResizedFromLeft()) {
			xChange = xDiff;
			widthChange = -xDiff;
			if (!this.isBeingResized())
				this.setBeingResizedFromLeft(true);
		}

		if ((isOnTopSide(x, y) && !this.isBeingResized()) || isBeingResizedFromTop()) {
			yChange = yDiff;
			heightChange = -yDiff;
			if (!this.isBeingResized())
				this.setBeingResizedFromTop(true);
		}

		if ((isOnRightSide(x, y) && !this.isBeingResized()) || isBeingResizedFromRight()) {
			widthChange = xDiff;
			if (!this.isBeingResized())
				this.setBeingResizedFromRight(true);
		}

		if ((isOnBottomSide(x, y) && !this.isBeingResized()) || isBeingResizedFromBottom()) {
			heightChange = yDiff;
			if (!this.isBeingResized())
				this.setBeingResizedFromBottom(true);
		}

		if (isBeingResizedFromLeft() || isBeingResizedFromBottom() || isBeingResizedFromRight()
				|| isBeingResizedFromTop()) {
			this.setBeingResized(true);
		}

		this.changeX(xChange);
		this.changeY(yChange);
		this.changeChildrenX(xChange);
		this.changeChildrenY(yChange);

		this.changeWidth(widthChange);
		this.changeHeight(heightChange);
	}

	private void handleMove(Drag drag) {
		int x = drag.getStartX();
		int y = drag.getStartY();

		if (isBeingMoved()) {
			x = this.getLastX();
			y = this.getLastY();
		}

		if (isInMoveActivator(x, y)) {
			this.setBeingMoved(true);
			this.setLastX(drag.getEndX());
			this.setLastY(drag.getEndY());

			int xDiff = drag.getEndX() - x;
			int yDiff = drag.getEndY() - y;

			this.changeX(xDiff);
			this.changeY(yDiff);
			this.changeChildrenX(xDiff);
			this.changeChildrenY(yDiff);
		}

	}

	@Override
	public void onDragUpdate(Drag drag) {
		if (!this.isBeingResized())
			handleMove(drag);
		if (!this.isBeingMoved())
			handleResize(drag);
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

	private final boolean isBeingResizedFromLeft() {
		return beingResizedFromLeft;
	}

	private final void setBeingResizedFromLeft(boolean beingResizedFromLeft) {
		this.beingResizedFromLeft = beingResizedFromLeft;
	}

	private final boolean isBeingResizedFromRight() {
		return beingResizedFromRight;
	}

	private final void setBeingResizedFromRight(boolean beingResizedFromRight) {
		this.beingResizedFromRight = beingResizedFromRight;
	}

	private final boolean isBeingResizedFromTop() {
		return beingResizedFromTop;
	}

	private final void setBeingResizedFromTop(boolean beingResizedFromTop) {
		this.beingResizedFromTop = beingResizedFromTop;
	}

	private final boolean isBeingResizedFromBottom() {
		return beingResizedFromBottom;
	}

	private final void setBeingResizedFromBottom(boolean beingResizedFromBottom) {
		this.beingResizedFromBottom = beingResizedFromBottom;
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

	private final boolean isBeingResized() {
		return isBeingResized;
	}

	private final void setBeingResized(boolean isBeingResized) {
		this.isBeingResized = isBeingResized;
	}

	private final boolean isBeingMoved() {
		return isBeingMoved;
	}

	private final void setBeingMoved(boolean isBeingMoved) {
		this.isBeingMoved = isBeingMoved;
	}

}
