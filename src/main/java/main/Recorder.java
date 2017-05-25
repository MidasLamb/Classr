package main;

import static main.Constants.CANVAS_TITLE;

import java.io.File;

import canvaswindow.MyCanvasWindow;

/**
 * A class to records steps and images for testcases.
 *
 */
public class Recorder {

	public static void main(String[] args) {
		String testName = "barTest";
		prepareDirectory(testName);
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		canvasWindow.recordSession("recordings/" + testName + "/recording");
		java.awt.EventQueue.invokeLater(() -> {
			canvasWindow.show();
		});
	}

	private static void prepareDirectory(String testName) {
		String directoryPath = "recordings/" + testName;
		removeDirectory(directoryPath);
		new File(directoryPath).mkdir();
	}

	private static void removeDirectory(String path) {
		try {
			File directory = new File(path);
			File[] content = directory.listFiles();
			for (File file : content) {
				file.delete();
			}
			directory.delete();
		} catch (NullPointerException e) {
			return;
		}
	}

}
