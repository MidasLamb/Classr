package gui.form.base;

public interface FormObjectWithChildren {
	FormObject getNextChild();
	FormObject getPreviousChild();
	boolean hasNextChild();
	boolean hasPreviousChild();
}
