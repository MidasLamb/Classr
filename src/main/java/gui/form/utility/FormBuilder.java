package gui.form.utility;

import gui.form.base.Form;
import gui.form.base.FormObject;
import gui.form.base.Label;

/**
 * An abstract class to help build forms.
 * 
 * @param <T>
 *            a subclass of Form
 */
public abstract class FormBuilder<T extends Form> {
	private T form;
	private FormObject lastAddedFormObject;

	/**
	 * The function you should implement to build a Form.
	 */
	protected abstract void buildForm();

	/**
	 * Add a FormObject to this Form.
	 * 
	 * @param f
	 *            FormObject to be added.
	 */
	protected final void addFormObject(FormObject f) {
		this.setLastAddedFormObject(f);
		this.form.addFormObject(f);
	}

	/**
	 * Add a Label to the top of the last added FormObject
	 * 
	 * @param s
	 *            text that should be displayed in the Label
	 */
	protected final void addLabelToTopOfLastFormObject(String s) {
		Label l = this.getLastAddedFormObject().createLabel(FormObject.LabelPosition.TOP, s);
		this.addFormObject(l);
	}

	/**
	 * Add a Label to the right of the last added FormObject
	 * 
	 * @param s
	 *            text that should be displayed in the Label
	 */
	protected final void addLabelToRightOfLastFormObject(String s) {
		Label l = this.getLastAddedFormObject().createLabel(FormObject.LabelPosition.RIGHT, s);
		this.addFormObject(l);
	}

	/**
	 * Set the Form of this FormBuilder
	 * 
	 * @param form
	 *            Form to be set as the Form of this FormBuilder.
	 */
	protected final void setForm(T form) {
		this.form = form;
	}

	/**
	 * Get the Form of this FormBuilder
	 * 
	 * @return the Form of this FormBuilder
	 */
	public T getForm() {
		if (this.form == null) {
			this.buildForm();
		}
		return this.form;
	}

	/**
	 * Get the FormObject that is last added to the Form
	 * 
	 * @return last added FormObject
	 */
	protected final FormObject getLastAddedFormObject() {
		return lastAddedFormObject;
	}

	/**
	 * Set the last added FormObject to the given FormObject
	 * 
	 * @param lastAddedFormObject
	 *            FormObject that has been added last
	 */
	private void setLastAddedFormObject(FormObject lastAddedFormObject) {
		this.lastAddedFormObject = lastAddedFormObject;
	}
}
