package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.base.FormObject;
import gui.base.InputBox;
import gui.base.Label;
import gui.base.RadioButton;
import gui.base.RadioButtonGroup;
import guiToApplication.FormWrapper;
import objects.Attribute;

public class AttributeFormBuilder extends FormBuilder {
	private Attribute attribute;
	private MyCanvasWindow window;

	public AttributeFormBuilder(Attribute attribute, MyCanvasWindow window) {
		this.attribute = attribute;
		this.window = window;
	}

	@Override
	protected FormWrapper buildForm() {
		FormWrapper form = new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window);
		InputBox attrName = new InputBox(10, 10, 100, 16) {
			@Override
			public void onAction() {
				// TODO Should check conditions
			}
		};
		this.addFormObject(attrName);
		this.addLabelToTopOfLastFormObject("Attribute name");

		InputBox attrType = new InputBox(10, 100, 100, 16) {
			@Override
			public void onAction() {
				// TODO Should check conditions
			}
		};
		this.addFormObject(attrType);
		this.addLabelToTopOfLastFormObject("Attribute type");

		RadioButtonGroup group = new RadioButtonGroup();
		this.addFormObject(new RadioButton(group, 10, 250) {
			@Override
			public void onAction() {
			}
		});

		this.addLabelToRightOfLostFormObject("Public");
		this.addFormObject(new RadioButton(group, 10, 300) {
			@Override
			public void onAction() {
			}
		});
		this.addLabelToRightOfLostFormObject("Private");

		this.addFormObject(new RadioButton(group, 10, 350) {
			@Override
			public void onAction() {
			}
		});
		this.addLabelToRightOfLostFormObject("Package");
		return form;
	}

}
