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
public class FormCreator implements LogicalObjectVisitor<Void> {
	private FormBuilder<FormWrapper> formBuilder;
	private final FormContainer formContainer;
	private final boolean isNew;
	private final Controller controller;
	
	
	/**
	 * Creates a new FormCreator
	 * 
	 * @param o
	 *            LogicalObject for which a Form needs to be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 */
	public FormCreator(LogicalObject o, FormContainer formContainer, 
			boolean isNew, Controller controller) {
		this.formContainer = formContainer;
		this.isNew = isNew;
		this.startVisit(o);
		this.controller = controller;
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

	/**
	 * To get the current form builder
	 * @return the current form builder
	 */
	private FormBuilder<FormWrapper> getFormBuilder() {
		return formBuilder;
	}

	/**
	 * Sets the current form builder
	 * @param 	formBuilder
	 * 			the new form builder
	 */
	private void setFormBuilder(FormBuilder<FormWrapper> formBuilder) {
		this.formBuilder = formBuilder;
	}

	/**
	 * Returns the current form container
	 * @return the form container
	 */
	private FormContainer getFormContainer() {
		return formContainer;
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

	/**
	 * Returns is this is new
	 * @return	true if this is new, false otherwise
	 */
	private final boolean isNew() {
		return isNew;
	}
	
	/**
	 * Returns the controller
	 * @return the used controller
	 */
	private Controller getController() {
		return controller;
	}

}
