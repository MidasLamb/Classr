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
		String testName = "methodParameterTest1";
		prepareDirectory(testName);
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		canvasWindow.recordSession("recordings/" + testName + "/recording");
		java.awt.EventQueue.invokeLater(() -> {
			canvasWindow.show();
		});
		sysoutTest(testName);
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
	
	private static void sysoutTest(String name){
		StringBuilder builder = new StringBuilder();
		builder.append("@Test \n");
		builder.append("public void " + name + "() throws IOException { \n");
		builder.append("\t String name = \"" + name + "\"; \n");
		builder.append("\t MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); \n");
		builder.append("\t assertTrue(MyCanvasWindow.replayRecording(\"recordings/\"+name+\"/recording\", canvasWindow)); \n");
		builder.append("}");
		System.out.println(builder.toString());
	}

}
