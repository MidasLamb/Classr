package canvaswindow;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import gui.form.base.Form;
import gui.inputHandlers.KeyHandler;
import gui.inputHandlers.MouseClickHandler;
import guiToApplication.FormWrapper;

public class FormWindow extends CanvasWindow {

	private KeyHandler keyHandler;

	private MouseClickHandler mouseClickHandler;

	private FormWrapper form;
	
	public FormWindow(String title, FormWrapper form) {
		super(title);
		setForm(form);
		setMouseClickHandler(new MouseClickHandler(getForm()));
	}
	
	private void setFont(Graphics g){
		Font font = new Font("Dialog",Font.PLAIN, 12);
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
	protected void paint(Graphics g) {
		this.setFont(g);
		this.getForm().draw(g);
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
	protected void handleMouseEvent(MouseEvent e) {
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
	protected void handleKeyEvent(KeyEvent e) {
		getKeyHandler().handleInput(e);
		this.repaint();
		// TODO Handle shift keys and such

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

	private FormWrapper getForm() {
		return form;
	}

	private void setForm(FormWrapper form) {
		this.form = form;
	}

}
