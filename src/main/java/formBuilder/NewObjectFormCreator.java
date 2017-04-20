package formBuilder;

import canvaswindow.MyCanvasWindow;
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
 * Builds a Form when a new object is created
 */
public class NewObjectFormCreator implements LogicalObjectVisitor<Void> {
	private FormBuilder<FormWrapper> formBuilder;
	private MyCanvasWindow window;

	/**
	 * Create a new NewObjectFormCreator.
	 * 
	 * @param o
	 *            LogicalObject for which this Form is to be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 */
	public NewObjectFormCreator(LogicalObject o, MyCanvasWindow window) {
		this.setWindow(window);
		this.startVisit(o);
	}

	@Override
	public Void visit(LogicalClass c) {
		// No form for LogicalClasses exist.
		return null;
	}

	@Override
	public Void visit(Method c) {
		this.setFormBuilder(new MethodFormBuilder(c, this.getWindow(), true));
		return null;
	}

	@Override
	public Void visit(Attribute c) {
		this.setFormBuilder(new AttributeFormBuilder(c, this.getWindow(), true));
		return null;
	}

	@Override
	public Void visit(Association c) {
		// No form for Associations exist.
		return null;
	}

	private FormBuilder<FormWrapper> getFormBuilder() {
		return formBuilder;
	}

	private void setFormBuilder(FormBuilder<FormWrapper> formBuilder) {
		this.formBuilder = formBuilder;
	}

	private MyCanvasWindow getWindow() {
		return window;
	}

	private void setWindow(MyCanvasWindow window) {
		this.window = window;
	}

	/**
	 * Get the FormWrapper of this NewObjectFormBuilder
	 * @return FormWrapper of this NewObjectFormBuilder
	 */
	public FormWrapper getForm() {
		return this.getFormBuilder().getForm();
	}

	@Override
	public Void visit(Parameter parameter) {
		// No Form for parameters exist.
		return null;
	}

}
