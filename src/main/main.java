package main;

import static main.Constants.CANVAS_TITLE;

import canvaswindow.MyCanvasWindow;

public class main {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			          new MyCanvasWindow(CANVAS_TITLE).show();
			      });
	}

}
