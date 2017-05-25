package canvaswindow;

import static main.Constants.CANVAS_TITLE;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

import visualobjects.Backend;

public class VisualTests {
	
	@Before
	public void init(){
		try {
			final Field field = Backend.class.getDeclaredField("container");
			field.setAccessible(true);
			final Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			final Field field = Backend.class.getDeclaredField("controller");
			field.setAccessible(true);
			final Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public void autoresizeTest() throws IOException { 
		 String name = "autoresizeTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void faultyNameOverwriteTest() throws IOException { 
		 String name = "faultyNameOverwriteTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void barTest() throws IOException { 
		 String name = "barTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}

	@Test 
	public void resizeContentBoxTest() throws IOException { 
		 String name = "resizeContentBoxTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
		 
	}
	
	@Test
	public void moveClassAndContentBoxTest() throws IOException {
		String name = "moveClassAndContentBoxTest";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test
	public void resizeClassTest() throws IOException {
		String name = "resizeClassTest";
		MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE);
		assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow));
	}
	
	@Test 
	public void createMethodTest() throws IOException { 
		 String name = "createMethodTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void createAttributeTest() throws IOException { 
		 String name = "createAttributeTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void openMethodFormTest() throws IOException { 
		 String name = "openMethodFormTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void openAttributeFormTest() throws IOException { 
		 String name = "openAttributeFormTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void attributeFormTest1() throws IOException { 
		 String name = "attributeFormTest1"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void methodFormTest1() throws IOException { 
		 String name = "methodFormTest1"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void methodParameterTest1() throws IOException { 
		 String name = "methodParameterTest1"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void createAssociationTest() throws IOException { 
		 String name = "createAssociationTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void associationDeleteClassTest() throws IOException { 
		 String name = "associationDeleteClassTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void associationDeleteMiddleClassTest() throws IOException { 
		 String name = "associationDeleteMiddleClassTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
	@Test 
	public void decoupleTest() throws IOException { 
		 String name = "decoupleTest"; 
		 MyCanvasWindow canvasWindow = new MyCanvasWindow(CANVAS_TITLE); 
		 assertTrue(MyCanvasWindow.replayRecording("recordings/"+name+"/recording", canvasWindow)); 
	}
	
}
