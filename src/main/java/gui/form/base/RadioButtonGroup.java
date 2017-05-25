package gui.form.base;

/**
 * A class of RadioButtonGroups, keeping track of the selected RadioButton
 */
public class RadioButtonGroup {

	private RadioButton selectedButton;

	/**
	 * Called when a RadioButton of this RadioButtonGroup is clicked. The
	 * clicked RadioButton is set as the selected RadioButton of this
	 * RadioButtonGroup.
	 * 
	 * @param button
	 *            The button which to set to be clicked.
	 */
	void radioButtonIsClicked(RadioButton button) {
		setSelectedButton(button);
	}

	/**
	 * @return the selected RadioButton
	 */
	public RadioButton getSelectedButton() {
		return selectedButton;
	}

	/**
	 * Set the selected RadioButton of this RadioButtonGroup
	 * 
	 * @param selectedButton
	 *            the RadioButton to set as the selected RadioButton of this
	 *            RadioButtonGroup
	 */
	public void setSelectedButton(RadioButton selectedButton) {
		if (getSelectedButton() != null)
			getSelectedButton().setSelected(false);
		selectedButton.setSelected(true);
		this.selectedButton = selectedButton;
	}

}
