package gui;

import canvaswindow.FormWindow;
import gui.FormObject.LabelPosition;

public class Test {
	
	public static void main(String[] args) {
		Form form = new Form(100,200);
		form.addFormObject(new Label("Hello world", 10, 10));
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton radioButton = new RadioButton(group, 10, 250) {
			@Override
			public void onAction() {}
		};
		form.addFormObject(radioButton);
		form.addFormObject(radioButton.createLabel(LabelPosition.RIGHT, "Pieter-Jan"));
		
		form.addFormObject(new RadioButton(group, 10, 300) {
			@Override
			public void onAction() {}
		});
		form.addFormObject(new CheckBox(10, 350) {
			
			@Override
			public void onAction() {}
		});
		form.addFormObject(new CheckBox(10, 400) {
			
			@Override
			public void onAction() {}
		});
		Button button1 = new Button("Hello", 50, 50, 50, 50) {
			
			@Override
			void onAction() {
				System.out.println("geklikt");
				
			}
		};
		Button button2 = new Button("Enable/disable", 200, 200, 100, 100) {
			
			@Override
			void onAction() {
				button1.setEnabled(false);
			}
		};
		form.addFormObject(button1);
		form.addFormObject(button2);
		
		FormWindow window = new FormWindow("hello", form);
		java.awt.EventQueue.invokeLater(() -> {
			window.show();
		});

	}

}
