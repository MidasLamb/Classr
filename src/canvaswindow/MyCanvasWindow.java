package canvaswindow;

import static main.Constants.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import mouse.MouseClick;
import mouse.MouseClickHandler;
import visualobjects.Container;

public class MyCanvasWindow extends CanvasWindow {
	private Container container;
	private boolean mousePressed = false;
	private MouseClickHandler mouseClickHandler;
	
	public MyCanvasWindow(String title) {
		super(title);
		this.container = new Container(0, 0, CONTAINER_WIDTH, CONTAINER_HEIGHT);
		this.mouseClickHandler = new MouseClickHandler(this.container);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Called to allow you to paint on the canvas. The canvas given is completely clean.
	 * 
	 * 
	 * You should not use the Graphics object after you return from this method.
	 * 
	 * @param g This object offers the methods that allow you to paint on the canvas.
	 */
	@Override
	protected void paint(Graphics g) {
		this.container.show(g);
	}
	
	/**
	 * Called when the user presses (e.getID() == MouseEvent.MOUSE_PRESSED), releases (e.getID() == MouseEvent.MOUSE_RELEASED), or drags (e.getID() == MouseEvent.MOUSE_DRAGGED) the mouse.
	 * 
	 * @param e Details about the event
	 */
	@Override
	protected void handleMouseEvent(MouseEvent e) {
		this.mouseClickHandler.handleKey(e);
		this.repaint();
		//System.out.println(e.getButton());
		//Dragging = 0;
		//Pressing down/releasing = 1;
		
//		
//		boolean clickedDown = false;
//		if(e.getButton() == 1){
//			if (this.mousePressed == false)
//				clickedDown = true;
//			this.mousePressed = !this.mousePressed;
//		}
//
//		if (clickedDown){
//			this.container.select(e.getX(), e.getY(), MouseClick.CLICK);
//			this.repaint();
//		}
		
	}
	
	/**
	 * Called when the user presses a key (e.getID() == KeyEvent.KEY_PRESSED) or enters a character (e.getID() == KeyEvent.KEY_TYPED).
	 * 
	 * @param e
	 */
	@Override
	protected void handleKeyEvent(KeyEvent e) {
		if (e.getKeyCode() == 0)
			return;
		this.container.sendKeyToSelected(e);
		this.repaint();
		//TODO Handle shift keys and such
		
	}	

}
