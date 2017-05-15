package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.FormContainer;
import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.form.base.Label;
import gui.form.utility.DefaultCheckBox;
import gui.form.utility.DefaultRadioButton;
import gui.form.utility.FormBuilder;
import gui.form.utility.OkButton;
import gui.form.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Attribute;
import visibilities.Visibility;

/**
 * Builds a Form for managing an attribute
 */
public class AttributeFormBuilder extends FormBuilder<FormWrapper> {
	private Attribute attribute;
	private FormContainer formContainer;
	private boolean isNew;

	/**
	 * Create a new AttributeFormBuilder.
	 * 
	 * @param attribute
	 *            Attribute for which a Form must be created
	 * @param window
	 *            MyCanvasWindow where the Form needs to be drawn
	 * @param isNew
	 *            indicates whether this is a newly created attribute
	 */
	public AttributeFormBuilder(Attribute attribute, FormContainer formContainer, boolean isNew) {
		this.attribute = attribute;
		this.formContainer = formContainer;
		this.isNew = isNew;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(270, 180, this.formContainer));
		RegexCheckedInputBox attrName = new RegexCheckedInputBox(getAttribute().getName(), "^[a-z][a-zA-Z0-9_]*", 10,
				26, 100, 16);
		this.addFormObject(attrName);
		this.addLabelToTopOfLastFormObject("Attribute name");

		RegexCheckedInputBox attrType = new RegexCheckedInputBox(getAttribute().getType(), "^[a-z][a-zA-Z0-9_]*", 150,
				26, 100, 16);
		this.addFormObject(attrType);
		this.addLabelToTopOfLastFormObject("Attribute type");

		// Radio buttons for visibility.
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
		this.addFormObject(new Label("Modifiers", 150, 65));
		CheckBox staticCheckbox = new DefaultCheckBox(150, 90);

		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLastFormObject("Static");

		OkButton ok = new OkButton(150, 120, 50, 50) {

			@Override
			protected void onOk() {
				getAttribute().setName(attrName.getText());
				getAttribute().setType(attrType.getText());
				if (group.getSelectedButton().equals(publicButton))
					getAttribute().setVisibility(Visibility.PUBLIC);
				else if (group.getSelectedButton().equals(packageButton))
					getAttribute().setVisibility(Visibility.PACKAGE);
				else if (group.getSelectedButton().equals(privateButton))
					getAttribute().setVisibility(Visibility.PRIVATE);
				else if (group.getSelectedButton().equals(protectedButton))
					getAttribute().setVisibility(Visibility.PROTECTED);

				getAttribute().setStatic(staticCheckbox.isChecked());

				getForm().close();
			}

		};

		ok.addCheckableConstraint(attrName);
		ok.addCheckableConstraint(attrType);

		this.addFormObject(ok);

		Button cancel = new Button("Cancel", 210, 120, 50, 50) {

			@Override
			protected void onAction() {
				if (isNew)
					attribute.delete();
				getForm().close();
			}
		};

		this.addFormObject(cancel);

		// Initialize all objects with correct startinput.
		Visibility v = getAttribute().getVisibility();
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
		staticCheckbox.setChecked(getAttribute().isStatic());
	}

	private Attribute getAttribute() {
		return attribute;
	}
}
