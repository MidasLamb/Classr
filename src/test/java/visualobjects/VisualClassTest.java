package visualobjects;

import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.BACKSPACE;

import static main.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import canvaswindow.MyCanvasWindow;
import command.Controller;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import logicalobjects.Association;

public class VisualClassTest {

	@Test
	public void isInTest1() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(0, 0, 0, container, new Controller());
		assertTrue(visualClass.isIn(0, 0));
	}

	@Test
	public void isInTest2() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(0, 0, 0, container, new Controller());
		assertFalse(visualClass.isIn(CLASS_WIDTH + 1, 0));
	}

	@Test
	public void isInTest3() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(10, 5, 0, container, new Controller());
		assertTrue(visualClass.isIn(10, 5));
	}

	@Test
	public void isInTest4() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(10, 5, 0, container, new Controller());
		assertTrue(visualClass.isIn(visualClass.getWidth() + 10, 5));
	}

	@Test
	public void isInTest5() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(10, 5, 0, container, new Controller());
		assertTrue(visualClass.isIn(10, visualClass.getHeight() + 4));
	}

	@Test
	public void isInTest6() {
		Container container = new Container(10, 5, 100, 100, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(10, 5, 0, container, new Controller());
		assertFalse(visualClass.isIn(visualClass.getWidth() + 11, 5));
	}

	@Test
	public void isInTest7() {
		Container container = new Container(10, 5, 100, 100, new MyCanvasWindow("test"));
		VisualClass visualClass = new VisualClass(0, 0, 0, container, new Controller());
		assertFalse(visualClass.isIn(10, visualClass.getHeight() + 6));
	}

	@Test
	public void addAttributeTest() {
		int x = 0;
		int y = 0;
		int z = 0;
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		VisualClass visualClass = new VisualClass(x, y, z, container, new Controller());
		int numberOfChildren = visualClass.getChildren().size();
		int clickY = visualClass.getName().getHeight() + 2;
		int clickX = x + 2;

		visualClass.onDoubleClick(new DoubleClick(clickX, clickY));
		assertTrue((numberOfChildren + 1) == visualClass.getChildren().size());

		assertTrue(visualClass.getLogicalObject().getAttributes().size() == 1);
	}

	@Test
	public void addMethodTest() {
		int x = 0;
		int y = 0;
		int z = 0;
		MyCanvasWindow window = new MyCanvasWindow("Test");
		Container container = new Container(0, 0, 1000, 1000, window);
		VisualClass visualClass = new VisualClass(x, y, z, container, new Controller());
		int numberOfChildren = visualClass.getChildren().size();
		int clickY = visualClass.getName().getHeight() + 2 + CLASS_WHITE_SPACE;
		int clickX = x + 2;

		visualClass.onDoubleClick(new DoubleClick(clickX, clickY));
		// Check if visual has updated.
		assertTrue((numberOfChildren + 1) == visualClass.getChildren().size());

		// Check if logical has updated.
		assertTrue(visualClass.getLogicalObject().getMethods().size() == 1);
	}

	@Test
	public void addAssociationTest() {
		Container container = new Container(0, 0, 1000, 1000, new MyCanvasWindow("test"));
		int x1 = 300;
		int y1 = 300;
		int z1 = 0;
		VisualClass visualClass1 = new VisualClass(x1, y1, z1, container, new Controller());

		int x2 = 300;
		int y2 = 600;
		int z2 = 0;
		VisualClass visualClass2 = new VisualClass(x2, y2, z2, container, new Controller());

		int dragEndX = x2;
		int dragEndY = y2 + (visualClass2.getHeight() / 2) + 2;

		int dragStartX = x1;
		int dragStartY = y1 + (visualClass1.getHeight() / 2) + 2;

		Drag d = new Drag(dragStartX, dragStartY, dragEndX, dragEndY);

		int startSize = container.getChildren().size();

		visualClass2.onDragEnd(d);

		assertTrue(startSize + 1 == container.getChildren().size());

		assertTrue(visualClass1.getLogicalObject().getAssociations().size() == 1);
		assertTrue(visualClass2.getLogicalObject().getAssociations().size() == 1);
		for (Association a : visualClass1.getLogicalObject().getAssociations()) {
			assertTrue(a.getClass1().equals(visualClass1.getLogicalObject())
					|| a.getClass1().equals(visualClass2.getLogicalObject()));
			assertTrue(a.getClass2().equals(visualClass1.getLogicalObject())
					|| a.getClass2().equals(visualClass2.getLogicalObject()));
		}

		for (Association a : visualClass2.getLogicalObject().getAssociations()) {
			assertTrue(a.getClass1().equals(visualClass1.getLogicalObject())
					|| a.getClass1().equals(visualClass2.getLogicalObject()));
			assertTrue(a.getClass2().equals(visualClass1.getLogicalObject())
					|| a.getClass2().equals(visualClass2.getLogicalObject()));
		}

	}

	@Test
	public void getParentTest() {
		Container container = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		VisualClass klasse = new VisualClass(5, 5, 0, container, new Controller());
		assertEquals(container, klasse.getParent());
	}

	@Test
	public void createClassEditNameTest() {
		MyCanvasWindow canvas = new MyCanvasWindow("test");
		Container container = new Container(0, 0, 1000, 1000, canvas);
		container.onDoubleClick(new DoubleClick(300, 300));
		
		VisualClass vc = null;
		for (VisualObject v : container.getChildren()) {
			if (v instanceof VisualClass)
				vc = (VisualClass) v;
		}
		
		while (!vc.getName().getContent().getCurrentDisplayedString().equals("")) {
			container.handleFunctionKey(new FunctionKey(BACKSPACE));
		}
		
		container.handleAsciiKey(new AsciiKey('a'));

		String current = vc.getName().getContent().getCurrentDisplayedString();
		assertEquals("a", current);
		container.handleAsciiKey(new AsciiKey('b'));
		current = vc.getName().getContent().getCurrentDisplayedString();
		assertEquals("ab", current);

		container.handleFunctionKey(new FunctionKey(BACKSPACE));
		current = vc.getName().getContent().getCurrentDisplayedString();
		assertEquals("a", current);

	}

}
