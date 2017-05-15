package interfaces;

public interface UpdateSubject {
	public void addUpdateListener(UpdateListener updateListener);
	public void removeUpdateListener(UpdateListener updateListener);
	public void notifyUpdateListeners();
}
