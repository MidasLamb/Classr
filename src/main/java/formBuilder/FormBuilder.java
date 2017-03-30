package formBuilder;

import gui.base.Form;
import gui.base.FormObject;
import gui.base.Label;
import guiToApplication.FormWrapper;

public abstract class FormBuilder {
	private FormWrapper form;
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
	
	protected final void setForm(FormWrapper form){
		this.form = form;
	}
	

	public FormWrapper getForm() {
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
