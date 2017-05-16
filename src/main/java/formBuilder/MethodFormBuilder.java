package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import java.util.ArrayList;
import java.util.Collection;

import canvaswindow.MyCanvasWindow;
import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.FormContainer;
import gui.form.base.Label;
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
import visibilities.Visibility;

/**
 * Builds a form managing a method
 */
public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private FormContainer<FormWrapper> container;
	private boolean isNew;

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
	public MethodFormBuilder(Method method, FormContainer<FormWrapper> container, boolean isNew) {
		this.method = method;
		this.container = container;
		this.isNew = isNew;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(330, 330, this.container));

		RegexCheckedInputBox methName = new RegexCheckedInputBox(getMethod().getName(), "^[a-z][a-zA-Z0-9_]*", 10, 26,
				100, 16);
		this.addFormObject(methName);
		this.addLabelToTopOfLastFormObject("Method name");

		RegexCheckedInputBox methType = new RegexCheckedInputBox(getMethod().getType(), "^[a-z][a-zA-Z0-9_]*", 150, 26,
				100, 16);

		this.addFormObject(methType);
		this.addLabelToTopOfLastFormObject("Method type");

		// Radio buttons for visibility.
		// ---------------------------------------------------------------
		this.addFormObject(new Label("Visibility", 10, 65));
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton publicButton = new DefaultRadioButton(group, 10, 90);
		this.addFormObject(publicButton);
		this.addLabelToRightOfLastFormObject("Public");
		RadioButton privateButton = new DefaultRadioButton(group, 10, 110);
		this.addFormObject(privateButton);
		this.addLabelToRightOfLastFormObject("Private");
		RadioButton packageButton = new DefaultRadioButton(group, 10, 130);
		this.addFormObject(packageButton);
		this.addLabelToRightOfLastFormObject("Package");
		RadioButton protectedButton = new DefaultRadioButton(group, 10, 150);
		this.addFormObject(protectedButton);
		this.addLabelToRightOfLastFormObject("Protected");
		// Static checkbox
		// ---------------------------------------------------------------
		this.addFormObject(new Label("Modifiers", 150, 65));
		CheckBox staticCheckbox = new DefaultCheckBox(150, 90);
		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLastFormObject("Static");

		// Abstract checkbox
		// ----------------------------------------------------------
		CheckBox abstractCheckbox = new DefaultCheckBox(150, 110);
		this.addFormObject(abstractCheckbox);
		this.addLabelToRightOfLastFormObject("Abstract");

		// Parameters
		// ---------------------------------------------------------------
		parameters = new ListBox<ParameterWrapper>(10, 200, 100, 100) {

			@Override
			protected void onAction() {
				checkEditAndCancelButtons();
			}

		};

		this.addFormObject(parameters);
		this.addLabelToTopOfLastFormObject("Parameters");

		Button addParameter = new Button("Add", 150, 200, 50, 50) {

			@Override
			protected void onAction() {
				Parameter p = new Parameter("name", "type");
				MethodParameterFormBuilder parabuilder = new MethodParameterFormBuilder(p, container) {

					@Override
					public void onOk() {
						parameters.addElement(new ParameterWrapper(p));
						checkEditAndCancelButtons();
					}

				};
				getContainer().switchTo(parabuilder.getForm());
			}

		};

		this.addFormObject(addParameter);

		editParameter = new Button("Edit", 210, 200, 50, 50) {

			@Override
			protected void onAction() {
				Parameter p = parameters.getSelectedObject().getParameter();
				MethodParameterFormBuilder parabuilder = new MethodParameterFormBuilder(p, container) {

					@Override
					public void onOk() {

					}

				};
				getContainer().switchTo(parabuilder.getForm());

			}

		};

		this.addFormObject(editParameter);

		removeParameter = new Button("Remove", 270, 200, 50, 50) {

			@Override
			protected void onAction() {
				parameters.removeSelectedElement();
				checkEditAndCancelButtons();
			}

		};

		this.addFormObject(removeParameter);

		// OK and cancel
		// ---------------------------------------------------------------
		OkButton ok = new OkButton(210, 270, 50, 50) {

			@Override
			protected void onOk() {
				getMethod().setName(methName.getText());
				getMethod().setType(methType.getText());
				if (group.getSelectedButton().equals(publicButton))
					getMethod().setVisibility(Visibility.PUBLIC);
				else if (group.getSelectedButton().equals(packageButton))
					getMethod().setVisibility(Visibility.PACKAGE);
				else if (group.getSelectedButton().equals(privateButton))
					getMethod().setVisibility(Visibility.PRIVATE);
				else if (group.getSelectedButton().equals(protectedButton))
					getMethod().setVisibility(Visibility.PROTECTED);

				getMethod().setStatic(staticCheckbox.isChecked());
				getMethod().setAbstract(abstractCheckbox.isChecked());

				Collection<Parameter> c = new ArrayList<Parameter>(getMethod().getParameters());
				for (Parameter p : c) {
					getMethod().removeParameter(p);
				}

				for (ParameterWrapper p : parameters.getElements())
					getMethod().addParameter(p.getParameter());

				getForm().close();
			}

		};

		ok.addCheckableConstraint(methName);
		ok.addCheckableConstraint(methType);

		this.addFormObject(ok);

		Button cancel = new Button("Cancel", 270, 270, 50, 50) {

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
	}

	private void checkEditAndCancelButtons() {
		editParameter.setEnabled(parameters.getSelectedObject() != null);
		removeParameter.setEnabled(parameters.getSelectedObject() != null);
	}

	private Method getMethod() {
		return method;
	}

	private final FormContainer<FormWrapper> getContainer() {
		return container;
	}

	private final void setContainer(FormContainer<FormWrapper> container) {
		this.container = container;
	}

}
