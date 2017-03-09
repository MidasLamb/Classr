package visualobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.Test;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;
import visualobjects.Container;
import visualobjects.VisualClass;

public class ContainerTest {

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
	
	@Test
	public void deteleteTest2() {
		Container container = new Container(10, 10, 100, 100);
		new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.removeChild(klasse2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest3() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest4() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		new VisualClass(1, 1, 1, container);
		container.removeChild(klasse1);
		container.removeChild(klasse1);
	}
	
	@Test
	public void deteleteTest5() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse1 = new VisualClass(0, 0, 0, container);
		VisualClass klasse2 = new VisualClass(1, 1, 1, container);
		container.removeChild(klasse1);
		container.removeChild(klasse2);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deteleteTest6() {
		Container container = new Container(10, 10, 100, 100);
		VisualClass klasse = new VisualClass(0, 0, 0, container);
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
	
	@Test
	public void createClassTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(138,101);
		container.onDoubleClick(click1);
		boolean found = false;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void createClassesTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(138,101);
		DoubleClick click2 = new DoubleClick(226,299);
		DoubleClick click3 = new DoubleClick(700,400);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDoubleClick(click3);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				count++;
			}
		}
		assertEquals(3, count);
	}
	
	@Test
	public void createAttributeTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(182,162);
		DoubleClick click2 = new DoubleClick(210,196);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void createMethodeTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(149,118);
		DoubleClick click2 = new DoubleClick(201,164);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		int count = 0;
		for(VisualObject child : container.getChildren()){
			if(child instanceof PaddingBox){
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	public void createAssTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(138,101);
		DoubleClick click2 = new DoubleClick(226,299);
		Drag drag = new Drag(137,135, 225,333);
		container.onDoubleClick(click1);
		container.onDoubleClick(click2);
		container.onDragStart(drag);
		container.onDragEnd(drag);
		boolean found = false;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualAssociation){
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void deleteClassTest(){
		Container container = new Container(0, 0, 1000, 1000);
		DoubleClick click1 = new DoubleClick(303,326);
		SingleClick click2 = new SingleClick(389,459);
		SingleClick click3 = new SingleClick(329,343);
		container.onDoubleClick(click1);
		//klik op andere locatie
		container.onClick(click2);
		//klik op klasse
		container.onClick(click3);
		Canvas canvas = new Canvas();
		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_DELETE, 'a');
		container.sendKeyToSelected(ke);
		boolean found = false;
		for(VisualObject child : container.getChildren()){
			if(child instanceof VisualClass){
				found = true;
			}
		}
		assertFalse(found);
	}
	
}
