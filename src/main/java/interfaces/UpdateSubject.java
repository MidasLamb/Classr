package interfaces;

/**
 * An interface for classes that want to have updateListeners
 */
public interface UpdateSubject {
	/**
	 * To add a new updateListener
	 * @param 	updateListener
	 * 			the new updateListener
	 */
	public void addUpdateListener(UpdateListener updateListener);
	
	/**
	 * To remove a updateListener
	 * @param 	updateListener
	 * 			the updateListener that need to be removed
	 */
	public void removeUpdateListener(UpdateListener updateListener);
	
	/**
	 * A function that notifies the updateListeners
	 */
	public void notifyUpdateListeners();
}
