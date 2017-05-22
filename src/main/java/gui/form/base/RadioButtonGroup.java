package gui.form.base;

/**
 * A class of RadioButtonGroups, keeping track of the selected RadioButton
 */
public class RadioButtonGroup {

	private RadioButton selectedButton;

	void radioButtonIsClicked(RadioButton button) {
		setSelectedButton(button);
	}

	public RadioButton getSelectedButton() {
		return selectedButton;
	}

	public void setSelectedButton(RadioButton selectedButton) {
		if (getSelectedButton() != null)
			getSelectedButton().setSelected(false);
		selectedButton.setSelected(true);
		this.selectedButton = selectedButton;
	}

}
