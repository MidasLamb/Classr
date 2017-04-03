package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.base.Button;
import gui.base.FormObject;
import gui.base.InputBox;
import gui.base.Label;
import gui.base.RadioButton;
import gui.base.RadioButtonGroup;
import gui.utility.DefaultRadioButton;
import gui.utility.FormBuilder;
import gui.utility.OkButton;
import gui.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Attribute;
import logicalobjects.Visibility;

public class AttributeFormBuilder extends FormBuilder<FormWrapper> {
	private Attribute attribute;
	private MyCanvasWindow window;

	public AttributeFormBuilder(Attribute attribute, MyCanvasWindow window) {
		this.attribute = attribute;
		this.window = window;
		
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));
		RegexCheckedInputBox attrName = new RegexCheckedInputBox(attribute.getName(), "\\p{Lower}.*", 10, 10, 100, 16);
		this.addFormObject(attrName);
		this.addLabelToTopOfLastFormObject("Attribute name");

		RegexCheckedInputBox attrType = new RegexCheckedInputBox(attribute.getType(), "\\p{Lower}.*", 10, 100, 100, 16);
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
		
		OkButton ok = new OkButton(10, 500, 50, 50){

			@Override
			protected void onOk() {
				attribute.setName(attrName.getText());
				attribute.setType(attrType.getText());
				if (group.getSelectedButton().equals(publicButton))
					attribute.setVisibility(Visibility.PUBLIC);
				if (group.getSelectedButton().equals(packageButton))
					attribute.setVisibility(Visibility.PACKAGE);
				if (group.getSelectedButton().equals(privateButton))
					attribute.setVisibility(Visibility.PRIVATE);
				
				getForm().close();
			}
			
		};
		
		ok.addCheckableConstraint(attrName);
		ok.addCheckableConstraint(attrType);
		
		this.addFormObject(ok);
		
		Button cancel = new Button("Cancel", 110,500,50,50){

			@Override
			protected void onAction() {
				getForm().close();
			}
		};
		
		this.addFormObject(cancel);
		
		
		// Initialize all objects with correct startinput.
		
		Visibility v = attribute.getVisibility();
		switch (v){
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
		
		
	}

}
