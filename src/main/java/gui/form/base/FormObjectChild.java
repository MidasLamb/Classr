package gui.form.base;

public interface FormObjectChild{
	FormObject getNextChild();
	FormObject getPreviousChild();
	boolean hasNextChild();
	boolean hasPreviousChild();
	FormObject getParent();
}
