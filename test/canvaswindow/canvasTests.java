package canvaswindow;

import static org.junit.Assert.*;

import org.junit.Test;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.MouseClick;
import inputHandlers.clicks.SingleClick;
import visualobjects.Container;
import visualobjects.VisualObject;
import visualobjects.visualclass.VisualClass;

public class canvasTests {

	@Test
	public void isInTest1() {
		Container container = new Container(0, 0, 100, 100);
		assertTrue(container.isIn(0, 0));
	}
	
	@Test
	public void isInTest2() {
		Container container = new Container(0, 0, 100, 100);
		assertTrue(container.isIn(container.getWidth(), container.getHeight()));
	}
	
	@Test
	public void isInTest3() {
		Container container = new Container(10, 10, 100, 100);
		assertTrue(container.isIn(container.getWidth()-10, container.getHeight()-10));
	}
	
	@Test
	public void isInTest4() {
		Container container = new Container(0, 0, 100, 200);
		assertTrue(container.isIn(70, 150));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest1() {
		Container container = new Container(10, 10, 100, 100);
		container.removeChild(container);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest2() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.addChild(klasse1);
		container.removeChild(klasse2);
	}
	
	@Test
	public void deteleteTest3() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		container.addChild(klasse1);
		container.addChild(klasse1);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest4() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.addChild(klasse1);
		container.addChild(klasse2);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test
	public void deteleteTest5() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.addChild(klasse1);
		container.addChild(klasse2);
		container.removeChild(klasse1);
		container.removeChild(klasse2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest6() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse = new VisualClass(0, 0, 0, container);
		container.addChild(klasse);
		klasse.delete();
		container.removeChild(klasse);
	}
	
	@Test
	public void getParentTest() {
		Container container = new Container(10, 10, 100, 100);
		assertEquals(null,container.getParent());
	}
	
	@Test
	public void constructorTest1() {
		Container container = new Container(0, 0, 100, 200);
		assertEquals(200, container.getHeight());
	}
	
	@Test
	public void constructorTest2() {
		Container container = new Container(0, 0, 100, 200);
		assertEquals(100, container.getWidth());
	}
	
	@Test
	public void selectedTest1() {
		Container container = new Container(0, 0, 100, 200);
		SingleClick click = new SingleClick(10, 20);
		container.onClick(click);
		assertFalse(container.isSelected());
	}
	
	@Test
	public void selectedTest2() {
		Container container = new Container(0, 0, 100, 200);
		assertFalse(container.isSelected());
	}
	
	@Test
	public void selectedTest3() {
		Container container = new Container(0, 0, 100, 200);
		DoubleClick doubleClick = new DoubleClick(10, 20);
		container.onDoubleClick(doubleClick);
		assertFalse(container.isSelected());
	}
	
}
