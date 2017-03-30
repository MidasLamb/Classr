package interfaces;

public interface DeleteListener {
	/**
	 * Called when subject is deleted.
	 * @param subject
	 */
	public void notifySubjectDeleted(DeleteSubject subject);
}
