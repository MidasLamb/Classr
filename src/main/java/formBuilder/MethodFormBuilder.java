package formBuilder;

import command.AddParameterCommand;
import command.ChangeClassContentStaticCommand;
import command.ChangeClassContentTypeCommand;
import command.ChangeClassContentVisibilityCommand;
import command.ChangeLogicalObjectNameCommand;
import command.ChangeMethodAbstractCommand;
import command.Controller;
import command.DeleteParameterCommand;
import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.FormContainer;
import gui.form.base.InputBox;
import gui.form.base.Label;
import gui.form.base.ListBox;
import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.form.utility.FormBuilder;
import guiToApplication.FormWrapper;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visibilities.Visibility;
import visualobjects.Backend;

/**
 * Builds a form managing a method
 */
public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private FormContainer<FormWrapper> container;
	private final Controller controller;

	private Button editParameter;
	private Button removeParameter;
	private ListBox<ParameterWrapper> parameters;

	/**
	 * Create a new MethodFormBuilder.
	 * 
	 * @param method
	 *            Method for which a form must be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 * @param isNew
	 *            indicates whether this is a newly created method
	 */
	public MethodFormBuilder(Method method, FormContainer<FormWrapper> container, Controller controller) {
		this.method = method;
		this.container = container;
		this.controller = controller;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(200, 330, this.container));

		InputBox methName = new InputBox(getMethod().getName(), 150, 26, 100, 16) {
			@Override
			protected void onAction() {
				if (method.canHaveAsName(getText()) && !method.getName().equals(getText())) {
					controller.executeCommand(new ChangeLogicalObjectNameCommand(method, getText()));
				}

			}

			@Override
			public boolean isValidString(String string) {
				return method.canHaveAsName(string);
			}

		};
		this.addFormObject(methName);
		this.addLabelToTopOfLastFormObject("Method name");

		InputBox methType = new InputBox(getMethod().getType(), 150, 70, 100, 16) {

			@Override
			protected void onAction() {
				if (method.canHaveAsType(getText()) && !method.getType().equals(getText())) {
					controller.executeCommand(new ChangeClassContentTypeCommand(method, getText()));
				}

			}

			@Override
			public boolean isValidString(String string) {
				return method.canHaveAsType(string);
			}

		};

		this.addFormObject(methType);
		this.addLabelToTopOfLastFormObject("Method type");

		// Radio buttons for visibility.
		// ---------------------------------------------------------------
		this.addFormObject(new Label("Visibility", 10, 65));
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton publicButton = new RadioButton(group, 10, 90) {
			@Override
			protected void onAction() {
				if (method.canHaveAsVisibility(Visibility.PUBLIC))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(method, Visibility.PUBLIC));
			}
		};
		this.addFormObject(publicButton);
		this.addLabelToRightOfLastFormObject("Public");
		RadioButton privateButton = new RadioButton(group, 10, 110) {
			@Override
			protected void onAction() {
				if (method.canHaveAsVisibility(Visibility.PRIVATE))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(method, Visibility.PRIVATE));
			}
		};
		;
		this.addFormObject(privateButton);
		this.addLabelToRightOfLastFormObject("Private");
		RadioButton packageButton = new RadioButton(group, 10, 130) {
			@Override
			protected void onAction() {
				if (method.canHaveAsVisibility(Visibility.PACKAGE))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(method, Visibility.PACKAGE));
			}
		};
		;
		this.addFormObject(packageButton);
		this.addLabelToRightOfLastFormObject("Package");
		RadioButton protectedButton = new RadioButton(group, 10, 150) {
			@Override
			protected void onAction() {
				if (method.canHaveAsVisibility(Visibility.PROTECTED))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(method, Visibility.PROTECTED));
			}
		};
		;
		this.addFormObject(protectedButton);
		this.addLabelToRightOfLastFormObject("Protected");
		// Static checkbox
		// ---------------------------------------------------------------
		this.addFormObject(new Label("Modifiers", 10, 5));
		CheckBox staticCheckbox = new CheckBox(10, 26) {
			@Override
			protected void onAction() {
				if (method.canBeStatic(isChecked())) {
					controller.executeCommand(new ChangeClassContentStaticCommand(method, isChecked()));
				} else {
					setChecked(false);
				}
			}
		};
		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLastFormObject("Static");

		// Abstract checkbox
		// ----------------------------------------------------------
		CheckBox abstractCheckbox = new CheckBox(10, 46) {
			@Override
			protected void onAction() {
				if (method.canBeAbstract(isChecked())) {
					controller.executeCommand(new ChangeMethodAbstractCommand(method, isChecked()));
				} else {
					setChecked(false);
				}
			}
		};
		this.addFormObject(abstractCheckbox);
		this.addLabelToRightOfLastFormObject("Abstract");

		// Parameters
		// ---------------------------------------------------------------
		
		Button addParameter = new Button("Add", 10, 200, 50, 50) {

			@Override
			protected void onAction() {
				Parameter p = new Parameter("name", "type");
				controller.executeCommand(new AddParameterCommand(method, p));
				checkEditAndCancelButtons();

			}

		};

		this.addFormObject(addParameter);
		this.addLabelToTopOfLastFormObject("Parameters");

		editParameter = new Button("Edit", 70, 200, 50, 50) {

			@Override
			protected void onAction() {
				Parameter p = parameters.getSelectedObject().getParameter();
				Backend.createForm(p);
			}

		};

		this.addFormObject(editParameter);

		removeParameter = new Button("Remove", 130, 200, 50, 50) {

			@Override
			protected void onAction() {
				controller.executeCommand(
						new DeleteParameterCommand(method, parameters.getSelectedObject().getParameter()));
				checkEditAndCancelButtons();
			}

		};

		this.addFormObject(removeParameter);

		parameters = new ListBox<ParameterWrapper>(10, 260, 100, 100) {

			@Override
			protected void onAction() {
				checkEditAndCancelButtons();
			}

		};

		this.addFormObject(parameters);

		// Initialize all objects with correct startinput.

		Visibility v = getMethod().getVisibility();
		switch (v) {
		case PUBLIC:
			group.setSelectedButton(publicButton);
			break;
		case PROTECTED:
			group.setSelectedButton(protectedButton);
			break;
		case PACKAGE:
			group.setSelectedButton(packageButton);
			break;
		case PRIVATE:
			group.setSelectedButton(privateButton);
			break;
		default:
			throw new AssertionError("Visibility must be one of the following: public, protected, package or private.");
		}

		for (Parameter p : getMethod().getParameters())
			parameters.addElement(new ParameterWrapper(p));

		staticCheckbox.setChecked(getMethod().isStatic());
		abstractCheckbox.setChecked(getMethod().isAbstract());
		checkEditAndCancelButtons();

		method.addUpdateListener(new UpdateListener() {

			@Override
			public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
				methName.setText(method.getName());
				methType.setText(method.getType());
				for (ParameterWrapper pw : parameters.getElements())
					parameters.removeElement(pw);
				for (Parameter p : getMethod().getParameters()) {
					ParameterWrapper pw = new ParameterWrapper(p);
					if (!parameters.contains(pw))
						parameters.addElement(pw);
				}

				if (!parameters.getElements().contains(parameters.getSelectedObject())) {
					parameters.unselect();
				}

				Visibility v = getMethod().getVisibility();
				switch (v) {
				case PUBLIC:
					group.setSelectedButton(publicButton);
					break;
				case PROTECTED:
					group.setSelectedButton(protectedButton);
					break;
				case PACKAGE:
					group.setSelectedButton(packageButton);
					break;
				case PRIVATE:
					group.setSelectedButton(privateButton);
					break;
				default:
					throw new AssertionError(
							"Visibility must be one of the following: public, protected, package or private.");
				}

				staticCheckbox.setChecked(getMethod().isStatic());
				abstractCheckbox.setChecked(getMethod().isAbstract());
				checkEditAndCancelButtons();

			}
		});
	}

	/**
	 * Updates the status of the edit and cancel buttons
	 */
	private void checkEditAndCancelButtons() {
		editParameter.setEnabled(parameters.getSelectedObject() != null);
		removeParameter.setEnabled(parameters.getSelectedObject() != null);
	}

	/**
	 * Returns the Method for which the form needs to be created
	 * @return the Method for which the form needs to be created
	 */
	private Method getMethod() {
		return method;
	}

}
