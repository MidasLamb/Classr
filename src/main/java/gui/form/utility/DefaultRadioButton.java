package gui.form.utility;

import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;

/**
 * Default RadioButton that can be added to a Form.
 */
public class DefaultRadioButton extends RadioButton {

	/**
	 * Construct a new DefaulRadioButton and set its RadioButtonGroup and
	 * coordinates. Uses the default RadioButton dimensions.
	 * 
	 * @param group
	 *            the RadioButtonGroup to which this DefaultRadioButton belongs
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public DefaultRadioButton(RadioButtonGroup group, int x, int y) {
		super(group, x, y);
	}

	/**
	 * Empty onAction() method.
	 */
	@Override
	protected void onAction() {

	}

}
