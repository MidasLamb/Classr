package formBuilder;

import gui.Form;
import gui.InputBox;
import gui.RadioButton;
import gui.RadioButtonGroup;
import objects.Association;
import static main.Constants.*;

public class AttributeFormBuilder {
	private Association association;
	private Form form;
	public AttributeFormBuilder(Association association){
		this.association = association;
		form = new Form(CONTAINER_WIDTH,CONTAINER_HEIGHT);
		InputBox attrName = new InputBox(10,10, 100,16){
			@Override
			public void onAction() {
				//TODO Should check conditions
			}
		};
		
		InputBox attrType = new InputBox(10,100, 100,16){
			@Override
			public void onAction() {
				//TODO Should check conditions
			}
		};
		
		RadioButtonGroup group = new RadioButtonGroup();
		form.addFormObject(new RadioButton(group, 10, 250) {
			@Override
			public void onAction() {}
		});
		form.addFormObject(new RadioButton(group, 10, 300) {
			@Override
			public void onAction() {}
		});
		
	}
}
