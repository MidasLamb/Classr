package gui.form.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import gui.form.base.CheckBox;
import gui.form.base.Form;
import gui.inputHandlers.clicks.MouseClick;

public class DefaultCheckBoxTest {

	@Test
	public void checkBoxTest1() {
		Form form = new Form(100, 100);
		CheckBox box = new DefaultCheckBox(0, 0);
		form.addFormObject(box);
		form.handleClick(new MouseClick(0, 0));
		assertTrue(box.isChecked());
	}
	
	@Test
	public void checkBoxTest2() {
		Form form = new Form(100, 100);
		CheckBox box = new DefaultCheckBox(0, 0);
		form.addFormObject(box);
		form.handleClick(new MouseClick(50, 50));
		assertFalse(box.isChecked());
	}
	
	@Test
	public void checkBoxTest3() {
		Form form = new Form(100, 100);
		CheckBox box1 = new DefaultCheckBox(0, 0);
		CheckBox box2 = new DefaultCheckBox(10, 10);
		form.addFormObject(box1);
		form.addFormObject(box2);
		form.handleClick(new MouseClick(0, 0));
		assertTrue(box1.isChecked());
		assertFalse(box2.isChecked());
	}
	
	@Test
	public void checkBoxTest5() {
		Form form = new Form(100, 100);
		CheckBox box1 = new DefaultCheckBox(0, 0);
		CheckBox box2 = new DefaultCheckBox(0, 0);
		form.addFormObject(box1);
		form.addFormObject(box2);
		form.handleClick(new MouseClick(0, 0));
		assertTrue(box1.isChecked());
		assertFalse(box2.isChecked());
	}
	
	@Test
	public void checkBoxTest6() {
		Form form = new Form(100, 100);
		CheckBox box1 = new DefaultCheckBox(0, 0);
		CheckBox box2 = new DefaultCheckBox(10, 10);
		form.addFormObject(box1);
		form.addFormObject(box2);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(box1.isChecked());
		assertTrue(box2.isChecked());
	}
	
	@Test
	public void checkBoxTest7() {
		Form form = new Form(100, 100);
		CheckBox box1 = new DefaultCheckBox(0, 0, 1, 1);
		CheckBox box2 = new DefaultCheckBox(10, 10);
		form.addFormObject(box1);
		form.addFormObject(box2);
		form.handleClick(new MouseClick(10, 10));
		assertFalse(box1.isChecked());
		assertTrue(box2.isChecked());
	}
	
	@Test
	public void checkBoxTest8() {
		Form form = new Form(100, 100);
		CheckBox box1 = new DefaultCheckBox(0, 0, 10, 10);
		CheckBox box2 = new DefaultCheckBox(10, 10);
		form.addFormObject(box1);
		form.addFormObject(box2);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(box1.isChecked());
		assertTrue(box2.isChecked());
	}

}
