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
import logicalobjects.Attribute;
import logicalobjects.Method;
import logicalobjects.Visibility;

public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private MyCanvasWindow window;

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

		// Radio buttons for visibility.
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

		// Static checkbox
		CheckBox staticCheckbox = new DefaultCheckBox(10, 400);

		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLostFormObject("Static");

		
		// OK and cancel
		OkButton ok = new OkButton(10, 500, 50, 50) {

			@Override
			protected void onOk() {
				method.setName(methName.getText());
				method.setType(methType.getText());
				if (group.getSelectedButton().equals(publicButton))
					method.setVisibility(Visibility.PUBLIC);
				if (group.getSelectedButton().equals(packageButton))
					method.setVisibility(Visibility.PACKAGE);
				if (group.getSelectedButton().equals(privateButton))
					method.setVisibility(Visibility.PRIVATE);
				
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
		switch (v) {
		case PRIVATE:
			group.setSelectedButton(privateButton);
			break;
		case PUBLIC:
			group.setSelectedButton(publicButton);
			break;
		case PACKAGE:
			group.setSelectedButton(packageButton);
			break;
		}
		
		staticCheckbox.setChecked(method.isStatic());

	}

}
