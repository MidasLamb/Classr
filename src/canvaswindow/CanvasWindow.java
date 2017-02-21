package canvaswindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A window for custom drawing.
 *
 * To use this class, create a subclass, say MyCanvasWindow, that overrides
 * methods {@link #paint(Graphics)}, {@link #handleMouseEvent(MouseEvent)}, and {@link #handleKeyEvent(KeyEvent)}, and then launch
 * it from your main method as follows:
 * 
 * <pre>
 * public static void main(String[] args) {
 *     java.awt.EventQueue.invokeLater(() -> {
 *         new MyCanvasWindow("My Canvas Window").show();
 *     });
 * }
 * </pre>
 */
public class CanvasWindow {
	
	private Panel panel;
	private Frame frame;
	
	/**
	 * Initializes a CanvasWindow object.
	 * 
	 * @param title Window title
	 */
	protected CanvasWindow(String title) {
		if (!EventQueue.isDispatchThread())
			throw new RuntimeException("You must call this constructor from the AWT dipatch thread");
		panel = new Panel();
		frame = new Frame(title);
	}
	
	/**
	 * Call this method if the canvas is out of date and needs to be repainted.
	 * This will cause method {@link #paint(Graphics)} to be called after the current call of method handleMouseEvent or handleKeyEvent finishes.
	 */
	protected final void repaint() {
		panel.repaint();
	}
	
	/**
	 * Called to allow you to paint on the canvas.
	 * 
	 * You should not use the Graphics object after you return from this method.
	 * 
	 * @param g This object offers the methods that allow you to paint on the canvas.
	 */
	protected void paint(Graphics g) {
	}
	
	/**
	 * Called when the user presses (e.getID() == MouseEvent.MOUSE_PRESSED), releases (e.getID() == MouseEvent.MOUSE_RELEASED), or drags (e.getID() == MouseEvent.MOUSE_DRAGGED) the mouse.
	 * 
	 * @param e Details about the event
	 */
	protected void handleMouseEvent(MouseEvent e) {
	}
	
	/**
	 * Called when the user presses a key (e.getID() == KeyEvent.KEY_PRESSED) or enters a character (e.getID() == KeyEvent.KEY_TYPED).
	 * 
	 * @param e
	 */
	protected void handleKeyEvent(KeyEvent e) {
	}

	private class Panel extends JPanel {
		
		{
			setPreferredSize(new Dimension(600, 600));
			setBackground(Color.WHITE);
			setFocusable(true);
			
			addMouseListener(new MouseAdapter() {
	
				@Override
				public void mouseClicked(MouseEvent e) {
					handleMouseEvent(e);
				}
	
				@Override
				public void mousePressed(MouseEvent e) {
					handleMouseEvent(e);
				}
	
				@Override
				public void mouseReleased(MouseEvent e) {
					handleMouseEvent(e);
				}
				
			});
			
			addMouseMotionListener(new MouseAdapter() {
	
				@Override
				public void mouseDragged(MouseEvent e) {
					handleMouseEvent(e);
				}
				
			});
			
			addKeyListener(new KeyAdapter() {
	
				@Override
				public void keyTyped(KeyEvent e) {
					handleKeyEvent(e);
				}
	
				@Override
				public void keyPressed(KeyEvent e) {
					handleKeyEvent(e);
				}
				
			});
		}
	
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			CanvasWindow.this.paint(g);
		}
		
	}

	private class Frame extends JFrame {
		Frame(String title) {
			super(title);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().add(panel);
			pack();
			setLocationRelativeTo(null);
		}
	}

	public final void show() {
		frame.setVisible(true);
	}

}