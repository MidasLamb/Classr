package visualobjects;

import static main.Constants.Z_CLASS;

import canvaswindow.MyCanvasWindow;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import interfaces.CanvasContent;

public class Container extends VisualObject  implements CanvasContent{
	private VisualObject selected;
	private MyCanvasWindow window;

	public Container(int x, int y, int width, int height, MyCanvasWindow window) {
		super(x, y, Integer.MIN_VALUE, width, height, null);
		this.setCanvasWindow(window);
	}
	
	public Container(int x, int y, int width, int height) {
		super(x, y, Integer.MIN_VALUE, width, height, null);
	}

	/**
	 * Unselects the selected item (if present), and sets VisualObject vo as
	 * selected
	 * 
	 * @param vo
	 *            VisualObject that will be selected
	 */
	public final void switchSelectedTo(VisualObject vo) {
		// Unselect previous selected item
		if (getSelected() != null)
			getSelected().setSelected(false);
		// Select current selected item
		setSelected(vo);
		if (vo != null)
			vo.setSelected(true);
		
	}

	/**
	 * Creates a new visual class in this container adds the class to the
	 * children makes the text item of the class the selected item
	 * 
	 * @param x
	 *            The x coordinate where the class needs to be showed
	 * @param y
	 *            The y coordinate where the class needs to be showed
	 */
	private void createNewClass(int x, int y) {
		new VisualClass(x, y, Z_CLASS, this);

	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		if (this.select(dc.getX(), dc.getY()).equals(this)) {
			// Double click on empty
			createNewClass(dc.getX(), dc.getY());
		} else {
			super.onDoubleClick(dc);
		}
	}

	@Override
	public void onClick(SingleClick sc) {
		int x = sc.getX();
		int y = sc.getY();
		boolean b = false;
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(x, y))
				b = true;
		}
		if (b)
			super.onClick(sc);
		else
			this.switchSelectedTo(null);
	}

	/**
	 * @return VisualObject that is selected
	 */
	final VisualObject getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            VisualObject that will be set as selected
	 */
	private void setSelected(VisualObject selected) {
		this.selected = selected;
	}

	MyCanvasWindow getCanvasWindow() {
		return window;
	}

	private void setCanvasWindow(MyCanvasWindow window) {
		this.window = window;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		if(getSelected() != null)
			getSelected().handleAsciiKey(key);
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		if(getSelected() != null)
			getSelected().handleFunctionKey(key);
	}

}
