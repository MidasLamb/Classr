package visualobjects;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

import canvaswindow.MyCanvasWindow;
import command.Controller;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

public class VisualAssociationTest {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void deleteOnDeleteClassTest() {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = (Container) canvas.getContent();
		container.getChildren().forEach(x -> x.delete());
		// Create new class
		DoubleClick click1 = new DoubleClick(211,215);
		// Create new association
		Drag drag = new Drag(208,246,208,246);
		// Deselect all objects
		SingleClick click2 = new SingleClick(143,360);
		
		container.onDoubleClick(click1);
		container.onDragEnd(drag);
		container.onClick(click2);
		
		// Select class
		container.onClick(new SingleClick(235,224));
		
		container.handleFunctionKey(new FunctionKey(FunctionKeyType.DELETE));
		
		assertEquals(0, container.getChildren().size());
	}

}
