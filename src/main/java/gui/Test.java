package gui;

import canvaswindow.FormWindow;

public class Test {
	
	public static void main(String[] args) {
		Form form = new Form(2000, 200);
		form.addFormObject(new Label("Hello world", 10, 10));
		form.addFormObject(new Button("hello", 20, 20, 50, 50) {
			
			@Override
			void onAction() {
				System.out.println("geklikt");
				
			}
		});
		
		FormWindow window = new FormWindow("hello", form);
		java.awt.EventQueue.invokeLater(() -> {
			window.show();
		});

	}

}
