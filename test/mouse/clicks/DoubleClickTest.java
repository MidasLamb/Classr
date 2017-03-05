package mouse.clicks;

import static org.junit.Assert.*;

import org.junit.Test;

import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.List;

import static main.Constants.*;
import visualobjects.Container;
import inputHandlers.MouseClickHandler;

public class DoubleClickTest {
	
	@Test
	public void doubleClicktest() throws InterruptedException{
		assertTrue(doubleClickRegistrationTest(0, 0, DOUBLECLICK_TRESHOLD/2, 0, 0, 100, 100));

	}
	
	@Test
	public void slowDoubleClicktest() throws InterruptedException{
		assertFalse(doubleClickRegistrationTest(0, 0, DOUBLECLICK_TRESHOLD*2, 0, 0, 100, 100));
	}
	
	@Test
	public void outOfContainerDoubleClicktest() throws InterruptedException{
		assertFalse(doubleClickRegistrationTest(200, 0, DOUBLECLICK_TRESHOLD/2, 0, 0, 100, 100));
	}

	

	/**
	 * Tests the registration of a double click by simulating two different clicks
	 * 
	 * @param 	x
	 * 			x-coordinate of the click
	 * @param 	y
	 * 			y-coordinate of the click
	 * @param 	ms
	 * 			amount of milliseconds between clicks
	 * @param 	cX
	 * 			x-coordinate of the container
	 * @param 	cY
	 * 			y-coordinate of the container
	 * @param 	cW
	 * 			width of the container
	 * @param 	cH
	 * 			Height of the container
	 * @throws InterruptedException
	 */
	public boolean doubleClickRegistrationTest(int x, int y, int ms, int cX, int cY, int cW, int cH) throws InterruptedException {
		Container container = new Container(cX,cY,cW,cH); //doesn't really matter
		MouseClickHandler handler = new MouseClickHandler(container);
		Component dummy = new List();
		//first click
		MouseEvent event = new MouseEvent(dummy,MouseEvent.MOUSE_PRESSED,1,MouseEvent.BUTTON1,x,y,2,false);
		handler.handleInput(event);
		//wait
		Thread.sleep(ms);
		//second click
		event = new MouseEvent(dummy,MouseEvent.MOUSE_PRESSED,1,MouseEvent.BUTTON1,x,y,2,false);
		return handler.isDoubleClick(event);
	}

}