package formBuilder;

import canvaswindow.MyCanvasWindow;
import gui.utility.FormBuilder;
import guiToApplication.FormWrapper;
import interfaces.LogicalObjectVisitor;
import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.Method;

public class FormCreator implements LogicalObjectVisitor{
	private FormBuilder<FormWrapper> formBuilder;
	private MyCanvasWindow window;
	
	public FormCreator(LogicalObject o, MyCanvasWindow window){
		this.setWindow(window);
		o.accept(this);
	}

	@Override
	public void visit(LogicalClass c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Method c) {
		this.setFormBuilder(new MethodFormBuilder(c, this.getWindow()));
		
	}

	@Override
	public void visit(Attribute c) {
		this.setFormBuilder(new AttributeFormBuilder(c, this.getWindow()));
		
	}

	@Override
	public void visit(Association c) {
		// TODO Auto-generated method stub
		
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

}
