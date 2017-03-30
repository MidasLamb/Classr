package interfaces;

public interface DeleteSubject {
	public void addDeleteListener(DeleteListener deletelistener);
	public void removeDeleteListener(DeleteListener deletelistener);
	/**
	 * Called when you want to notify the listeners of this subjects delete.
	 * @param subject
	 */
	public void notifySubjectDeleted(DeleteSubject subject);
}
