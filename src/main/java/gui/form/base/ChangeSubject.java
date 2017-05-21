package gui.form.base;

import java.util.Collection;

public interface ChangeSubject {

	
	public void addChangeListener(ChangeListener c);
	
	public void removeChangeListener(ChangeListener c);
	
	void notifyChangeListeners();
}
