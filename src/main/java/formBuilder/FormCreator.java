package formBuilder;

import canvaswindow.MyCanvasWindow;
import gui.form.base.FormContainer;
import gui.form.utility.FormBuilder;
import guiToApplication.FormWrapper;
import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.LogicalObjectVisitor;
import logicalobjects.Method;
import logicalobjects.Parameter;

/**
 * A visitor to create the Form for the corresponding logical object.
 */
public class FormCreator implements LogicalObjectVisitor<Void> {
	private FormBuilder<FormWrapper> formBuilder;
	private FormContainer formContainer;
	private boolean isNew;

	/**
	 * Creates a new FormCreator
	 * 
	 * @param o
	 *            LogicalObject for which a Form needs to be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 */
	public FormCreator(LogicalObject o, FormContainer formContainer, boolean isNew) {
		this.setFormContainer(formContainer);
		this.setNew(isNew);
		this.startVisit(o);
	}

	@Override
	public Void visit(LogicalClass c) {
		// No form for LogicalClass exists.
		return null;
	}

	@Override
	public Void visit(Method c) {
		this.setFormBuilder(new MethodFormBuilder(c, this.getFormContainer(), isNew()));
		return null;
	}

	@Override
	public Void visit(Attribute c) {
		this.setFormBuilder(new AttributeFormBuilder(c, this.getFormContainer(), isNew()));
		return null;
	}

	@Override
	public Void visit(Association c) {
		// No form exists for an association.
		return null;
	}

	private FormBuilder<FormWrapper> getFormBuilder() {
		return formBuilder;
	}

	private void setFormBuilder(FormBuilder<FormWrapper> formBuilder) {
		this.formBuilder = formBuilder;
	}

	private FormContainer getFormContainer() {
		return formContainer;
	}

	private void setFormContainer(FormContainer formContainer) {
		this.formContainer = formContainer;
	}

	/**
	 * Get the FormWrapper of this FormCreator
	 * @return FormWrapper of this FormCreator
	 */
	public FormWrapper getForm() {
		return this.getFormBuilder().getForm();
	}

	@Override
	public Void visit(Parameter parameter) {
		// No form for parameter exists.
		return null;
	}

	private final boolean isNew() {
		return isNew;
	}

	private final void setNew(boolean isNew) {
		this.isNew = isNew;
	}

}
