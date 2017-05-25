package interfaces;

/**
 * A listener for DeleteSubjects
 */
public interface DeleteListener {
	/**
	 * Called when subject is deleted.
	 * 
	 * @param subject
	 *            the subject that is deleted
	 */
	public void getNotifiedSubjectDeleted(DeleteSubject subject);
}
