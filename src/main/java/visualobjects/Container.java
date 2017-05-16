package visualobjects;

import static main.Constants.CLASS_WIDTH;
import static main.Constants.Z_CLASS;

import canvaswindow.MyCanvasWindow;
import command.Controller;
import command.CreateClassCommand;
import gui.form.base.DropDownMenu;
import gui.form.base.MenuBar;
import gui.form.base.MenuHeader;
import gui.form.base.MenuItem;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;
import interfaces.CanvasContent;

public class Container extends VisualObject implements CanvasContent{
	private VisualObject selected;
	private MyCanvasWindow window;
	private MenuBar toolbar;

	/**
	 * 
	 * @param 	x
	 * 			The x-coordinate of the container
	 * @param 	y
	 * 			The y-coordinate of the container
	 * @param 	width
	 * 			the width of the container
	 * @param 	height
	 * 			the height of the container
	 * @param 	window
	 * 			the canvas window in this container
	 */
	public Container(int x, int y, int width, int height, MyCanvasWindow window) {
		super(x, y, Integer.MIN_VALUE, width, height, null, new Controller());
		this.setCanvasWindow(window);
		
		BarBackend.initialize(this, getController());
		this.createToolBar();
		this.createMenuBar();
	}
	
	private void createMenuBar(){
		MenuBar menu = new MenuBar(0,30,500, 30);
		MenuHeader header = new MenuHeader("testheader", 0, 30, 100, 30);
		for (int i = 0; i < 15;i++){
			String name = "testitem"+i;
			MenuItem item = new MenuItem(name,100,0){
				@Override
				protected void onAction() {
					
					System.out.println(name);
				}
				
			};
			header.getDropDownMenu().addMenuItem(item);
		}
				
		menu.addMenuHeader(header);
		new FormObjectWrapper<MenuBar>(menu, 0,30, 0, 500,30, this, getController());
		
		DropDownMenu<MenuItem> dd = header.getDropDownMenu();
		new FormObjectWrapper<DropDownMenu<MenuItem>>(dd,0,60,0,100,238, this, getController());
		
	}
	
	private void createToolBar(){
		int defaultHeight = 30;
		int x = 0;
		int y = 0;
		int defaultWidth = 100;
		int newPosX = x;
		
		MenuBar toolbar = new MenuBar(x, y, this.getWidth(), defaultHeight);
		this.setToolbar(toolbar);
		
		toolbar.addMenuHeader(new MenuHeader("Create Class", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.createClass();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Add Attribute", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.addAttribute();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canAddAttribute();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Add Method", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.addMethod();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canAddMethod();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Add Parameter", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.addParameter();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canAddParameter();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Edit Name", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.editName();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canEditName();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Edit...", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.editTripleDot();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canEditTripleDot();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Delete", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.delete();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canDelete();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Undo", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.undo();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canUndo();
			}
		});
		
		newPosX += defaultWidth;
		
		toolbar.addMenuHeader(new MenuHeader("Redo", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				BarBackend.redo();
			}
			
			@Override
			protected boolean canBeEnabled() {
				return BarBackend.canRedo();
			}
		});
		
		new FormObjectWrapper<MenuBar>(toolbar, x, y, 0, 100000, defaultHeight, this, getController());
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
	 * @return the created visualClass
	 */
	public VisualClass createNewClass(int x, int y) {
		return new VisualClass(x, y, Z_CLASS, this, getController());
	}
	
	/**
	 * Creates a new visual class in this container when no location is given
	 *  adds the class to the children makes the text item of the class the selected item
	 * The location for this class will be an empty location in the container
	 * If there is no empty location, no class will be created
	 * 
	 * @return the created visualClass, null if there is no empty space
	 */
	public VisualClass createNewClass(){
		try{
			int[] coordinates = findEmptyPosition();
			return createNewClass(coordinates[0], coordinates[1]);
		} catch (IllegalStateException e){
			return null;
		}
	}
	
	/**
	 * Finds an empty position in the container
	 * @return	coordinates [x,y] if there exists a position
	 * @throws 	IllegalStateException
	 * 			if there is no empty position
	 */
	private int[] findEmptyPosition() throws IllegalStateException{
		int startPos = 10;
		int x = startPos;
		int y = startPos;
		while(isChildIn(x, y)){
			if(x+CLASS_WIDTH > getWidth()){
				x = startPos;
				y++;
			} else {
				x++;
			}
			if(y > getHeight()) throw new IllegalStateException();
		}
		return new int[]{x,y};
	}
	
	/**
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @return	True if there is a child in the given coordinates,
	 * 				otherwise false
	 */
	private boolean isChildIn(int x, int y){
		return getChildren().stream().anyMatch(child -> child.isIn(x, y));
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		if (this.select(dc.getX(), dc.getY()).equals(this)) {
			// Double click on empty
			getController().executeCommand(new CreateClassCommand(this,dc.getX(), dc.getY()));
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

	/**
	 * @return the canvasWindow
	 */
	MyCanvasWindow getCanvasWindow() {
		return window;
	}

	/**
	 * @param 	window
	 * 			the canvas window
	 */
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
		if(key.getKeyType() == FunctionKeyType.CTRL_Z)
			getController().undo();
		if(key.getKeyType() == FunctionKeyType.CTRL_Y)
			getController().redo();
		if(getSelected() != null)
			getSelected().handleFunctionKey(key);
	}

	/**
	 * @return the toolbar
	 */
	private final MenuBar getToolbar() {
		return toolbar;
	}

	/**
	 * @param toolbar the toolbar to set
	 */
	private final void setToolbar(MenuBar toolbar) {
		this.toolbar = toolbar;
	}
	
	@Override
	public int getWidth() {
		return super.getWidth();
	}
	
	@Override
	public int getHeight() {
		return super.getHeight();
	}

}
