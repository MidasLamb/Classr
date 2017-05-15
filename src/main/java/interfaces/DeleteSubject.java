package interfaces;

/**
 * An interface for classes that want to have deleteListeners
 */
public interface DeleteSubject {
	/**
	 * To add a new deleteListener
	 * @param 	deletelistener
	 * 			the new deleteListener
	 */
	public void addDeleteListener(DeleteListener deletelistener);
	
	/**
	 * To remove a deleteListener
	 * @param 	deletelistener
	 * 			the deleteListener that need to be removed
	 */
	public void removeDeleteListener(DeleteListener deletelistener);
	
	/**
	 * A function that notifies the deleteListeners
	 */
	public void notifyDeleteListeners();
}
