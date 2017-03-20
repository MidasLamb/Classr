package main;

import static main.Constants.CANVAS_TITLE;

import canvaswindow.MyCanvasWindow;

public class Main {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			new MyCanvasWindow(CANVAS_TITLE).show();
		});
	}

}
