package visualobjects;

import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.DELETE;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

import command.Controller;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.inputHandlers.Typable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import logicalobjects.LogicalObject;

/**
 * A class of Visual objects, involving three coordinates, a width and height, a
 * list of children, a parent, a logical object, a collection of
 * deleteListeners, a selected state and a deleted state. This is a class for
 * the objects that will visually represent the logical objects of our program
 * and thus will be drawn on a canvas.
 * 
 * @author team 11
 */
public abstract class VisualObject<L extends LogicalObject> implements DeleteListener, DeleteSubject, Typable {
	private int x;
	private int y;
	private int z;
	private int width;
	private int height;
	ArrayList<VisualObject<?>> children;
	private VisualObject<?> parent;
	private L logicalObject;
	private Collection<DeleteListener> deleteListeners;
	private boolean isSelected;
	public boolean isDeleted;
	private Color color;
	private Color forcedColor;
	private VisualObject<?> draggedChild;

	private final Controller controller;

	/**
	 * Constructs a new visual object with the stated coordinates, width, height
	 * and parent. Also updates the children of the stated parent, if it is not
	 * null.
	 * 
	 * @param x
	 *            the x-coordinate of the visual object
	 * @param y
	 *            the y-coordinate of the visual object
	 * @param z
	 *            the z-coordinate of the visual object
	 * @param width
	 *            the width of the visual object
	 * @param height
	 *            the height of the visual object
	 * @param parent
	 *            the parent visual object of this visual object
	 * @param controller
	 *            the controller for this object.
	 */
	public VisualObject(int x, int y, int z, int width, int height, VisualObject<?> parent, Controller controller) {
		setChildren(new ArrayList<VisualObject<?>>());
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
		this.controller = controller;
	}

	/**
	 * Adds a deletelistener to the list of deletelisteners
	 * 
	 * @param deletelistener
	 *            the deletelistener to add
	 */
	public final void addDeleteListener(DeleteListener deletelistener) {
		this.deleteListeners.add(deletelistener);
	}

	/**
	 * Removes a deletelistener from to the list of deletelisteners
	 * 
	 * @param deletelistener
	 *            the deletelistener to remove
	 */
	public final void removeDeleteListener(DeleteListener deletelistener) {
		Collection<DeleteListener> cd = getDeleteListeners();
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
	public void show(Graphics g) {
		Color c = g.getColor();
		if (this.isSelected() || this.hasSelectedAncestor()) {
			this.forceColor(Color.RED);
		} else {
			this.forceColor(null);
		}
		if (this.getColor() != null)
			g.setColor(this.getColor());
		if (this.getForcedColor() != null)
			g.setColor(this.getForcedColor());

		this.draw(g);
		for (VisualObject<?> v : this.getChildren()) {
			v.show(g);
		}
		if (this.isSelected()) {
			g.setColor(c);
		}
	}

	/**
	 * Draws the visualObject
	 * 
	 * @param g
	 *            the graphics to draw the visualObject
	 */
	void draw(Graphics g) {

	}

	/**
	 * Function that is called on deletion
	 */
	void onDelete() {

	}

	/**
	 * Deletes this visual object
	 */
	public final void delete() {
		if (!this.isDeleted()) {
			this.setDeleted(true);
			if (this.getLogicalObject() != null)
				this.getLogicalObject().delete();
			this.onDelete();
			Container c = this.getContainer();
			if (c != null) {
				if (c.getSelected() != null && c.getSelected().equals(this))
					c.switchSelectedTo(null);
			}

			for (DeleteListener d : this.getDeleteListeners()) {
				d.getNotifiedSubjectDeleted(this);
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
	public final VisualObject<?> select(int x, int y) {
		for (VisualObject<?> v : this.getChildren()) {
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
		VisualObject<?> clickedChild = getClickedChild(sc);
		if (clickedChild != null)
			clickedChild.onClick(sc);
	}

	/**
	 * Triggers the onDoubleClick function of the child object that is clicked
	 * 
	 * @param dc
	 *            The double click object
	 */
	void onDoubleClick(DoubleClick dc) {
		VisualObject<?> clickedChild = getClickedChild(dc);
		if (clickedChild != null)
			clickedChild.onDoubleClick(dc);
	}

	/**
	 * Given a click this function returns the child on which is clicked
	 * 
	 * @param click
	 *            the click that happened
	 * @return the child on which there is clicked, otherwise null
	 */
	private VisualObject<?> getClickedChild(MouseClick click) {
		// Reduce is to get the last element
		Optional<VisualObject<?>> clickedChild = getChildren().stream().filter(x -> x.isIn(click.getX(), click.getY()))
				.sorted(new VisualObjectComparator()).reduce((a, b) -> b);
		if (clickedChild.isPresent())
			return clickedChild.get();
		return null;
	}

	/**
	 * Triggers the onDragEnd function of the child where there is dragged too
	 * 
	 * @param d
	 *            The drag object
	 */
	public void onDragEnd(Drag d) {
		setDraggedChild(null);
		getChildren().forEach(x -> x.onDragEnd(d));
	}

	/**
	 * Triggers the onDragUpdate function of the child that is being dragged if
	 * there is one
	 * 
	 * @param drag
	 *            the drag object
	 */
	public void onDragUpdate(Drag drag) {
		if (getDraggedChild() == null)
			setDraggedChild(getClickedChild(drag));
		if (getDraggedChild() != null)
			getDraggedChild().onDragUpdate(drag);
	}

	/**
	 * @param c
	 *            the color for this VisualObject
	 */
	protected final void setColor(Color c) {
		this.color = c;
	}

	/**
	 * 
	 * @param c
	 *            the color that all the children of this VisualObject are
	 *            forced to use
	 */
	protected final void forceColor(Color c) {
		setForcedColor(c);
		for (VisualObject<?> v : this.getChildren())
			v.forceColor(c);
	}

	/**
	 * Returns whether a coordinate is in the visual object
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
	 * Checks if c is between a and b
	 * 
	 * @param a
	 *            The lower bound
	 * @param b
	 *            The upper bound
	 * @param c
	 *            The value to check
	 * 
	 * @return wheter or not c is between a and b.
	 */
	final static boolean isBetween(int a, int b, int c) {
		return a <= c && b >= c;
	}

	/**
	 * 
	 * @return the container where this object is in
	 */
	public final Container getContainer() {
		VisualObject<?> v = this;
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
	void afterDeleteChild(VisualObject<?> child) {

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
	void setX(int x) {
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
	void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Sets the height of this VisualObject
	 * 
	 * @param height
	 *            the height to be set
	 */
	void setHeight(int height) {
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
	final void setParent(VisualObject<?> parent) {
		this.parent = parent;
	}

	/**
	 * Returns the parent VisualObject of this VisualObject
	 * 
	 * @return the parent VisualObject of this VisualObject
	 */
	public VisualObject<?> getParent() {
		return this.parent;
	}

	/**
	 * Sets the list of children VisualObjects of this VisualObject
	 * 
	 * @param list
	 *            the list of children VisualObjects
	 */
	private void setChildren(ArrayList<VisualObject<?>> list) {
		this.children = list;
	}

	/**
	 * Returns the list of children VisualObjects of this VisualObject
	 * 
	 * @return the list of children VisualObjects of this VisualObject
	 */
	public final ArrayList<VisualObject<?>> getChildren() {
		return new ArrayList<VisualObject<?>>(this.children);
	}

	/**
	 * Adds a child to the list of children
	 * 
	 * @param c
	 *            the child to be added
	 */
	public final void addChild(VisualObject<?> c) {
		this.children.add(c);
		c.addDeleteListener(new DeleteListener() {

			@Override
			public void getNotifiedSubjectDeleted(DeleteSubject subject) {
				removeChild(c);

			}

		});
		this.children.sort(new VisualObjectComparator());
	}

	/**
	 * Removes a child from the list of children
	 * 
	 * @param c
	 *            the child to be removed
	 * 
	 * @return Whether or not the child was removed
	 */
	public final boolean removeChild(VisualObject<?> c) {
		if (this.children.remove(c)) {
			this.afterDeleteChild(c);
			return true;
		}
		return false;
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
	public L getLogicalObject() {
		return logicalObject;
	}

	/**
	 * Sets the LogicalObject belonging to this VisualObject
	 * 
	 * @param object
	 *            the LogicalObject to be set
	 */
	final void setLogicalObject(L object) {
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

	/**
	 * A class of VisualObjectComparators which compares visual objects by their
	 * z-coordinates.
	 * 
	 * @author team 11
	 */
	private class VisualObjectComparator implements Comparator<VisualObject<?>> {
		@Override
		public int compare(VisualObject<?> x, VisualObject<?> y) {
			int diff = x.getZ() - y.getZ();
			return diff;
		}

	}

	/**
	 * Handles a given AsciiKey
	 * 
	 * @param key
	 *            the key that need to be handled
	 */
	public void handleAsciiKey(AsciiKey key) {

	};

	/**
	 * Handles function keys which effect this visual object, more specifically
	 * the delete key.
	 * 
	 * @param key
	 *            the function key to handle
	 */
	public void handleFunctionKey(FunctionKey key) {
		if (key.getKeyType() == DELETE) {
			Backend.delete();
		}
	};

	/**
	 * Returns the deletelisteners of this visual object.
	 * 
	 * @return the deletelisteners of this visual object
	 */
	private Collection<DeleteListener> getDeleteListeners() {
		return deleteListeners;
	}

	/**
	 * Sets the deletelisteners of this visual object
	 * 
	 * @param deleteListeners
	 *            the deletelisteners to be set
	 */
	private void setDeleteListeners(Collection<DeleteListener> deleteListeners) {
		this.deleteListeners = deleteListeners;
	}

	/**
	 * Notifies the listeners that this visual object is being deleted by
	 * calling the delete method.
	 * 
	 */
	@Override
	public void getNotifiedSubjectDeleted(DeleteSubject subject) {
		this.delete();
	}

	/**
	 * 
	 * @return whether the visual object is deleted.
	 */
	private boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * Sets the deleted state of this visual object
	 * 
	 * @param isDeleted
	 *            a boolean value indicating whether the visual object is
	 *            deleted.
	 */
	private void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 *
	 * @return whether this visual object has an ancestor cisual object that is
	 *         selected
	 */
	boolean hasSelectedAncestor() {
		VisualObject<?> v = this;
		while (v != null) {
			if (v.isSelected())
				return true;
			v = v.getParent();
		}
		return false;
	}

	@Override
	public void notifyDeleteListeners() {
		getDeleteListeners().forEach(x -> x.getNotifiedSubjectDeleted(this));
	}

	/**
	 * @return the ForcedColor
	 */
	private final Color getForcedColor() {
		return forcedColor;
	}

	/**
	 * @return the color
	 */
	private final Color getColor() {
		return color;
	}

	/**
	 * Returns the controller
	 * 
	 * @return the controller
	 */
	Controller getController() {
		return controller;
	}

	/**
	 * Sets the forced color
	 * 
	 * @param forcedColor
	 *            Color the forced color
	 */
	private void setForcedColor(Color forcedColor) {
		this.forcedColor = forcedColor;
	}

	/**
	 * The child that is currently being dragged
	 * 
	 * @return the child that is currently being dragged
	 */
	private VisualObject<?> getDraggedChild() {
		return draggedChild;
	}

	/**
	 * Sets the child that is getting dragged
	 * 
	 * @param draggedObject
	 *            the child that is getting dragged
	 */
	private void setDraggedChild(VisualObject<?> draggedObject) {
		this.draggedChild = draggedObject;
	}

	/**
	 * The decouple visitor
	 * 
	 * @param visitor
	 *            the visitor that needs to be visited
	 * @return the decoupler associated to this object
	 */
	public abstract Decoupler decoupleVisitor(CoupleVisitor visitor);

}
