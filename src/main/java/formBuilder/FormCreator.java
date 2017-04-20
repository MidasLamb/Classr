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

public class FormCreator implements LogicalObjectVisitor<Void>{
	private FormBuilder<FormWrapper> formBuilder;
	private MyCanvasWindow window;
	
	public FormCreator(LogicalObject o, MyCanvasWindow window){
		this.setWindow(window);
		this.startVisit(o);
	}

	@Override
	public Void visit(LogicalClass c) {
		//No form for LogicalClass exists.
		return null;
	}

	@Override
	public Void visit(Method c) {
		this.setFormBuilder(new MethodFormBuilder(c, this.getWindow(), false));
		return null;
	}

	@Override
	public Void visit(Attribute c) {
		this.setFormBuilder(new AttributeFormBuilder(c, this.getWindow(), false));
		return null;
	}

	@Override
	public Void visit(Association c) {
		//No form exists for an association.
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
	
	public FormWrapper getForm(){
		return this.getFormBuilder().getForm();
	}

	@Override
	public Void visit(Parameter parameter) {
		// No  form for parameter exists.
		return null;
	}

}
