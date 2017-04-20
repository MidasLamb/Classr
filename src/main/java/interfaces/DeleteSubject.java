package interfaces;

public interface DeleteSubject {
	public void addDeleteListener(DeleteListener deletelistener);
	public void removeDeleteListener(DeleteListener deletelistener);
	public void notifyListeners();
}
