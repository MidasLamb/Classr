package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.ListBox;
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
import visibilities.Package;
import visibilities.Private;
import visibilities.Protected;
import visibilities.Public;
import visibilities.Visibility;

public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private MyCanvasWindow window;
	private boolean isNew;
	
	private Button editParameter;
	private Button removeParameter;
	private ListBox<ParameterWrapper> parameters;

	public MethodFormBuilder(Method method, MyCanvasWindow window, boolean isNew) {
		this.method = method;
		this.window = window;
		this.isNew = isNew;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));

		RegexCheckedInputBox methName = new RegexCheckedInputBox(getMethod().getName(), "^[a-z][a-zA-Z0-9_]*", 10, 10, 100, 16);
		this.addFormObject(methName);
		this.addLabelToTopOfLastFormObject("Method name");

		RegexCheckedInputBox methType = new RegexCheckedInputBox(getMethod().getType(), "^[a-z][a-zA-Z0-9_]*", 10, 60, 100, 16);

		this.addFormObject(methType);
		this.addLabelToTopOfLastFormObject("Method type");

		// Radio buttons for visibility. ---------------------------------------------------------------
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton publicButton = new DefaultRadioButton(group, 10, 110);
		this.addFormObject(publicButton);
		this.addLabelToRightOfLostFormObject("Public");
		RadioButton privateButton = new DefaultRadioButton(group, 10, 160);
		this.addFormObject(privateButton);
		this.addLabelToRightOfLostFormObject("Private");
		RadioButton packageButton = new DefaultRadioButton(group, 10, 210);
		this.addFormObject(packageButton);
		this.addLabelToRightOfLostFormObject("Package");
		RadioButton protectedButton = new DefaultRadioButton(group, 10, 260);
		this.addFormObject(protectedButton);
		this.addLabelToRightOfLostFormObject("Protected");
		// Static checkbox ---------------------------------------------------------------
		CheckBox staticCheckbox = new DefaultCheckBox(10, 310);
		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLostFormObject("Static");
		
		//Abstract checkbox ----------------------------------------------------------
		CheckBox abstractCheckbox = new DefaultCheckBox(10, 360);
		this.addFormObject(abstractCheckbox);
		this.addLabelToRightOfLostFormObject("Abstract");
		
		
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
				getMethod().setName(methName.getText());
				getMethod().setType(methType.getText());
				if (group.getSelectedButton().equals(publicButton))
					getMethod().setVisibility(new Public());
				else if (group.getSelectedButton().equals(packageButton))
					getMethod().setVisibility(new Package());
				else if (group.getSelectedButton().equals(privateButton))
					getMethod().setVisibility(new Private());
				else if (group.getSelectedButton().equals(protectedButton))
					getMethod().setVisibility(new Protected());
				
				getMethod().setStatic(staticCheckbox.isChecked());
				getMethod().setAbstract(abstractCheckbox.isChecked());

				getForm().close();
			}

		};

		ok.addCheckableConstraint(methName);
		ok.addCheckableConstraint(methType);

		this.addFormObject(ok);

		Button cancel = new Button("Cancel", 110, 500, 50, 50) {

			@Override
			protected void onAction() {
				if (isNew)
					method.delete();
				getForm().close();
			}
		};

		this.addFormObject(cancel);

		// Initialize all objects with correct startinput.

		Visibility v = getMethod().getVisibility();
		if(v instanceof Private)
			group.setSelectedButton(privateButton);
		else if(v instanceof Public)
			group.setSelectedButton(publicButton);
		else if(v instanceof Package)
			group.setSelectedButton(packageButton);	
		else if(v instanceof Protected)
			group.setSelectedButton(protectedButton);
		
		staticCheckbox.setChecked(getMethod().isStatic());
		abstractCheckbox.setChecked(getMethod().isAbstract());
		checkEditAndCancelButtons();
	}

	
	private void checkEditAndCancelButtons(){
		editParameter.setEnabled(parameters.getSelectedObject() != null);
		removeParameter.setEnabled(parameters.getSelectedObject() != null);
	}
	
	private Method getMethod() {
		return method;
	}

}
