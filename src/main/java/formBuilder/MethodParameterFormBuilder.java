package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.form.base.Button;
import gui.form.base.FormContainer;
import gui.form.utility.FormBuilder;
import gui.form.utility.OkButton;
import gui.form.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Parameter;

/**
 * Builds a Form for managing the parameter for a method
 */
public abstract class MethodParameterFormBuilder extends FormBuilder<FormWrapper> {
	private FormContainer formContainer;
	private Parameter parameter;

	/**
	 * Create a new MethodParameterFormBuilder.
	 * 
	 * @param parameter
	 *            Parameter for which the Form is to be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 */
	public MethodParameterFormBuilder(Parameter parameter, FormContainer formContainer) {
		this.formContainer = formContainer;
		this.parameter = parameter;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.formContainer));

		RegexCheckedInputBox parName = new RegexCheckedInputBox(parameter.getName(), "^[a-z][a-zA-Z0-9_]*", 10, 10, 100,
				16);
		this.addFormObject(parName);
		this.addLabelToTopOfLastFormObject("Parameter name");

		RegexCheckedInputBox parType = new RegexCheckedInputBox(parameter.getType(), "^[a-z][a-zA-Z0-9_]*", 10, 100,
				100, 16);
		this.addFormObject(parType);
		this.addLabelToTopOfLastFormObject("Parameter type");

		MethodParameterFormBuilder t = this;
		OkButton ok = new OkButton(10, 500, 50, 50) {

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

		Button cancel = new Button("Cancel", 110, 500, 50, 50) {

			@Override
			protected void onAction() {
				getForm().close();
				onCancel();
			}
		};

		this.addFormObject(cancel);
	}

	/**
	 * Method executed when the OK button is triggered
	 */
	public abstract void onOk();

	/**
	 * Method executed when the Cancel button is triggered
	 */
	public void onCancel() {
		// Default behavior is to do nothing
	}

}
