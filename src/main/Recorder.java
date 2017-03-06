package main;

import static main.Constants.CANVAS_TITLE;

import canvaswindow.MyCanvasWindow;

public class Recorder {
	
	public static void main(String[] args) {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		canvasWindow.recordSession("recordings/addAttribute/recording");
		java.awt.EventQueue.invokeLater(() -> {
	         canvasWindow.show();
	      });
	}

}
