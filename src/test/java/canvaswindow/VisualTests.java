package canvaswindow;

import static main.Constants.CANVAS_TITLE;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
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



	static BufferedImage getReferenceImage(String testName) throws IOException{
		String pattern = ".*recording.image([0-9]*).png";
		Pattern r = Pattern.compile(pattern);
		Stream<Path> directory = Files.walk(Paths.get("recordings/" + testName));
		@SuppressWarnings("resource")
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

	@Test
	public void newClassTest() throws IOException {
		String name = "newClass";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}

	@Test
	public void createTwoClassesTest() throws IOException {
		String name = "createTwoClasses";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void createAndSelectClassTest() throws IOException {
		String name = "createAndSelectClass";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void changeClassNameTest() throws IOException {
		String name = "changeClassName";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void addAttributeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "addAttribute";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void addMethodeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "addMethode";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void addAssociationTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "addAssociation";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteClassTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteClass";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void selectAttrTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "selectAttr";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void selectMethodeTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "selectMethode";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteClassWithAssAndMethTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteClassWithAssAndMeth";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteAssociationTest1() throws IOException {
		//Associatie zelf verwijderen
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteAssociation1";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteAssociationTest2() throws IOException {
		//Klasse 1 verwijderen
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteAssociation2";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteAssociationTest3() throws IOException {
		//Klasse 2 verwijderen
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteAssociation3";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void deleteAssociationTest4() throws IOException {
		//Drie klassen associatie van 1 -> 2 van 2 ->3 en dan 2 verwijderen
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "deleteAssociation4";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}	
	
	@Test
	public void doubleClickTest() throws IOException {
		//Twee keer klikken op dezelfde plaats in container
		// zou geen twee klassen mogen maken
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "doubleClick";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void methodePrivateTest() throws IOException {
		//Klasse maken, methode toevoegen private maken, opslaan
		// Terug op methode klikken en kijken of private is aangeklikt
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "methodePrivate";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void focusCheckBoxTest() throws IOException {
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		String name = "focusCheckBox";
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void removeParameterTest() throws IOException {
		//Add a parameter, save everything and delete it again
		String name = "removeParameter";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	/**
	 * Press delete while typing.
	 * @throws IOException
	 */
	@Test
	public void pressDeleteWhileTyping() throws IOException {
		String name = "pressDeleteWhileTyping";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	/**
	 * Checks moving through the listbox in MethodForm with mouse and keyboard.
	 * @throws IOException
	 */
	@Test
	public void keyAndMouseFocusListBox() throws IOException {
		String name = "keyAndMouseFocusListBox";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));

	}

}
