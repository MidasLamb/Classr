package visualobjects.visualclass;

import static org.junit.Assert.*;

import org.junit.Test;

import visualobjects.Container;

public class VisualClassTest {

	@Test
	public void isInTest1() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(0, 0, container);
		assertTrue(visualClass.isIn(0, 0));
	}
	
	@Test
	public void isInTest2() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(0, 0, container);
		assertFalse(visualClass.isIn(101, 0));
	}
	
	@Test
	public void isInTest3() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, container);
		assertTrue(visualClass.isIn(10, 5));
	}
	
	@Test
	public void isInTest4() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, container);
		assertTrue(visualClass.isIn(visualClass.getWidth()+10, 5));
	}
	
	@Test
	public void isInTest5() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass visualClass = new VisualClass(10, 5, container);
		assertTrue(visualClass.isIn(10, visualClass.getHeight()+4));
	}
	
	@Test
	public void isInTest6() {
		Container container = new Container(10, 5, 100, 100);
		VisualClass visualClass = new VisualClass(10, 5, container);
		assertFalse(visualClass.isIn(visualClass.getWidth()+11, 5));
	}
	
	@Test
	public void isInTest7() {
		Container container = new Container(10, 5, 100, 100);
		VisualClass visualClass = new VisualClass(0, 0, container);
		assertFalse(visualClass.isIn(10, visualClass.getHeight()+6));
	}
	
	@Test
	public void deleteTest() {
		Container container = new Container(10, 5, 100, 100);
		VisualClass visualClass = new VisualClass(0, 0, container);
		visualClass.delete();
		Graphics g = new Grap
		
	}

}
