package canvaswindow;

import static main.Constants.CANVAS_TITLE;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.junit.Test;

public class VisualTests {

	@Test
	public void newClassTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/newClass/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("newClass"),  actual));
	}
	
	@Test
	public void createTwoClassesTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/createTwoClasses/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("createTwoClasses"),  actual));
	}
	
	@Test
	public void createAndSelectClassTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/createAndSelectClass/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("createAndSelectClass"),  actual));
	}
	
	@Test
	public void changeClassNameTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/changeClassName/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();;
		assertTrue(imagesEqual(getReferenceImage("changeClassName"),  actual));
	}
	
	@Test
	public void addAttributeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/addAttribute/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("addAttribute"),  actual));
	}
	
	@Test
	public void addMethodeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/addMethode/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("addMethode"),  actual));
	}
	
	@Test
	public void addAssociationTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/addAssociation/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("addAssociation"),  actual));
	}
	
	@Test
	public void deleteClassTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteClass/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteClass"),  actual));
	}
	
	@Test
	public void selectAttrTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/selectAttr/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("selectAttr"),  actual));
	}
	
	@Test
	public void selectMethodeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/selectMethode/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("selectMethode"),  actual));
	}
	
	@Test
	public void deleteClassWithAssAndMethTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteClassWithAssAndMeth/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteClassWithAssAndMeth"),  actual));
	}
	
	@Test
	public void deleteAssociationTest1() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteAssociation1/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteAssociation1"),  actual));
	}
	
	@Test
	public void deleteAssociationTest2() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteAssociation2/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteAssociation2"),  actual));
	}
	
	@Test
	public void deleteAssociationTest3() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteAssociation3/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteAssociation3"),  actual));
	}
	
	@Test
	public void deleteAssociationTest4() throws IOException {
		//Drie klassen associatie van 1 -> 2 van 2 ->3 en dan 2 verwijderen
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteAssociation4/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteAssociation4"),  actual));
	}
	
	@Test
	public void deleteAttributeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/deleteAttribute/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("deleteAttribute"),  actual));
	}	
	
	@Test
	public void doubleClickTest() throws IOException {
		//Twee keer klikken op dezelfde plaats in container
		// zou geen twee klassen mogen maken
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		MyCanvasWindow.replayRecording("recordings/doubleClick/recording", canvasWindow);
		BufferedImage actual = canvasWindow.captureImage();
		assertTrue(imagesEqual(getReferenceImage("doubleClick"),  actual));
	}
	
	
	
	static boolean imagesEqual(BufferedImage reference, BufferedImage actual) {
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
	
	static BufferedImage getReferenceImage(String testName) throws IOException{
		String pattern = ".*recording.image([0-9]*).png";
		Pattern r = Pattern.compile(pattern);
		Stream<Path> directory = Files.walk(Paths.get("recordings/" + testName));
		OptionalInt max = directory.mapToInt(file ->{
			Matcher m = r.matcher(file.toString());
			if (m.find())
				return Integer.parseInt(m.group(1));
			return -1;
		}).max();
		directory.close();
		String referenceDirectory =  "recordings/" + testName + "/recording.image" + Integer.toString(max.getAsInt())+ ".png";
		return ImageIO.read(new FileInputStream(referenceDirectory));
	}

}
