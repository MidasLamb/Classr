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
import logicalobjects.Attribute;
import visibilities.Visibility;

/**
 * Builds a Form for managing an attribute
 */
public class AttributeFormBuilder extends FormBuilder<FormWrapper> {
	private Attribute attribute;
	private MyCanvasWindow window;
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
	public AttributeFormBuilder(Attribute attribute, MyCanvasWindow window, boolean isNew) {
		this.attribute = attribute;
		this.window = window;
		this.isNew = isNew;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));
		RegexCheckedInputBox attrName = new RegexCheckedInputBox(getAttribute().getName(), "^[a-z][a-zA-Z0-9_]*", 10,
				10, 100, 16);
		this.addFormObject(attrName);
		this.addLabelToTopOfLastFormObject("Attribute name");

		RegexCheckedInputBox attrType = new RegexCheckedInputBox(getAttribute().getType(), "^[a-z][a-zA-Z0-9_]*", 10,
				100, 100, 16);
		this.addFormObject(attrType);
		this.addLabelToTopOfLastFormObject("Attribute type");

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
		RadioButton protectedButton = new DefaultRadioButton(group, 10, 400);
		this.addFormObject(protectedButton);
		this.addLabelToRightOfLostFormObject("Protected");

		// Static checkbox

		CheckBox staticCheckbox = new DefaultCheckBox(10, 450);

		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLostFormObject("Static");

		OkButton ok = new OkButton(10, 500, 50, 50) {

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

		Button cancel = new Button("Cancel", 110, 500, 50, 50) {

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
