package gui.form.utility;

import gui.form.base.Form;
import gui.form.base.FormObject;
import gui.form.base.Label;

public abstract class FormBuilder<T extends Form> {
	private T form;
	private FormObject lastAddedFormObject;

	protected abstract void buildForm();

	protected final void addFormObject(FormObject f) {
		this.setLastAddedFormObject(f);
		this.form.addFormObject(f);
	}

	protected final void addLabelToTopOfLastFormObject(String s) {
		Label l = this.getLastAddedFormObject().createLabel(FormObject.LabelPosition.TOP, s);
		this.addFormObject(l);
	}

	protected final void addLabelToRightOfLostFormObject(String s) {
		Label l = this.getLastAddedFormObject().createLabel(FormObject.LabelPosition.RIGHT, s);
		this.addFormObject(l);
	}

	protected final Form getInternalForm() {
		return this.form;
	}
	
	protected final void setForm(T form){
		this.form = form;
	}
	

	public T getForm() {
		if (this.form == null) {
			this.buildForm();
		}
		return this.form;
	}

	protected final FormObject getLastAddedFormObject() {
		return lastAddedFormObject;
	}

	private void setLastAddedFormObject(FormObject lastAddedFormObject) {
		this.lastAddedFormObject = lastAddedFormObject;
	}
}
