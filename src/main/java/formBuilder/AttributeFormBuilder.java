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
import visibilities.Package;
import visibilities.Private;
import visibilities.Protected;
import visibilities.Public;
import visibilities.Visibility;

public class AttributeFormBuilder extends FormBuilder<FormWrapper> {
	private Attribute attribute;
	private MyCanvasWindow window;
	private boolean isNew;

	public AttributeFormBuilder(Attribute attribute, MyCanvasWindow window, boolean isNew) {
		this.attribute = attribute;
		this.window = window;
		this.isNew = isNew;
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));
		RegexCheckedInputBox attrName = new RegexCheckedInputBox(getAttribute().getName(), "^[a-z][a-zA-Z0-9_]*", 10, 10, 100, 16);
		this.addFormObject(attrName);
		this.addLabelToTopOfLastFormObject("Attribute name");

		RegexCheckedInputBox attrType = new RegexCheckedInputBox(getAttribute().getType(), "^[a-z][a-zA-Z0-9_]*", 10, 100, 100, 16);
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
		
		OkButton ok = new OkButton(10, 500, 50, 50){

			@Override
			protected void onOk() {
				getAttribute().setName(attrName.getText());
				getAttribute().setType(attrType.getText());
				if (group.getSelectedButton().equals(publicButton))
					getAttribute().setVisibility(new Public());
				else if (group.getSelectedButton().equals(packageButton))
					getAttribute().setVisibility(new Package());
				else if (group.getSelectedButton().equals(privateButton))
					getAttribute().setVisibility(new Private());
				else if (group.getSelectedButton().equals(protectedButton))
					getAttribute().setVisibility(new Protected());
				
				getAttribute().setStatic(staticCheckbox.isChecked());
				
				getForm().close();
			}
			
		};
		
		ok.addCheckableConstraint(attrName);
		ok.addCheckableConstraint(attrType);
		
		this.addFormObject(ok);
		
		Button cancel = new Button("Cancel", 110,500,50,50){

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
		if(v instanceof Private)
			group.setSelectedButton(privateButton);
		else if(v instanceof Public)
			group.setSelectedButton(publicButton);
		else if(v instanceof Package)
			group.setSelectedButton(packageButton);	
		else if(v instanceof Protected)
			group.setSelectedButton(protectedButton);
		staticCheckbox.setChecked(getAttribute().isStatic());
	}

	private Attribute getAttribute() {
		return attribute;
	}
}
