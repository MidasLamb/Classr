package barBuilder;

import gui.form.base.MenuBar;
import gui.form.base.MenuHeader;
import visualobjects.Backend;

/**
 * A class that builds a ToolBar
 */
public class ToolBarBuilder extends BarBuilder<MenuBar> {

	private final int width, height;

	/**
	 * Constructs a ToolBar with the stated width and height
	 * 
	 * @param width
	 * @param height
	 */
	public ToolBarBuilder(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	void buildBar() {
		int defaultHeight = this.getHeight();
		int x = 0;
		int y = 0;
		int defaultWidth = 100;
		int newPosX = x;

		this.setMenuBar(new MenuBar(x, y, this.getWidth(), defaultHeight));
		this.addMenuHeader(new MenuHeader("Create Class", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.createClass();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Add Attribute", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.addAttribute();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canAddAttribute();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Add Method", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.addMethod();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canAddMethod();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Add Parameter", newPosX, y, defaultWidth, defaultHeight) {
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

		this.addMenuHeader(new MenuHeader("Edit Name", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.editName();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canEditName();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Edit...", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.editTripleDot();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canEditTripleDot();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Delete", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.delete();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canDelete();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Undo", newPosX, y, defaultWidth, defaultHeight) {
			@Override
			protected void onAction() {
				Backend.undo();
			}

			@Override
			protected boolean canBeEnabled() {
				return Backend.canUndo();
			}
		});

		newPosX += defaultWidth;

		this.addMenuHeader(new MenuHeader("Redo", newPosX, y, defaultWidth, defaultHeight) {
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
