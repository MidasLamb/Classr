package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.form.utility.DefaultCheckBox;
import gui.form.utility.DefaultRadioButton;
import gui.form.utility.FormBuilder;
import gui.form.utility.OkButton;
import gui.form.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visibilities.Visibility;

public abstract class MethodParameterFormBuilder extends FormBuilder<FormWrapper> {
	private MyCanvasWindow window;
	private Parameter parameter;
	private boolean closedByOk;

	public MethodParameterFormBuilder(Parameter parameter, MyCanvasWindow window) {
		this.window = window;
		this.parameter = parameter;
		this.closedByOk = false;
	}
	
	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));
		
		RegexCheckedInputBox parName = new RegexCheckedInputBox(parameter.getName(), "^[a-z][a-zA-Z0-9_]*", 10, 10, 100, 16);
		this.addFormObject(parName);
		this.addLabelToTopOfLastFormObject("Parameter name");

		RegexCheckedInputBox parType = new RegexCheckedInputBox(parameter.getType(), "^[a-z][a-zA-Z0-9_]*", 10, 100, 100, 16);
		this.addFormObject(parType);
		this.addLabelToTopOfLastFormObject("Parameter type");	
		
		MethodParameterFormBuilder t = this;
		OkButton ok = new OkButton(10, 500, 50, 50){

			@Override
			protected void onOk() {
				parameter.setName(parName.getText());
				parameter.setType(parType.getText());
				t.onOk();
				getForm().close();
			}
			
		};
		
		ok.addCheckableConstraint(parName);
		ok.addCheckableConstraint(parType);
		
		this.addFormObject(ok);
		
		Button cancel = new Button("Cancel", 110,500,50,50){

			@Override
			protected void onAction() {
				getForm().close();
				onCancel();
			}
		};
		
		this.addFormObject(cancel);
	}
	
	public abstract void onOk();
	
	public void onCancel(){
		// Default behavior is to do nothing
	}

}
