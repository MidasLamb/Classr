package gui.inputHandlers;

import static main.Constants.DOUBLECLICK_RANGE;
import static main.Constants.DOUBLECLICK_TRESHOLD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.List;
import java.awt.event.MouseEvent;

import org.junit.Test;

import visualobjects.TestContainer;

public class MouseClickHandlerTest {
	
	@Test
	public void singleClickTest1(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertEquals(1, container.amountRecievedClicks);
	}
	
	@Test
	public void singleClickTest2(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void singleClickTest3(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest1(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest2(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest3(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest4(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest5(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest6(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest7(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest8(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest9(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest10(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest11(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest12(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest13(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest14(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest15(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest16(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(15,10, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest17(){
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(8,15, handler);
		simulateSingleClick(0, 15+DOUBLECLICK_RANGE, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTestTime1() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTestTime2() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTestTime3() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTestTime4() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTestTime5() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTestTime6() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void dragTest1() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateDrag(0, 0, 1, 1, handler);
		assertTrue(container.isDragged);
	}
	
	@Test
	public void dragTest2() throws InterruptedException{
		TestContainer container = new TestContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		Component dummy = new List();
		MouseEvent event = new MouseEvent(dummy,MouseEvent.MOUSE_DRAGGED,1,MouseEvent.BUTTON1,2,2,2,false);
		handler.handleInput(event);
		assertFalse(container.isDragged);
	}
	
	private static void simulateSingleClick(MouseClickHandler handler){
		simulateSingleClick(0, 0, handler);
	}
	
	private static void simulateSingleClick(int x, int y, MouseClickHandler handler){
		Component dummy = new List();
		MouseEvent event = new MouseEvent(dummy,MouseEvent.MOUSE_PRESSED,1,MouseEvent.BUTTON1,x,y,2,false);
		handler.handleInput(event);
	}
	
	private static void simulateDrag(int x1, int y1, int x2, int y2, MouseClickHandler handler){
		Component dummy = new List();
		MouseEvent event = new MouseEvent(dummy,MouseEvent.MOUSE_DRAGGED,1,MouseEvent.BUTTON1,x1,y1,2,false);
		handler.handleInput(event);
		event = new MouseEvent(dummy,MouseEvent.MOUSE_RELEASED,1,MouseEvent.BUTTON1,x2,y2,2,false);
		handler.handleInput(event);
	}
}