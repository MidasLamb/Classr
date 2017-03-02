package canvaswindow;

import static main.Constants.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import mouse.MouseClick;
import mouse.MouseClickHandler;
import visualobjects.Container;

public class MyCanvasWindow extends CanvasWindow {

	public MyCanvasWindow(String title) {
		super(title);
		setContainer(new Container(0, 0, CONTAINER_WIDTH, CONTAINER_HEIGHT));
		setMouseClickHandler(new MouseClickHandler(this.container));
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
		this.getContainer().show(g);
	}
	
	/**
	 * Called when the user presses (e.getID() == MouseEvent.MOUSE_PRESSED), releases (e.getID() == MouseEvent.MOUSE_RELEASED), or drags (e.getID() == MouseEvent.MOUSE_DRAGGED) the mouse.
	 * 
	 * @param e Details about the event
	 */
	@Override
	protected void handleMouseEvent(MouseEvent e) {
		getMouseClickHandler().handleKey(e);
		this.repaint();
		
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
		this.getContainer().sendKeyToSelected(e);
		this.repaint();
		//TODO Handle shift keys and such
		
	}	
	
	private Container getContainer() {
		return container;
	}

	private void setContainer(Container container) {
		this.container = container;
	}
	
	private Container container;

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	private boolean mousePressed = false;
	
	private MouseClickHandler getMouseClickHandler() {
		return mouseClickHandler;
	}

	private void setMouseClickHandler(MouseClickHandler mouseClickHandler) {
		this.mouseClickHandler = mouseClickHandler;
	}
	
	private MouseClickHandler mouseClickHandler;

}
