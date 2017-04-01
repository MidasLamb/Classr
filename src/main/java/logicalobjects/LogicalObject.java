package logicalobjects;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import interfaces.LogicalObjectElement;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public abstract class LogicalObject implements DeleteSubject, LogicalObjectElement{
	private boolean isDeleted = false;
	private Collection<DeleteListener> deleteListeners = new ArrayList<DeleteListener>();
	private String name;

	public final void addDeleteListener(DeleteListener deletelistener) {
		this.getDeleteListeners().add(deletelistener);
	}

	public final void removeDeleteListener(DeleteListener deletelistener) {
		Collection<DeleteListener> cd = new ArrayList<DeleteListener>();
		cd.remove(deletelistener);
		this.setDeleteListeners(cd);
	}

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
	 * @param name
	 *            the name to be set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	public final void delete() {
		if (this.isDeleted()) {
			System.out.println("This object is already deleted!");
		} else {
			this.onDelete();
			this.notifySubjectDeleted(this);
		}
		this.setDeleted(true);
	}
	

	@Override
	public void notifySubjectDeleted(DeleteSubject subject) {
		for (DeleteListener d: this.getDeleteListeners())
			d.notifySubjectDeleted(this);
	}

	protected abstract void onDelete();

	public final boolean isDeleted() {
		return this.isDeleted;
	}

	private void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	private Collection<DeleteListener> getDeleteListeners() {
		return deleteListeners;
	}

	private void setDeleteListeners(Collection<DeleteListener> deleteListeners) {
		this.deleteListeners = deleteListeners;
	}
}
