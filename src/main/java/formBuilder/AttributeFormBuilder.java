package formBuilder;

import gui.Form;
import gui.InputBox;
import gui.RadioButton;
import gui.RadioButtonGroup;
import guiToApplication.FormWrapper;
import objects.Association;
import objects.Attribute;

import static main.Constants.*;

public class AttributeFormBuilder {
	private Attribute attribute;
	private FormWrapper form;
	public AttributeFormBuilder(Attribute attribute){
		this.attribute = attribute;
		form = new FormWrapper(CONTAINER_WIDTH,CONTAINER_HEIGHT);
//		InputBox attrName = new InputBox(10,10, 100,16){
//			@Override
//			public void onAction() {
//				//TODO Should check conditions
//			}
//		};
//		
//		form.addFormObject(attrName);
//		
//		InputBox attrType = new InputBox(10,100, 100,16){
//			@Override
//			public void onAction() {
//				//TODO Should check conditions
//			}
//		};
//		
//		form.addFormObject(attrType);
		
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
	
	public FormWrapper getForm(){
		return this.form;
	}
}
