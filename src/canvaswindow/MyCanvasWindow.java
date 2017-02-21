package canvaswindow;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

	public MyCanvasWindow(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Called to allow you to paint on the canvas.
	 * 
	 * You should not use the Graphics object after you return from this method.
	 * 
	 * @param g This object offers the methods that allow you to paint on the canvas.
	 */
	@Override
	protected void paint(Graphics g) {
	}
	
	/**
	 * Called when the user presses (e.getID() == MouseEvent.MOUSE_PRESSED), releases (e.getID() == MouseEvent.MOUSE_RELEASED), or drags (e.getID() == MouseEvent.MOUSE_DRAGGED) the mouse.
	 * 
	 * @param e Details about the event
	 */
	@Override
	protected void handleMouseEvent(MouseEvent e) {
		
	}
	
	/**
	 * Called when the user presses a key (e.getID() == KeyEvent.KEY_PRESSED) or enters a character (e.getID() == KeyEvent.KEY_TYPED).
	 * 
	 * @param e
	 */
	@Override
	protected void handleKeyEvent(KeyEvent e) {
	}
	

}
