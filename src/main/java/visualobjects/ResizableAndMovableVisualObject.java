package visualobjects;

import java.util.ArrayList;
import java.util.Collection;

import command.Controller;
import command.MoveCommand;
import command.ResizeCommand;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.LogicalObject;

/**
 * A class of VisualObjects that are resizable and movable
 *
 * @param <T>
 *            The logical object class which will be linked to the object.
 */
public abstract class ResizableAndMovableVisualObject<T extends LogicalObject> extends VisualObject<T>
		implements UpdateListener, UpdateSubject {
	private boolean beingResizedFromLeft;
	private boolean beingResizedFromRight;
	private boolean beingResizedFromTop;
	private boolean beingResizedFromBottom;

	private boolean isBeingResized;

	private boolean isBeingMoved;

	private int deadzone = 3;

	private int lastX;
	private int lastY;

	private int resizeStartX;
	private int resizeStartY;
	private int resizeStartWidth;
	private int resizeStartHeight;

	private Collection<UpdateListener> updateListeners;

	/**
	 * Creates a new ResizableAndMovableVisualObject with the stated
	 * coordinates, width, height, parent VisualObject and Controller
	 * 
	 * @param x
	 *            The x coordinate for this object.
	 * @param y
	 *            the y coordinate for this object.
	 * @param z
	 *            the z coordinate for this object.
	 * @param width
	 *            the width for this object.
	 * @param height
	 *            the height for this object.
	 * @param parent
	 *            the parent for this object.
	 * @param controller
	 *            the controller for this object.
	 */
	public ResizableAndMovableVisualObject(int x, int y, int z, int width, int height, VisualObject<?> parent,
			Controller controller) {
		super(x, y, z, width, height, parent, controller);
		this.setBeingResizedFromBottom(false);
		this.setBeingResizedFromLeft(false);
		this.setBeingResizedFromRight(false);
		this.setBeingResizedFromTop(false);
		this.setBeingResized(false);

		this.setBeingMoved(false);
		this.setUpdateListeners(new ArrayList<UpdateListener>());
	}

	/**
	 * Returns whether a coordinate is within a margin of the left side of this
	 * VisualObject
	 * 
	 * @param x
	 *            The x value
	 * @param y
	 *            The y value
	 * @return Whether or not the x and y are on the left side of this object,
	 *         with regards to the deadzone.
	 * 
	 */
	public boolean isOnLeftSide(int x, int y) {
		return (x >= this.getX() - deadzone && x <= this.getX() + deadzone)
				&& (y > this.getY() - deadzone && y < this.getY() + this.getHeight() + deadzone);
	}

	/**
	 * Returns whether a coordinate is within a margin of the top side of this
	 * VisualObject
	 * 
	 * @param x
	 *            The x value
	 * @param y
	 *            The y value
	 * @return Whether or not the x and y are on the top side of this object,
	 *         with regards to the deadzone.
	 */
	public boolean isOnTopSide(int x, int y) {
		return (y >= this.getY() - deadzone && y <= this.getY() + deadzone)
				&& (x > this.getX() - deadzone && x < this.getX() + this.getWidth() + deadzone);
	}

	/**
	 * Returns whether a coordinate is within a margin of the right side of this
	 * VisualObject
	 * 
	 * @param x
	 *            The x value
	 * @param y
	 *            The y value
	 * @return Whether or not the x and y are on the right side of this object,
	 *         with regards to the deadzone.
	 * 
	 */
	public boolean isOnRightSide(int x, int y) {
		return (x >= this.getX() + this.getWidth() - deadzone && x <= this.getX() + this.getWidth() + deadzone)
				&& (y > this.getY() - deadzone && y < this.getY() + this.getHeight() + deadzone);
	}

	/**
	 * Returns whether a coordinate is within a margin of the bottom side of
	 * this VisualObject
	 * 
	 * @param x
	 *            The x value
	 * @param y
	 *            The y value
	 * @return Whether or not the x and y are on the bottom side of this object,
	 *         with regards to the deadzone.
	 * 
	 */
	public boolean isOnBottomSide(int x, int y) {
		return (y >= this.getY() + this.getHeight() - deadzone && y <= this.getY() + this.getHeight() + deadzone)
				&& (x > this.getX() - deadzone && x < this.getX() + this.getWidth() + deadzone);
	}

	@Override
	public void onDragEnd(Drag drag) {
		super.onDragEnd(drag);

		if (isBeingMoved()) {
			int xDiff = drag.getEndX() - this.getX();
			int yDiff = drag.getEndY() - this.getY();
			getController().executeCommand(new MoveCommand(this, drag.getStartX() - xDiff, drag.getStartY() - yDiff,
					drag.getEndX() - xDiff, drag.getEndY() - yDiff));
		} else if (isBeingResized()) {
			getController()
					.executeCommand(new ResizeCommand(this, getResizeStartX(), getResizeStartY(), getResizeStartWidth(),
							getResizeStartHeight(), this.getX(), this.getY(), this.getWidth(), this.getHeight()));
		}
		this.setBeingResizedFromBottom(false);
		this.setBeingResizedFromLeft(false);
		this.setBeingResizedFromRight(false);
		this.setBeingResizedFromTop(false);
		this.setBeingResized(false);
		this.setBeingMoved(false);
	}

	/**
	 * Indicates whether the coordinate is in the area to move the VisualObject
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @return true if the coordinate is in the area to move the VisualObject,
	 *         false otherwise
	 */
	protected abstract boolean isInMoveActivator(int x, int y);

	private void handleResize(Drag drag) {
		if (!isBeingResized()) {
			this.setResizeStartX(this.getX());
			this.setResizeStartY(this.getY());
			this.setResizeStartWidth(this.getWidth());
			this.setResizeStartHeight(this.getHeight());
		}

		int x = drag.getStartX();
		int y = drag.getStartY();

		if (isBeingResizedFromLeft() || isBeingResizedFromBottom() || isBeingResizedFromRight()
				|| isBeingResizedFromTop()) {
			x = this.getLastX();
			y = this.getLastY();
		}

		this.setLastX(drag.getEndX());
		this.setLastY(drag.getEndY());

		int newX = this.getX();
		int newY = this.getY();
		int newWidth = this.getWidth();
		int newHeight = this.getHeight();

		if ((isOnLeftSide(x, y) && !this.isBeingResized()) || isBeingResizedFromLeft()) {
			newWidth = this.getWidth() + (this.getX() - drag.getEndX());
			newWidth = newWidth < getMinimumWidth() ? getMinimumWidth() : newWidth;
			newX = this.getWidth() + this.getX() - newWidth;

			if (!this.isBeingResized())
				this.setBeingResizedFromLeft(true);
		}

		if ((isOnTopSide(x, y) && !this.isBeingResized()) || isBeingResizedFromTop()) {
			newHeight = this.getHeight() + (this.getY() - drag.getEndY());
			newHeight = newHeight < getMinimumHeight() ? getMinimumHeight() : newHeight;
			newY = this.getHeight() + this.getY() - newHeight;
			if (!this.isBeingResized())
				this.setBeingResizedFromTop(true);
		}

		if ((isOnRightSide(x, y) && !this.isBeingResized()) || isBeingResizedFromRight()) {

			newWidth = drag.getEndX() - this.getX();
			if (!this.isBeingResized())
				this.setBeingResizedFromRight(true);
		}

		if ((isOnBottomSide(x, y) && !this.isBeingResized()) || isBeingResizedFromBottom()) {
			newHeight = drag.getEndY() - this.getY();

			if (!this.isBeingResized())
				this.setBeingResizedFromBottom(true);
		}

		if (isBeingResizedFromLeft() || isBeingResizedFromBottom() || isBeingResizedFromRight()
				|| isBeingResizedFromTop()) {
			this.setBeingResized(true);
		}

		// Change children relative, because their position is relative to this.
		// Change yourself absolute, so resizing follows the mouse, not the
		// change of the mouse.

		this.setWidth(newWidth);
		this.setHeight(newHeight);

		this.changeChildrenX(newX - this.getX());
		this.setX(newX);
		this.changeChildrenY(newY - this.getY());
		this.setY(newY);

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
		getContainer().bringToFront(this);

		if (!this.isBeingMoved())
			handleResize(drag);
		if (!this.isBeingResized())
			handleMove(drag);
	}

	@Override
	void onClick(SingleClick sc) {
		getContainer().bringToFront(this);
		super.onClick(sc);
	}

	/**
	 * Changes this x position relatively.
	 * 
	 * @param x
	 *            The amount of x to change.
	 */
	private void changeX(int x) {
		this.setX(this.getX() + x);
	}

	/**
	 * Changes this y position relatively.
	 * 
	 * @param y
	 *            The amount of y to change.
	 */
	private void changeY(int y) {
		this.setY(this.getY() + y);
	}

	/**
	 * Changes this objects children's x relatively.
	 * 
	 * @param x
	 *            The amount of x to change.
	 */
	private void changeChildrenX(int x) {
		for (VisualObject<?> v : this.getChildren()) {
			v.setX(v.getX() + x);
		}
	}

	/**
	 * Changes this objects children's y relatively.
	 * 
	 * @param y
	 *            the amount of y to change.
	 */
	private void changeChildrenY(int y) {
		for (VisualObject<?> v : this.getChildren()) {
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

	/**
	 * A method to return the minimum width this VisualObject must have.
	 * 
	 * @return The minimum width this VisualObject must have.
	 */
	public abstract int getMinimumWidth();

	/**
	 * A method to return the minimum height this VisualObject must have.
	 * 
	 * @return The minimum height this VisualObject must have.
	 */
	public abstract int getMinimumHeight();

	@Override
	void setWidth(int width) {
		if (width >= this.getMinimumWidth())
			super.setWidth(width);
		else
			super.setWidth(this.getMinimumWidth());
		this.notifyUpdateListeners();
	}

	@Override
	void setHeight(int height) {
		if (height >= this.getMinimumHeight())
			super.setHeight(height);
		else
			super.setHeight(this.getMinimumHeight());
		this.notifyUpdateListeners();
	}

	/**
	 * Moves this VisualObject and its children to the stated coordinate
	 * 
	 * @param x
	 *            The x value to which to move.
	 * @param y
	 *            The y value to which to move.
	 */
	public void moveTo(int x, int y) {
		this.changeChildrenX(x - this.getX());
		this.changeChildrenY(y - this.getY());

		this.setX(x);
		this.setY(y);
		this.notifyUpdateListeners();
	}

	/**
	 * Resizes this VisualObject so that it matches the stated coordinates,
	 * width and height
	 * 
	 * @param x
	 *            The x value to move the object to.
	 * @param y
	 *            The y value to move the object to.
	 * @param width
	 *            The width to which to set the object.
	 * @param height
	 *            The height to which to set the object.
	 */
	public void resizeTo(int x, int y, int width, int height) {
		moveTo(x, y);
		this.setWidth(width);
		this.setHeight(height);
		this.notifyUpdateListeners();
	}

	private final int getResizeStartX() {
		return resizeStartX;
	}

	private final void setResizeStartX(int resizeStartX) {
		this.resizeStartX = resizeStartX;
	}

	private final int getResizeStartY() {
		return resizeStartY;
	}

	private final void setResizeStartY(int resizeStartY) {
		this.resizeStartY = resizeStartY;
	}

	private final int getResizeStartWidth() {
		return resizeStartWidth;
	}

	private final void setResizeStartWidth(int resizeStartWidth) {
		this.resizeStartWidth = resizeStartWidth;
	}

	private final int getResizeStartHeight() {
		return resizeStartHeight;
	}

	private final void setResizeStartHeight(int resizeStartHeight) {
		this.resizeStartHeight = resizeStartHeight;
	}

	@Override
	public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
		if (getMinimumWidth() > getWidth()) {
			this.setWidth(getMinimumWidth());
		}
	}

	@Override
	public void addUpdateListener(UpdateListener updateListener) {
		this.getUpdateListeners().add(updateListener);

	}

	@Override
	public void removeUpdateListener(UpdateListener updateListener) {
		this.getUpdateListeners().remove(updateListener);

	}

	@Override
	public void notifyUpdateListeners() {
		if (getUpdateListeners() != null)
			getUpdateListeners().stream().forEach(x -> x.getNotifiedOfUpdate(this));

	}

	/**
	 * Returns the update Listeners
	 * 
	 * @return the update Listeners
	 */
	private final Collection<UpdateListener> getUpdateListeners() {
		return updateListeners;
	}

	/**
	 * Sets the update listeners
	 * 
	 * @param updateListeners
	 *            the new update listeners
	 */
	private final void setUpdateListeners(Collection<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
	}

	@Override
	void setX(int x) {
		super.setX(x);
		this.notifyUpdateListeners();
	}

	@Override
	void setY(int y) {
		super.setY(y);
		this.notifyUpdateListeners();
	}

}
