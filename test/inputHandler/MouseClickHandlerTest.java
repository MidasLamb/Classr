package inputHandler;

import static main.Constants.DOUBLECLICK_RANGE;
import static main.Constants.DOUBLECLICK_TRESHOLD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.List;
import java.awt.event.MouseEvent;

import org.junit.Test;

import inputHandlers.MouseClickHandler;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;
import visualobjects.Container;

public class MouseClickHandlerTest {
	
	@Test
	public void singleClickTest1(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertEquals(1, container.amountRecievedClicks);
	}
	
	@Test
	public void singleClickTest2(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void singleClickTest3(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest1(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest2(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest3(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		simulateSingleClick(handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest4(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest5(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest6(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE-1, 0, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest7(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest8(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest9(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(DOUBLECLICK_RANGE, 0, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest10(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest11(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest12(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE-1, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest13(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTest14(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTest15(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(0,0, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest16(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(15,10, handler);
		simulateSingleClick(0, DOUBLECLICK_RANGE, handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTest17(){
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(8,15, handler);
		simulateSingleClick(0, 15+DOUBLECLICK_RANGE, handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTestTime1() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTestTime2() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTestTime3() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD-5);
		simulateSingleClick(handler);
		assertTrue(container.recievedDoubleClick);
	}
	
	@Test
	public void doubleClickTestTime4() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertEquals(2, container.amountRecievedClicks);
	}
	
	@Test
	public void doubleClickTestTime5() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertTrue(container.recievedSingleClick);
	}
	
	@Test
	public void doubleClickTestTime6() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateSingleClick(handler);
		Thread.sleep(DOUBLECLICK_TRESHOLD+5);
		simulateSingleClick(handler);
		assertFalse(container.recievedDoubleClick);
	}
	
	@Test
	public void dragTest1() throws InterruptedException{
		testContainer container = new testContainer();
		MouseClickHandler handler = new MouseClickHandler(container);
		simulateDrag(0, 0, 1, 1, handler);
		assertTrue(container.isDragged);
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
	
	class testContainer extends Container{

		public testContainer() {
			super(0,0,100,100);
		}
		
		@Override
		public void onClick(SingleClick sc){
			amountRecievedClicks++;
			recievedSingleClick = true;
		}
		
		@Override
		public void onDoubleClick(DoubleClick dc){
			amountRecievedClicks++;
			recievedDoubleClick = true;
		}
		
		@Override
		public void onDragEnd(Drag d){
			isDragged = true;
		}
		
		public int amountRecievedClicks;
		public boolean recievedSingleClick;
		public boolean recievedDoubleClick;
		public boolean isDragged;
		
	}

}