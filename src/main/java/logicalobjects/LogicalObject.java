package logicalobjects;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public abstract class LogicalObject implements DeleteSubject, UpdateSubject {
	private boolean isDeleted = false;
	private Collection<DeleteListener> deleteListeners = new ArrayList<DeleteListener>();
	private Collection<UpdateListener> updateListeners = new ArrayList<UpdateListener>();
	
	private String name = "";

	/**
	 * Returns the name of this LogicalObject
	 * 
	 * @return the name of this LogicalObject
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name of this LogicalObject
	 * 
	 * @param 	name
	 *         	the name to be set
	 * @throws 	IllegalArgumentException
	 * 			if the given name is not allowed
	 */
	public final void setName(String name) throws IllegalArgumentException {
		if (this.canHaveAsName(name)) {
			this.name = name;
			this.notifyUpdateListeners();
		} else {
			throw new IllegalArgumentException("Invalid name. Check if the name is valid with canHaveAsName(name).");
		}
	}

	/**
	 * Delete this LogicalObject
	 */
	public final void delete() {
		if (!isDeleted()) {
			setDeleted(true);
			onDelete();
			notifyDeleteListeners();
		}
	}

	@Override
	public void notifyDeleteListeners() {
		for (DeleteListener d : this.getDeleteListeners())
			d.getNotifiedSubjectDeleted(this);
	}

	/**
	 * Function that contains the actions that need to be done when this object
	 * will be deleted
	 */
	protected abstract void onDelete();

	/**
	 * @return True if the isDeleted parameter is set, False otherwise
	 */
	public final boolean isDeleted() {
		return this.isDeleted;
	}

	/**
	 * Sets the isDeleted parameter
	 * 
	 * @param isDeleted
	 *            The new value for the isDeleted value
	 */
	private void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * @return the deleteListeners
	 */
	private Collection<DeleteListener> getDeleteListeners() {
		return deleteListeners;
	}

	/**
	 * Sets the deleteListeners to the given deleteListeners
	 * 
	 * @param deleteListeners
	 *            the new deleteListeners
	 */
	private void setDeleteListeners(Collection<DeleteListener> deleteListeners) {
		this.deleteListeners = deleteListeners;
	}

	/**
	 * Accepts a LogicalObjectVisitor
	 * 
	 * @param v
	 *            the logicalObjectVisitor
	 * @return an object as defined by the LogicalObjectVisitor
	 */
	abstract Object accept(LogicalObjectVisitor<?> v);

	@Override
	public final void addDeleteListener(DeleteListener deletelistener) {
		this.getDeleteListeners().add(deletelistener);
	}

	@Override
	public final void removeDeleteListener(DeleteListener deletelistener) {
		Collection<DeleteListener> cd = new ArrayList<DeleteListener>();
		cd.remove(deletelistener);
		this.setDeleteListeners(cd);
	}

	/**
	 * Returns whether this logical object can have the provided string as a
	 * valid name
	 * 
	 * @return true if the string would be a valid name, false otherwise
	 */
	public abstract boolean canHaveAsName(String string);
	
	@Override
	public final void addUpdateListener(UpdateListener Updatelistener) {
		this.getUpdateListeners().add(Updatelistener);
	}

	@Override
	public final void removeUpdateListener(UpdateListener Updatelistener) {
		Collection<UpdateListener> cd = new ArrayList<UpdateListener>();
		cd.remove(Updatelistener);
		this.setUpdateListeners(cd);
	}
	
	@Override
	public void notifyUpdateListeners() {
		for (UpdateListener d : this.getUpdateListeners())
			d.getNotifiedOfUpdate(this);
	}

	private final Collection<UpdateListener> getUpdateListeners() {
		return updateListeners;
	}

	private final void setUpdateListeners(Collection<UpdateListener> updateListeners) {
		this.updateListeners = updateListeners;
	}
}
