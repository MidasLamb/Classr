package main;

import canvaswindow.MyCanvasWindow;

public class main {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			          new MyCanvasWindow("My Canvas Window").show();
			      });
	}

}
