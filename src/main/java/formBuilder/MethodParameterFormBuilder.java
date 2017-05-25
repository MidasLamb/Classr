package formBuilder;

import command.ChangeParameterNameCommand;
import command.ChangeParameterTypeCommand;
import command.Controller;
import gui.form.base.FormContainer;
import gui.form.base.InputBox;
import gui.form.utility.FormBuilder;
import guiToApplication.FormWrapper;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.Parameter;

/**
 * Builds a Form for managing the parameter for a method
 */
public class MethodParameterFormBuilder extends FormBuilder<FormWrapper> {
	private FormContainer<?> formContainer;
	private Parameter parameter;
	private final Controller controller;

	/**
	 * Create a new MethodParameterFormBuilder.
	 * 
	 * @param parameter
	 *            Parameter for which the Form is to be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 */
	public MethodParameterFormBuilder(Parameter parameter, FormContainer<?> formContainer, Controller controller) {
		this.formContainer = formContainer;
		this.parameter = parameter;
		this.controller = controller;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(150, 100, this.formContainer));

		InputBox parName = new InputBox(parameter.getName(), 10, 26, 100, 16) {
			@Override
			protected void onAction() {
				if (parameter.canHaveAsName(getText()) && !parameter.getName().equals(getText())) {
					controller.executeCommand(new ChangeParameterNameCommand(parameter, getText()));
				}
			}

			@Override
			public boolean isValidString(String string) {
				return parameter.canHaveAsName(string);
			}

		};
		this.addFormObject(parName);
		this.addLabelToTopOfLastFormObject("Parameter name");

		InputBox parType = new InputBox(parameter.getType(), 10, 76, 100, 16) {

			@Override
			protected void onAction() {
				if (parameter.canHaveAsType(getText()) && !parameter.getType().equals(getText())) {
					controller.executeCommand(new ChangeParameterTypeCommand(parameter, getText()));
				}
			}

			@Override
			public boolean isValidString(String string) {
				return parameter.canHaveAsType(string);
			}

		};
		this.addFormObject(parType);
		this.addLabelToTopOfLastFormObject("Parameter type");

		parameter.addUpdateListener(new UpdateListener() {

			@Override
			public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
				parName.setText(parameter.getName());
				parType.setText(parameter.getType());
			}
		});
	}

}
