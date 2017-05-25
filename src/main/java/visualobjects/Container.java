package visualobjects;

import static main.Constants.CLASS_WIDTH;
import static main.Constants.Z_CLASS;

import java.util.ArrayList;
import java.util.Collection;

import barBuilder.MenuBarBuilder;
import barBuilder.ToolBarBuilder;
import canvaswindow.MyCanvasWindow;
import command.Controller;
import command.CreateClassCommand;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.form.base.MenuBar;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;
import interfaces.CanvasContent;
import logicalobjects.LogicalVoid;

/**
 * Container for VisualObjects
 */
public class Container extends VisualObject<LogicalVoid> implements CanvasContent {
	private VisualObject<?> selected;
	private Collection<VisualObject<?>> prior;
	private MyCanvasWindow window;

	/**
	 * 
	 * @param x
	 *            The x-coordinate of the container
	 * @param y
	 *            The y-coordinate of the container
	 * @param width
	 *            the width of the container
	 * @param height
	 *            the height of the container
	 * @param window
	 *            the canvas window in this container
	 */
	public Container(int x, int y, int width, int height, MyCanvasWindow window) {
		super(x, y, Integer.MIN_VALUE, width, height, null, new Controller());
		this.setCanvasWindow(window);

		Backend.initialize(this, getController());
		this.setPrior(new ArrayList<VisualObject<?>>());
		this.createToolBar();
		this.createMenuBar();

	}

	/**
	 * Create a MenuBar
	 */
	private void createMenuBar() {
		int defaultHeight = 30;
		int x = 0;
		int y = 30;
		MenuBarBuilder builder = new MenuBarBuilder(this.getWidth(), defaultHeight);
		MenuBar menu = builder.getMenuBar();
		FormObjectWrapper<MenuBar> bar = new FormObjectWrapper<MenuBar>(menu, x, y, Integer.MAX_VALUE, this.getWidth(),
				defaultHeight, this, getController());
		addToPrior(bar);
	}

	/**
	 * Create a ToolBar
	 */
	private void createToolBar() {
		int defaultHeight = 30;
		int x = 0;
		int y = 0;
		ToolBarBuilder builder = new ToolBarBuilder(this.getWidth(), defaultHeight);
		MenuBar toolbar = builder.getMenuBar();
		FormObjectWrapper<MenuBar> bar = new FormObjectWrapper<>(toolbar, x, y, Integer.MAX_VALUE, this.getWidth(),
				defaultHeight, this, getController());
		addToPrior(bar);
	}

	/**
	 * Unselects the selected item (if present), and sets VisualObject vo as
	 * selected
	 * 
	 * @param vo
	 *            VisualObject that will be selected
	 */
	public final void switchSelectedTo(VisualObject<?> vo) {
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
	 * @return the created visualClass
	 */
	public VisualClass createNewClass(int x, int y) {
		return new VisualClass(x, y, Z_CLASS, this, getController());
	}

	/**
	 * Creates a new visual class in this container when no location is given
	 * adds the class to the children makes the text item of the class the
	 * selected item The location for this class will be an empty location in
	 * the container If there is no empty location, no class will be created
	 * 
	 * @return the created visualClass, null if there is no empty space
	 */
	public VisualClass createNewClass() {
		try {
			int[] coordinates = findEmptyPosition();
			return createNewClass(coordinates[0], coordinates[1]);
		} catch (IllegalStateException e) {
			return null;
		}
	}

	/**
	 * Finds an empty position in the container
	 * 
	 * @return coordinates [x,y] if there exists a position
	 * @throws IllegalStateException
	 *             if there is no empty position
	 */
	private int[] findEmptyPosition() throws IllegalStateException {
		int startPos = 10;
		int x = startPos;
		int y = startPos;
		while (isChildIn(x, y)) {
			if (x + CLASS_WIDTH > getWidth()) {
				x = startPos;
				y += 10;
			} else {
				x += 10;
			}
			if (y > getHeight())
				throw new IllegalStateException();
		}
		return new int[] { x, y };
	}

	/**
	 * @param x
	 *            the x-coordinate
	 * @param y
	 *            the y-coordinate
	 * @return True if there is a child in the given coordinates, otherwise
	 *         false
	 */
	private boolean isChildIn(int x, int y) {
		return getChildren().stream().anyMatch(child -> child.isIn(x, y));
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		if (this.select(dc.getX(), dc.getY()).equals(this)) {
			// Double click on empty
			getController().executeCommand(new CreateClassCommand(this, dc.getX(), dc.getY()));
		} else {
			super.onDoubleClick(dc);
		}
	}

	@Override
	public void onClick(SingleClick sc) {
		int x = sc.getX();
		int y = sc.getY();
		boolean b = false;
		for (VisualObject<?> v : this.getChildren()) {
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
	final VisualObject<?> getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            VisualObject that will be set as selected
	 */
	private void setSelected(VisualObject<?> selected) {
		this.selected = selected;
	}

	/**
	 * @return the canvasWindow
	 */
	MyCanvasWindow getCanvasWindow() {
		return window;
	}

	/**
	 * @param window
	 *            the canvas window
	 */
	private void setCanvasWindow(MyCanvasWindow window) {
		this.window = window;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		if (getSelected() != null)
			getSelected().handleAsciiKey(key);
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		if (key.getKeyType() == FunctionKeyType.CTRL_Z)
			getController().undo();
		if (key.getKeyType() == FunctionKeyType.CTRL_Y)
			getController().redo();
		if (getSelected() != null)
			getSelected().handleFunctionKey(key);
	}

	@Override
	public int getWidth() {
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		return super.getHeight();
	}

	/**
	 * Bring the given VisualObject to the front of the canvas.
	 * 
	 * @param vo
	 *            the VisualObject to draw at the front
	 */
	void bringToFront(VisualObject<?> vo) {
		children.remove(vo);
		children.add(vo);
		children.remove(getPrior());
		children.addAll(getPrior());
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return visitor.visit(this);
	}

	/**
	 * @return the prior
	 */
	private final Collection<VisualObject<?>> getPrior() {
		return prior;
	}

	/**
	 * @param prior
	 *            the prior to set
	 */
	private final void setPrior(Collection<VisualObject<?>> prior) {
		this.prior = prior;
	}

	private final void addToPrior(VisualObject<?> prior) {
		getPrior().add(prior);
	}

	@Override
	public void clearFocus() {
		this.switchSelectedTo(null);
	}

}