package canvaswindow;

import static main.Constants.CANVAS_TITLE;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

public class VisualTests {

	@Test
	public void barTest() throws IOException {
		String name = "barTest";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test 
	public void createClassTest() throws IOException { 
		 String name = "createClassTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}	
	
}
