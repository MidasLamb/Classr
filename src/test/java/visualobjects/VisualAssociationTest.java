package visualobjects;

import static org.junit.Assert.*;

import org.junit.Test;

import canvaswindow.MyCanvasWindow;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

public class VisualAssociationTest {

	@Test
	public void deleteOnDeleteClassTest() {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		// Create new class
		DoubleClick click1 = new DoubleClick(103,108);
		// Create new association
		Drag drag = new Drag(104,142,104,142);
		// Deselect all objects
		SingleClick click2 = new SingleClick(1,1);
		
		container.onDoubleClick(click1);
		container.onDragEnd(drag);
		container.onClick(click2);
		
		// Select class
		container.onClick(new SingleClick(103, 108));
		
		container.handleFunctionKey(new FunctionKey(FunctionKeyType.DELETE));
		
		assertEquals(0, container.getChildren().size());
	}

}
