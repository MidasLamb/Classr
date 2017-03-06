package canvaswindow;

import static main.Constants.CANVAS_TITLE;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class containerTests {

	@Test
	public void createClassTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/newClass/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		BufferedImage reference = ImageIO.read(new FileInputStream("recordings/newClass/recording.image10.png"));
		assertTrue(imagesEqual(actual,  reference));
	}
	
	@Test
	public void createTwoClassesTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/createTwoClasses/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		BufferedImage reference = ImageIO.read(new FileInputStream("recordings/createTwoClasses/recording.image152.png"));
		assertTrue(imagesEqual(actual,  reference));
	}
	
	@Test
	public void createAndSelectClass() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/createAndSelectClass/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		BufferedImage reference = ImageIO.read(new FileInputStream("recordings/createAndSelectClass/recording.image20.png"));
		assertTrue(imagesEqual(actual,  reference));
	}
	
	@Test
	public void changeClassNameTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/changeClassName/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		BufferedImage reference = ImageIO.read(new FileInputStream("recordings/changeClassName/recording.image127.png"));
		assertTrue(imagesEqual(actual,  reference));
	}
	
	@Test
	public void addAttributeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/addAttribute/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		BufferedImage reference = ImageIO.read(new FileInputStream("recordings/addAttribute/recording.image122.png"));
		assertTrue(imagesEqual(actual,  reference));
	}
	
	private static boolean imagesEqual(BufferedImage reference, BufferedImage actual) {
		try{
			for (int x = 0; x < reference.getWidth(); x++) {
	            for (int y = 0; y < reference.getHeight(); y++) {
	                if (reference.getRGB(x, y) != actual.getRGB(x, y)){
	                	return false;
	                }
	            }
	        }
		    return true;
		} catch (IndexOutOfBoundsException e){
			return false;
		}
	}

}
