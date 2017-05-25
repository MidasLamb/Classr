package gui.form.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.inputHandlers.clicks.MouseClick;

public class DefaultRadioButtonTest {

	@Test
	public void selectedTest1() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		assertTrue(button1.isSelected());
	}

	@Test
	public void selectedTest2() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		RadioButton button2 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		assertFalse(button2.isSelected());
	}

	@Test
	public void selectedTest3() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		RadioButton button2 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		button2.onClick(new MouseClick(0, 0));
		assertTrue(button2.isSelected());
	}

	@Test
	public void selectedTest4() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		RadioButton button2 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		button2.onClick(new MouseClick(0, 0));
		assertFalse(button1.isSelected());
	}

	@Test
	public void selectedTest6() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		RadioButton button2 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		button2.onClick(new MouseClick(0, 0));
		button1.onClick(new MouseClick(0, 0));
		assertTrue(button1.isSelected());
	}

	@Test
	public void selectedTest7() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button1 = new DefaultRadioButton(group, 0, 0);
		RadioButton button2 = new DefaultRadioButton(group, 0, 0);
		button1.onClick(new MouseClick(0, 0));
		button2.onClick(new MouseClick(0, 0));
		button1.onClick(new MouseClick(0, 0));
		assertFalse(button2.isSelected());
	}

}
