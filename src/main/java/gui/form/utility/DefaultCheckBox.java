package gui.form.utility;

import gui.form.base.CheckBox;

/**
 * Default CheckBox that can be added to a Form.
 */
public class DefaultCheckBox extends CheckBox {

	/**
	 * Construct a new DefaultCheckBox given its coordinates and dimensions.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            width
	 * @param height
	 *            height
	 */
	public DefaultCheckBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Construct a new DefaultCheckBox given its coordinates and use the default
	 * dimensions.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public DefaultCheckBox(int x, int y) {
		super(x, y);
	}

	/*
	 * Empty onAction() method.
	 */
	@Override
	protected void onAction() {

	}

}
