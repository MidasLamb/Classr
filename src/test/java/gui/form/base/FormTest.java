package gui.form.base;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.form.utility.DefaultCheckBox;
import gui.inputHandlers.keys.FunctionKey;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.*;

public class FormTest {

	@Test
	public void navigateTest1() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(DOWN));
		assertTrue(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest2() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(DOWN));
		assertFalse(box.isFocused());
	}
	
	@Test
	public void navigateTest3() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(DOWN));
		form.handleFunctionKey(new FunctionKey(DOWN));
		assertTrue(box.isFocused());
	}
	
	@Test
	public void navigateTest4() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(DOWN));
		form.handleFunctionKey(new FunctionKey(DOWN));
		assertFalse(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest5() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		assertTrue(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest6() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		assertFalse(box.isFocused());
	}
	
	@Test
	public void navigateTest7() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(RIGHT));
		assertTrue(box.isFocused());
	}
	
	@Test
	public void navigateTest8() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(RIGHT));
		assertFalse(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest9() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(LEFT));
		assertTrue(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest10() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(LEFT));
		assertFalse(box.isFocused());
	}
	
	@Test
	public void navigateTest11() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(UP));
		assertTrue(inputBox.isFocused());
	}
	
	@Test
	public void navigateTest12() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(DOWN));
		assertTrue(box.isFocused());
	}
	
	@Test
	public void navigateTest13() {
		CheckBox box = new DefaultCheckBox(10,10);
		InputBox inputBox = new InputBox(0,0,5,5) {
			@Override
			protected void onAction() {}
		};
		Form form = new Form(0, 0);
		form.addFormObject(box);
		form.addFormObject(inputBox);
		form.handleFunctionKey(new FunctionKey(RIGHT));
		form.handleFunctionKey(new FunctionKey(DOWN));
		form.handleFunctionKey(new FunctionKey(RIGHT));
		assertTrue(box.isFocused());
	}

}
