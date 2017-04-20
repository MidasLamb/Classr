package gui.form.base;

public interface FormObjectChild{
	FormObject getNextSibling();
	FormObject getPreviousSibling();
	boolean hasNextSibling();
	boolean hasPreviousSibling();
	FormObject getParent();
}
