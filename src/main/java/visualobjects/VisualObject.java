package visualobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;
import interfaces.DeleteListener;
import objects.LogicalObject;

public abstract class VisualObject implements DeleteListener {
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	private ArrayList<VisualObject> children;
	private boolean isSelected;
	private VisualObject parent;
	private LogicalObject logicalObject;
	private Collection<DeleteListener> deleteListeners;

	public boolean isDeleted;

	public VisualObject(int x, int y, int z, int width, int height, VisualObject parent) {
		setChildren(new ArrayList<VisualObject>());
		this.setDeleteListeners(new ArrayList<DeleteListener>());
		this.setDeleted(false);
		setX(x);
		setY(y);
		setZ(z);
		setWidth(width);
		setHeight(height);
		setParent(parent);
		if (parent != null)
			parent.addChild(this);
	}

	public final void addDeleteListener(DeleteListener deletelistener) {
		this.getDeleteListeners().add(deletelistener);
	}

	public final void removeDeleteListener(DeleteListener deletelistener) {
		Collection<DeleteListener> cd = new ArrayList<DeleteListener>();
		cd.remove(deletelistener);
		this.setDeleteListeners(cd);
	}

	/**
	 * Shows this visual object and his children shows this object red if it is
	 * selected
	 * 
	 * @param g
	 *            Graphics g
	 */
	public final void show(Graphics g) {
		// draw backgrounds first
		if (this.isSelected())
			g.setColor(Color.red);
		this.draw(g);
		for (VisualObject v : this.getChildren()) {
			v.show(g);
		}
		g.setColor(Color.black);
	}

	void draw(Graphics g) {

	}

	void onDelete() {

	}

	/**
	 * Deletes this visual object
	 */
	public final void delete() {
		if (!this.isDeleted()) {
			this.setDeleted(true);
			this.onDelete();
			Container c = this.getContainer();
			if (c != null) {
				if (c.getSelected() != null && c.getSelected().equals(this))
					c.switchSelectedTo(null);
			}

			this.getParent().removeChild(this);

			for (DeleteListener d : this.getDeleteListeners()) {
				d.notifyDelete();
			}
		}
	}

	/**
	 * 
	 * @param x
	 *            The x-coordinate of the pixel that is selected
	 * @param y
	 *            The y-coordinate of the pixel that is selected
	 * @return the visual object that is in this region it can be one of the
	 *         children or this object itself
	 */
	public final VisualObject select(int x, int y) {
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(x, y)) {
				return v.select(x, y);
			}
		}
		return this;
	}

	/**
	 * Triggers the onClick function of the child object that is clicked
	 * 
	 * @param sc
	 *            The single click object
	 */
	void onClick(SingleClick sc) {
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(sc.getX(), sc.getY())) {
				v.onClick(sc);
				// Return is required because association text would get two
				// clicks when one is pressed, because it get's it from both
				// parents
				return;
			}
		}
	}

	/**
	 * Triggers the onDoubleClick function of the child object that is clicked
	 * 
	 * @param dc
	 *            The double click object
	 */
	void onDoubleClick(DoubleClick dc) {
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(dc.getX(), dc.getY())) {
				v.onDoubleClick(dc);
			}
		}
	}

	/**
	 * Triggers the onDragStart function of the child where the dragging starts
	 * 
	 * @param d
	 *            The drag object
	 */
	void onDragStart(Drag d) {
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(d.getStartX(), d.getStartY())) {
				v.onDragStart(d);
			}
		}
	}

	/**
	 * Triggers the onDragEnd function of the child where there is dragged too
	 * 
	 * @param d
	 *            The drag object
	 */
	public void onDragEnd(Drag d) {
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(d.getEndX(), d.getEndY())) {
				v.onDragEnd(d);
			}
		}
	}

	/**
	 * 
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @return true if this object is at the given coordinates otherwise false
	 */
	boolean isIn(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}

	/**
	 * Checks if c is between a en b
	 */
	final static boolean isBetween(int a, int b, int c) {
		return a <= c && b >= c;
	}

	/**
	 * If delete is pressed this object will delete itself
	 * 
	 * @param e
	 *            Key event
	 */
	void handleKey(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DELETE)
			this.getLogicalObject().delete();
	}

	/**
	 * 
	 * @return the container where this object is in
	 */
	final Container getContainer() {
		VisualObject v = this;
		while (v != null && !(v instanceof Container))
			v = v.getParent();
		return (Container) v;
	}

	/**
	 * Actions to be executed after child is deleted: no actions
	 * 
	 * @param child
	 *            The child that is deleted
	 */
	void afterDeleteChild(VisualObject child) {

	}

	// Getters and setters

	/**
	 * Returns the x-coordinate of this VisualObject
	 * 
	 * @return the x-coordinate of this VisualObject
	 */
	final int getX() {
		return this.x;
	}

	/**
	 * Sets the x-coordinate of this VisualObject
	 * 
	 * @param x
	 *            the x-coordinate to be set
	 */
	final void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y-coordinate of this VisualObject
	 * 
	 * @param y
	 *            the y-coordinate to be set
	 */
	void setY(int y) {
		this.y = y;
	}

	/**
	 * Return the y-coordinate of this VisualObject
	 * 
	 * @return the y-coordinate of this VisualObject
	 */
	final int getY() {
		return this.y;
	}

	/**
	 * Returns the Width of this VisualObject
	 * 
	 * @return the width of this VisualObject
	 */
	int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this VisualObject
	 * 
	 * @param width
	 *            the width to be set
	 */
	final void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of this VisualObject
	 * 
	 * @param height
	 *            the height to be set
	 */
	final void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Returns the height of this VisualObject
	 * 
	 * @return the height of this VisualObject
	 */
	int getHeight() {
		return height;
	}

	/**
	 * Sets the parent VisualObject of this VisualObject
	 * 
	 * @param parent
	 *            the parent VisualObject to be set
	 */
	final void setParent(VisualObject parent) {
		this.parent = parent;
	}

	/**
	 * Returns the parent VisualObject of this VisualObject
	 * 
	 * @return the parent VisualObject of this VisualObject
	 */
	final VisualObject getParent() {
		return this.parent;
	}

	/**
	 * Sets the list of children VisualObjects of this VisualObject
	 * 
	 * @param list
	 *            the list of children VisualObjects
	 */
	private void setChildren(ArrayList<VisualObject> list) {
		this.children = list;
	}

	/**
	 * Returns the list of children VisualObjects of this VisualObject
	 * 
	 * @return the list of children VisualObjects of this VisualObject
	 */
	final ArrayList<VisualObject> getChildren() {
		return new ArrayList<VisualObject>(this.children);
	}

	/**
	 * Adds a child to the list of children
	 * 
	 * @param c
	 *            the child to be added
	 */
	private void addChild(VisualObject c) {
		this.children.add(c);
		this.children.sort(new VisualObjectComparator());
	}

	/**
	 * Removes a child from the list of children
	 * 
	 * @param c
	 *            the child to be removed
	 */
	final void removeChild(VisualObject c) {
		if (this.children.remove(c))
			this.afterDeleteChild(c);
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Sets whether or not the selected state of this VisualObject is true
	 * 
	 * @param b
	 *            the boolean value denoting whether or not the selected state
	 *            of this VisualObject is true
	 */
	void setSelected(boolean b) {
		this.isSelected = b;
	}

	/**
	 * Returns whether or not the selected state of this VisualObject is true
	 * 
	 * @return whether or not the selected state of this VisualObject is true
	 */
	final boolean isSelected() {
		return isSelected;
	}

	/**
	 * Returns the LogicalObject belonging to this VisualObject
	 * 
	 * @return the LogicalObject belonging to this VisualObject
	 */
	LogicalObject getLogicalObject() {
		return logicalObject;
	}

	/**
	 * Sets the LogicalObject belonging to this VisualObject
	 * 
	 * @param object
	 *            the LogicalObject to be set
	 */
	final void setLogicalObject(LogicalObject object) {
		this.logicalObject = object;
		this.logicalObject.addDeleteListener(this);
	}

	/**
	 * Returns the z-coordinate of this VisualObject
	 * 
	 * @return the z-coordinate of this VisualObject
	 */
	final int getZ() {
		return z;
	}

	/**
	 * Sets the z-coordinate of this VisualObject
	 * 
	 * @param z
	 *            the z-coordinate to be set
	 */
	private void setZ(int z) {
		this.z = z;
	}

	private class VisualObjectComparator implements Comparator<VisualObject> {
		@Override
		public int compare(VisualObject x, VisualObject y) {
			int diff = x.getZ() - y.getZ();
			return diff;
		}

	}

	private Collection<DeleteListener> getDeleteListeners() {
		return deleteListeners;
	}

	private void setDeleteListeners(Collection<DeleteListener> deleteListeners) {
		this.deleteListeners = deleteListeners;
	}

	@Override
	public void notifyDelete() {
		this.delete();
	}

	private boolean isDeleted() {
		return isDeleted;
	}

	private void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
