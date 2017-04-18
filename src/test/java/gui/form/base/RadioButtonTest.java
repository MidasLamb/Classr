package gui.form.base;

import static gui.form.base.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gui.form.base.RadioButton;
import gui.form.base.RadioButtonGroup;
import gui.inputHandlers.clicks.MouseClick;

public class RadioButtonTest {

	@Test
	public void actionTest1() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0, 10, 10) {
			@Override
			public void onAction() {
				action = true;
			}
		};
		assertFalse(action);
	}
	
	@Test
	public void actionTest2() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0, 10, 10) {
			@Override
			public void onAction() {
				action = true;
			}
		};
		button.handleClick(new MouseClick(0, 0));
		assertTrue(action);
	}
	
	@Test
	public void constructorTest1() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0) {
			@Override
			public void onAction() {}
		};
		assertEquals(STANDARD_RADIOBUTTON_WIDTH, button.getWidth());
	}
	
	@Test
	public void constructorTest2() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0) {
			@Override
			public void onAction() {}
		};
		assertEquals(STANDARD_RADIOBUTTON_HEIGHT, button.getHeight());
	}
	
	@Test
	public void selectedTest1() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0) {
			@Override
			public void onAction() {}
		};
		assertFalse(button.isSelected());
	}
	
	@Test
	public void selectedTest2() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0) {
			@Override
			public void onAction() {}
		};
		button.setSelected(true);
		assertTrue(button.isSelected());
	}
	
	@Test
	public void selectedTest3() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0) {
			@Override
			public void onAction() {}
		};
		button.setSelected(false);
		assertFalse(button.isSelected());
	}
	
	@Test
	public void selectedTest4() {
		RadioButtonGroup group = new RadioButtonGroup();
		RadioButton button = new RadioButton(group, 0, 0, 10, 10) {
			@Override
			public void onAction() {}
		};
		button.handleClick(new MouseClick(0, 0));
		assertTrue(button.isSelected());
	}
	
	@Before
	public void resetAction(){
		action = false;
	}
	
	private boolean action;

}
