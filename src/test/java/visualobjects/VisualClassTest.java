package visualobjects;

import static inputHandler.keys.FunctionKey.FunctionKeyType.BACKSPACE;
import static main.Constants.CLASS_WHITE_SPACE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import inputHandler.keys.AsciiKey;
import inputHandler.keys.FunctionKey;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import logicalobjects.Association;

public class VisualClassTest {

	@Test
	public void isInTest1() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(0, 0,0, container);
		assertTrue(visualClass.isIn(0, 0));
	}
	
	@Test
	public void isInTest2() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(0, 0, 0, container);
		assertFalse(visualClass.isIn(101, 0));
	}
	
	@Test
	public void isInTest3() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, 0, container);
		assertTrue(visualClass.isIn(10, 5));
	}
	
	@Test
	public void isInTest4() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, 0, container);
		assertTrue(visualClass.isIn(visualClass.getWidth()+10, 5));
	}
	
	@Test
	public void isInTest5() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, 0, container);
		assertTrue(visualClass.isIn(10, visualClass.getHeight()+4));
	}
	
	@Test
	public void isInTest6() {
		Container container = new Container(10, 5, 100, 100);
		VisualClass visualClass = new VisualClass(10, 5, 0, container);
		assertFalse(visualClass.isIn(visualClass.getWidth()+11, 5));
	}
	
	@Test
	public void isInTest7() {
		Container container = new Container(10, 5, 100, 100);
		VisualClass visualClass = new VisualClass(0, 0, 0, container);
		assertFalse(visualClass.isIn(10, visualClass.getHeight()+6));
	}
	
	@Test
	public void addAttributeTest(){
		int x = 0;
		int y = 0;
		int z = 0;
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(x, y, z, container);
		int numberOfChildren = visualClass.getChildren().size();
		int clickY = visualClass.getName().getHeight() + 2;
		int clickX = x + 2;
		
		visualClass.onDoubleClick(new DoubleClick(clickX, clickY));
		assertTrue((numberOfChildren + 1 )== visualClass.getChildren().size());
		
		assertTrue(visualClass.getLogicalObject().getAttributes().size() == 1);
	}
	
	@Test
	public void addMethodTest(){
		int x = 0;
		int y = 0;
		int z = 0;
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(x, y, z, container);
		int numberOfChildren = visualClass.getChildren().size();
		int clickY = visualClass.getName().getHeight() + 2 + CLASS_WHITE_SPACE;
		int clickX = x + 2;
		
		visualClass.onDoubleClick(new DoubleClick(clickX, clickY));
		//Check if visual has updated.
		assertTrue((numberOfChildren + 1 )== visualClass.getChildren().size());
		
		//Check if logical has updated.
		assertTrue(visualClass.getLogicalObject().getMethods().size() == 1);
	}
	
	@Test
	public void addAssociationTest(){
		Container container = new Container(0, 0, 1000, 1000);
		int x1 = 0;
		int y1 = 0;
		int z1 = 0;
		VisualClass visualClass1 = new VisualClass(x1, y1, z1, container);
		
		int x2 = 0;
		int y2 = 0;
		int z2 = 0;
		VisualClass visualClass2 = new VisualClass(x2, y2, z2, container);
		
		int dragEndX = x2;
		int dragEndY = y2 + (visualClass2.getHeight() / 2) + 2;
		
		int dragStartX = x1;
		int dragStartY = y1 + (visualClass1.getHeight() / 2) + 2;
		
		Drag d = new Drag(dragStartX, dragStartY, dragEndX, dragEndY);
		
		int startSize = container.getChildren().size();
		
		visualClass2.onDragEnd(d);
		
		assertTrue(startSize + 1 ==  container.getChildren().size());
		
		assertTrue(visualClass1.getLogicalObject().getAssociations().size() == 1);
		assertTrue(visualClass2.getLogicalObject().getAssociations().size() == 1);
		for (Association a: visualClass1.getLogicalObject().getAssociations()){
			assertTrue(a.getClass1().equals(visualClass1.getLogicalObject()) || a.getClass1().equals(visualClass2.getLogicalObject()));
			assertTrue(a.getClass2().equals(visualClass1.getLogicalObject()) || a.getClass2().equals(visualClass2.getLogicalObject()));
		}
		
		for (Association a: visualClass2.getLogicalObject().getAssociations()){
			assertTrue(a.getClass1().equals(visualClass1.getLogicalObject()) || a.getClass1().equals(visualClass2.getLogicalObject()));
			assertTrue(a.getClass2().equals(visualClass1.getLogicalObject()) || a.getClass2().equals(visualClass2.getLogicalObject()));
		}
		
	}
	
	@Test
	public void getParentTest() {
		Container container = new Container(0, 0, 100, 100);
		VisualClass klasse = new VisualClass(5, 5, 0, container);
		assertEquals(container, klasse.getParent());
	}
	
	@Test
	public void createClassEditNameTest(){
		Container container = new Container(0, 0, 100, 100);
		container.onDoubleClick(new DoubleClick(10,10));
		container.handleAsciiKey(new AsciiKey('a'));		
		for (VisualObject v: container.getChildren()){
			String n = ((Text) ((VisualClass) v).getName().getContent()).getString();
			assertEquals("a", n);
		}
		container.handleAsciiKey(new AsciiKey('b'));		
		for (VisualObject v: container.getChildren()){
			String n = ((Text) ((VisualClass) v).getName().getContent()).getString();
			assertEquals("ab", n);
		}
		container.handleFunctionKey(new FunctionKey(BACKSPACE));		
		for (VisualObject v: container.getChildren()){
			String n = ((Text) ((VisualClass) v).getName().getContent()).getString();
			assertEquals("a", n);
		}
	}

}
