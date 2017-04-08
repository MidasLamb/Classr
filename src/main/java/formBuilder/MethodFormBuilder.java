package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.base.Button;
import gui.base.CheckBox;
import gui.base.ListBox;
import gui.base.RadioButton;
import gui.base.RadioButtonGroup;
import gui.utility.DefaultCheckBox;
import gui.utility.DefaultRadioButton;
import gui.utility.FormBuilder;
import gui.utility.OkButton;
import gui.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Attribute;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visibilities.Package;
import visibilities.Private;
import visibilities.Public;
import visibilities.Visibility;

public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private MyCanvasWindow window;
	
	private Button editParameter;
	private Button removeParameter;
	private ListBox<ParameterWrapper> parameters;

	public MethodFormBuilder(Method method, MyCanvasWindow window) {
		this.method = method;
		this.window = window;

	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));

		RegexCheckedInputBox methName = new RegexCheckedInputBox(method.getName(), ".*", 10, 10, 100, 16);
		this.addFormObject(methName);
		this.addLabelToTopOfLastFormObject("Method name");

		RegexCheckedInputBox methType = new RegexCheckedInputBox(method.getType(), ".*", 10, 100, 100, 16);

		this.addFormObject(methType);
		this.addLabelToTopOfLastFormObject("Method type");

		// Radio buttons for visibility. ---------------------------------------------------------------
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton publicButton = new DefaultRadioButton(group, 10, 250);
		this.addFormObject(publicButton);
		this.addLabelToRightOfLostFormObject("Public");
		RadioButton privateButton = new DefaultRadioButton(group, 10, 300);
		this.addFormObject(privateButton);
		this.addLabelToRightOfLostFormObject("Private");
		RadioButton packageButton = new DefaultRadioButton(group, 10, 350);
		this.addFormObject(packageButton);
		this.addLabelToRightOfLostFormObject("Package");
		//TODO protected missing

		// Static checkbox ---------------------------------------------------------------
		CheckBox staticCheckbox = new DefaultCheckBox(10, 400);


		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLostFormObject("Static");
		
		
		// Parameters ---------------------------------------------------------------
		parameters = new ListBox<ParameterWrapper>(10,450,100,100){

			@Override
			protected void onAction() {
				checkEditAndCancelButtons();
			}
			
		};
		
		this.addFormObject(parameters);
		this.addLabelToTopOfLastFormObject("Parameters");
		
		Button addParameter = new Button("Add", 150,450, 50, 50){

			@Override
			protected void onAction() {
				Parameter p = new Parameter("name", "type");
				MethodParameterFormBuilder parabuilder = new MethodParameterFormBuilder(p, window){

					@Override
					public void onOk() {
						parameters.addElement(new ParameterWrapper(p));
						checkEditAndCancelButtons();
					}
					
				};
				getForm().getCanvasWindow().addContentAndSwitchTo(parabuilder.getForm());
			}
			
		};
		
		this.addFormObject(addParameter);
		
		editParameter = new Button("Edit", 250,450, 50, 50){

			@Override
			protected void onAction() {
				Parameter p = parameters.getSelectedObject().getParameter();
				MethodParameterFormBuilder parabuilder = new MethodParameterFormBuilder(p, window){

					@Override
					public void onOk() {
						
					}
					
				};
				getForm().getCanvasWindow().addContentAndSwitchTo(parabuilder.getForm());
				
			}
			
		};
		
		this.addFormObject(editParameter);
		
		removeParameter = new Button("Remove", 350,450, 50, 50){

			@Override
			protected void onAction() {
				parameters.removeSelectedElement();
				checkEditAndCancelButtons();
			}
			
		};
		
		this.addFormObject(removeParameter);
		

		
		// OK and cancel ---------------------------------------------------------------
		OkButton ok = new OkButton(10, 500, 50, 50) {

			@Override
			protected void onOk() {
				method.setName(methName.getText());
				method.setType(methType.getText());
				if (group.getSelectedButton().equals(publicButton))
					method.setVisibility(new Public());
				if (group.getSelectedButton().equals(packageButton))
					method.setVisibility(new Package());
				if (group.getSelectedButton().equals(privateButton))
					method.setVisibility(new Private());
				
				method.setStatic(staticCheckbox.isChecked());

				getForm().close();
			}

		};

		ok.addCheckableConstraint(methName);
		ok.addCheckableConstraint(methType);

		this.addFormObject(ok);

		Button cancel = new Button("Cancel", 110, 500, 50, 50) {

			@Override
			protected void onAction() {
				getForm().close();
			}
		};

		this.addFormObject(cancel);

		// Initialize all objects with correct startinput.

		Visibility v = method.getVisibility();
		if(v instanceof Private)
			group.setSelectedButton(privateButton);
		else if(v instanceof Public)
			group.setSelectedButton(publicButton);
		else if(v instanceof Package)
			group.setSelectedButton(packageButton);	
		
		staticCheckbox.setChecked(method.isStatic());
		checkEditAndCancelButtons();
	}

	
	private void checkEditAndCancelButtons(){
		editParameter.setEnabled(parameters.getSelectedObject() != null);
		removeParameter.setEnabled(parameters.getSelectedObject() != null);
	}

}
