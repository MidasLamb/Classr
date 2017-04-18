package gui.inputHandlers;

import static org.junit.Assert.*;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.Test;

import gui.inputHandlers.KeyHandler;

public class KeyHandlerTest {
	
	@Test
	public void isAsciiTest1() {
		//Enter key
		KeyEvent e = generateKey(10);
		assertFalse(KeyHandler.keyEventIsAscii(e));
	}
	
	@Test
	public void isAsciiTest2(){
		// 'a' key
		KeyEvent e = generateKey(65);
		assertTrue(KeyHandler.keyEventIsAscii(e));
	}
	
	@Test
	public void isAsciiTest3(){
		KeyEvent e = generateKey(KeyEvent.VK_SPACE);
		assertTrue(KeyHandler.keyEventIsAscii(e));
	}
	
	@Test
	public void getFuntionKeyTest(){
		Canvas canvas = new Canvas();
		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, 
				0, KeyEvent.VK_SHIFT,Character.MIN_VALUE);
		assertEquals(null, KeyHandler.getFunctionKey(ke));
	}
	
	private KeyEvent generateKey(int keyCode){
		Canvas canvas = new Canvas();
		return new KeyEvent(canvas, 0, 0, 0, keyCode);
	}

}
