package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.base.Button;
import gui.base.CheckBox;
import gui.base.RadioButton;
import gui.base.RadioButtonGroup;
import gui.utility.DefaultCheckBox;
import gui.utility.DefaultRadioButton;
import gui.utility.FormBuilder;
import gui.utility.OkButton;
import gui.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Method;
import logicalobjects.Parameter;
import logicalobjects.Visibility;

public class MethodParameterFormBuilder extends FormBuilder<FormWrapper> {
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
		
		RegexCheckedInputBox parName = new RegexCheckedInputBox(parameter.getName(), "\\p{Lower}.*", 10, 10, 100, 16);
		this.addFormObject(parName);
		this.addLabelToTopOfLastFormObject("Parameter name");

		RegexCheckedInputBox parType = new RegexCheckedInputBox(parameter.getType(), "\\p{Lower}.*", 10, 100, 100, 16);
		this.addFormObject(parType);
		this.addLabelToTopOfLastFormObject("Parameter type");	
		
		OkButton ok = new OkButton(10, 500, 50, 50){

			@Override
			protected void onOk() {
				parameter.setName(parName.getText());
				parameter.setType(parType.getText());
				closedByOk = true;
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
			}
		};
		
		this.addFormObject(cancel);
	}

}
