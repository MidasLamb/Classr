package barBuilder;

import gui.form.base.MenuBar;
import gui.form.base.MenuHeader;
import gui.form.base.MenuItem;
import visualobjects.Backend;

/**
 * A class that builds a MenuBar
 */
public class MenuBarBuilder extends BarBuilder<MenuBar> {
	
	private final int width, height;
	
	/**
	 * Constructs a MenuBarBuilder with the stated width and height
	 * @param width
	 * @param height
	 */
	public MenuBarBuilder(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	protected void buildBar() {
		int defaultHeight = this.getHeight();
		int x = 0;
		int y = 30;
		int defaultWidth = 100;
		int newPosX = x;
		
		this.setMenuBar(new MenuBar(x, y, this.getWidth(), defaultHeight));
		this.addMenuHeader(new MenuHeader("Create/Add", x, y, defaultWidth, defaultHeight));
		this.addMenuItemToLastAddedHeader(new MenuItem("Create Class", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.createClass();
			}
		});

		this.addMenuItemToLastAddedHeader(new MenuItem("Add Attribute", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.addAttribute();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canAddAttribute();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Add Method", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.addMethod();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canAddMethod();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Add Parameter", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.addParameter();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canAddParameter();
			}
		});
		
		newPosX += defaultWidth;
		
		// EDIT menu header
		this.addMenuHeader(new MenuHeader("Edit", newPosX, y, defaultWidth, defaultHeight));
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Edit Name", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.editName();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canEditName();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Edit...", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.editTripleDot();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canEditTripleDot();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Delete", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.delete();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canDelete();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Undo", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.undo();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canUndo();
			}
		});
		
		this.addMenuItemToLastAddedHeader(new MenuItem("Redo", defaultWidth) {
			@Override
			protected void onAction() {
				Backend.redo();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canRedo();
			}
		});

	}

	/**
	 * @return the width
	 */
	private final int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	private final int getHeight() {
		return height;
	}
	
	
}
