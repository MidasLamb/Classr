package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import command.ChangeClassContentStaticCommand;
import command.ChangeClassContentTypeCommand;
import command.ChangeClassContentVisibilityCommand;
import command.ChangeLogicalObjectNameCommand;
import command.Controller;
import gui.form.base.Button;
import gui.form.base.CheckBox;
import gui.form.base.FormContainer;
import gui.form.base.InputBox;
import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.form.base.Label;
import gui.form.utility.DefaultCheckBox;
import gui.form.utility.DefaultRadioButton;
import gui.form.utility.FormBuilder;
import gui.form.utility.OkButton;
import gui.form.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.Attribute;
import visibilities.Visibility;

/**
 * Builds a Form for managing an attribute
 */
public class AttributeFormBuilder extends FormBuilder<FormWrapper> {
	private Attribute attribute;
	private FormContainer<FormWrapper> formContainer;
	private boolean isNew;
	private final Controller controller;

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
	public AttributeFormBuilder(Attribute attribute, FormContainer<FormWrapper> formContainer, Controller controller) {
		this.attribute = attribute;
		this.formContainer = formContainer;
		this.controller = controller;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(270, 180, this.formContainer));
		InputBox attrName = new InputBox(getAttribute().getName(), 150, 25, 100, 16) {

			@Override
			protected void onAction() {
				if (attribute.canHaveAsName(getText()) && !attribute.getName().equals(getText())) {
					controller.executeCommand(new ChangeLogicalObjectNameCommand(attribute, getText()));
				}

			}

			@Override
			public boolean isValidString(String string) {
				return attribute.canHaveAsName(getText());
			}

		};
		this.addFormObject(attrName);

		this.addLabelToTopOfLastFormObject("Attribute name");

		InputBox attrType = new InputBox(getAttribute().getType(), 150, 75, 100, 16) {

			@Override
			protected void onAction() {
				if (attribute.canHaveAsType(getText())  && !attribute.getType().equals(getText())) {
					controller.executeCommand(new ChangeClassContentTypeCommand(attribute, getText()));
				}

			}

			@Override
			public boolean isValidString(String string) {
				return attribute.canHaveAsType(getText());
			}

		};
		this.addFormObject(attrType);
		this.addLabelToTopOfLastFormObject("Attribute type");

		// Radio buttons for visibility.
		// ---------------------------------------------------------------
		this.addFormObject(new Label("Visibility", 10, 65));
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton publicButton = new RadioButton(group, 10, 90) {
			@Override
			protected void onAction() {
				if (attribute.canHaveAsVisibility(Visibility.PUBLIC))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(attribute, Visibility.PUBLIC));
			}
		};
		this.addFormObject(publicButton);
		this.addLabelToRightOfLastFormObject("Public");
		RadioButton privateButton = new RadioButton(group, 10, 110) {
			@Override
			protected void onAction() {
				if (attribute.canHaveAsVisibility(Visibility.PRIVATE))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(attribute, Visibility.PRIVATE));
			}
		};
		
		this.addFormObject(privateButton);
		this.addLabelToRightOfLastFormObject("Private");
		RadioButton packageButton = new RadioButton(group, 10, 130) {
			@Override
			protected void onAction() {
				if (attribute.canHaveAsVisibility(Visibility.PACKAGE))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(attribute, Visibility.PACKAGE));
			}
		};
		
		this.addFormObject(packageButton);
		this.addLabelToRightOfLastFormObject("Package");
		RadioButton protectedButton = new RadioButton(group, 10, 150) {
			@Override
			protected void onAction() {
				if (attribute.canHaveAsVisibility(Visibility.PROTECTED))
					controller.executeCommand(new ChangeClassContentVisibilityCommand(attribute, Visibility.PROTECTED));
			}
		};
		
		this.addFormObject(protectedButton);
		this.addLabelToRightOfLastFormObject("Protected");

		// Static checkbox
		this.addFormObject(new Label("Modifiers", 10, 3));
		CheckBox staticCheckbox = new CheckBox(10, 25){
			@Override
			protected void onAction() {
				if (attribute.canBeStatic(isChecked()))
					controller.executeCommand(new ChangeClassContentStaticCommand(attribute, isChecked()));
				
			}
		};

		this.addFormObject(staticCheckbox);
		this.addLabelToRightOfLastFormObject("Static");

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

		attribute.addUpdateListener(new UpdateListener() {

			@Override
			public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
				attrName.setText(attribute.getName());
				attrType.setText(attribute.getType());
				
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
		});
	}

	private Attribute getAttribute() {
		return attribute;
	}
}
