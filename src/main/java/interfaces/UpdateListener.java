package interfaces;

/**
 * A listener for UpdateSubjects
 */
public interface UpdateListener {
	/**
	 * Called when subject is updated.
	 * @param 	subject
	 * 			the subject that is updated
	 */
	public void getNotifiedOfUpdate(UpdateSubject updateSubject);
}
