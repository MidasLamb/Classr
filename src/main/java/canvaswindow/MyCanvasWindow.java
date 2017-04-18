package canvaswindow;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.Queue;

import gui.inputHandlers.KeyHandler;
import gui.inputHandlers.MouseClickHandler;
import interfaces.CanvasContent;
import visualobjects.Container;

public class MyCanvasWindow extends CanvasWindow {

	private KeyHandler keyHandler;

	private MouseClickHandler mouseClickHandler;

	private CanvasContent content;
	private ArrayDeque<CanvasContent> contentQueue;
	
	public MyCanvasWindow(String title) {
		super(title);
		setContent(new Container(0, 0, CONTAINER_WIDTH, CONTAINER_HEIGHT, this));
		setMouseClickHandler(new MouseClickHandler(getContent()));
		setKeyHandler(new KeyHandler(getContent()));
		this.setContentQueue(new ArrayDeque<CanvasContent>());
	}
	
	private void setFont(Graphics g){
		Font font = new Font(Font.SANS_SERIF,Font.PLAIN, 12);
		g.setFont(font);
	}

	/**
	 * Called to allow you to paint on the canvas. The canvas given is
	 * completely clean.
	 * 
	 * 
	 * You should not use the Graphics object after you return from this method.
	 * 
	 * @param g
	 *            This object offers the methods that allow you to paint on the
	 *            canvas.
	 */
	@Override
	protected final void paint(Graphics g) {
		this.setFont(g);
		this.getContent().show(g);
	}

	/**
	 * Called when the user presses (e.getID() == MouseEvent.MOUSE_PRESSED),
	 * releases (e.getID() == MouseEvent.MOUSE_RELEASED), or drags (e.getID() ==
	 * MouseEvent.MOUSE_DRAGGED) the mouse.
	 * 
	 * @param e
	 *            Details about the event
	 */
	@Override
	protected final void handleMouseEvent(MouseEvent e) {
		getMouseClickHandler().handleInput(e);
		this.repaint();

	}

	/**
	 * Called when the user presses a key (e.getID() == KeyEvent.KEY_PRESSED) or
	 * enters a character (e.getID() == KeyEvent.KEY_TYPED).
	 * 
	 * @param e
	 */
	@Override
	protected final void handleKeyEvent(KeyEvent e) {
		getKeyHandler().handleInput(e);
		this.repaint();
		// TODO Handle shift keys and such

	}

	private CanvasContent getContent() {
		return content;
	}

	private void setContent(CanvasContent content) {
		this.content = content;
	}
	
	/**
	 * Switches to the passed in content.
	 * @param content
	 */
	public final void addContentAndSwitchTo(CanvasContent content){
		this.getContentQueue().addFirst(this.getContent());
		this.switchToContent(content);
	}
	
	/**
	 * Puts the content as the next one to be displayed if the currently showed one is closed.
	 * @param content
	 */
	public final void addContentAsNext(CanvasContent content){
		this.getContentQueue().add(content);
	}
	
	/**
	 * Closes the current displayed content and switches in the previous content.
	 */
	private void closeCurrentContent(){
		CanvasContent prev = this.getContentQueue().pop();
		this.switchToContent(prev);
	}
	
	/**
	 * Closes the passed in content. If that content is the currently displayed content, we switch it out with the previous content.
	 * @param content
	 */
	public final void closeContent(CanvasContent content){
		if (content.equals(this.getContent())){
			this.closeCurrentContent();
		} else {
			this.getContentQueue().remove(content);
		}
	}
	
	/**
	 * Switches the canvas to display the passed in content.
	 * @param content
	 */
	private void switchToContent(CanvasContent content){
		this.setContent(content);
		setMouseClickHandler(new MouseClickHandler(getContent()));
		setKeyHandler(new KeyHandler(getContent()));
	}

	private MouseClickHandler getMouseClickHandler() {
		return mouseClickHandler;
	}

	private void setMouseClickHandler(MouseClickHandler mouseClickHandler) {
		this.mouseClickHandler = mouseClickHandler;
	}

	public KeyHandler getKeyHandler() {
		return keyHandler;
	}

	public void setKeyHandler(KeyHandler keyHandler) {
		this.keyHandler = keyHandler;
	}

	private ArrayDeque<CanvasContent> getContentQueue() {
		return contentQueue;
	}

	private void setContentQueue(ArrayDeque<CanvasContent> contentQueue) {
		this.contentQueue = contentQueue;
	}

}
