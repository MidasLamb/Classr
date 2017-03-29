package gui;

import inputHandlers.clicks.MouseClick;

public class Test {

	public void main(){
		CheckBox box = new CheckBox(1,1) {
			
			@Override
			void onClick(MouseClick click) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAction() {
				// TODO Auto-generated method stub
				
			}
		};
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group,10,10) {
			
			@Override
			public void onAction() {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
