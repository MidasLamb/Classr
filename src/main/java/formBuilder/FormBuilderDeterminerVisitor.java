package formBuilder;

import command.Controller;
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
public class FormBuilderDeterminerVisitor implements LogicalObjectVisitor<Void> {
	private FormBuilder<FormWrapper> formBuilder;
	private final FormContainer<FormWrapper> formContainer;
	private final Controller controller;

	/**
	 * Creates a new FormCreator
	 * 
	 * @param o
	 *            LogicalObject for which a Form needs to be created
	 * @param formContainer
	 *            the container for the created form
	 * @param controller
	 *            the Controller to execute the commands
	 */
	public FormBuilderDeterminerVisitor(LogicalObject o, FormContainer<FormWrapper> formContainer,
			Controller controller) {
		this.formContainer = formContainer;
		this.controller = controller;
		this.startVisit(o);
	}

	@Override
	public Void visit(LogicalClass c) {
		// No form for LogicalClass exists.
		return null;
	}

	@Override
	public Void visit(Method c) {
		this.setFormBuilder(new MethodFormBuilder(c, this.getFormContainer(), controller));
		return null;
	}

	@Override
	public Void visit(Attribute c) {
		this.setFormBuilder(new AttributeFormBuilder(c, this.getFormContainer(), controller));
		return null;
	}

	@Override
	public Void visit(Association c) {
		// No form exists for an association.
		return null;
	}

	/**
	 * To get the current form builder
	 * 
	 * @return the current form builder
	 */
	private FormBuilder<FormWrapper> getFormBuilder() {
		return formBuilder;
	}

	/**
	 * Sets the current form builder
	 * 
	 * @param formBuilder
	 *            the new form builder
	 */
	private void setFormBuilder(FormBuilder<FormWrapper> formBuilder) {
		this.formBuilder = formBuilder;
	}

	/**
	 * Returns the current form container
	 * 
	 * @return the form container
	 */
	private FormContainer<FormWrapper> getFormContainer() {
		return formContainer;
	}

	/**
	 * Get the FormWrapper of this FormCreator
	 * 
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

}
