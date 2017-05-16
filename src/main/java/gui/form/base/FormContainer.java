package gui.form.base;

public interface FormContainer<T extends Form> {
	public void close();
	public void switchTo(T f);
	public FormContainer<T> getExtraContainer();
}
