package gui.form.base;

public interface ChangeSubject {

	public void addChangeListener(ChangeListener c);
	
	public void removeChangeListener(ChangeListener c);
	
	void notifyChangeListeners();
}
