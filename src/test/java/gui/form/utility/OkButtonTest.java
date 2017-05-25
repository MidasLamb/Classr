package gui.form.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gui.form.base.Form;
import gui.inputHandlers.clicks.MouseClick;

public class OkButtonTest {

	@Test
	public void OkButtonTest1() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		form.addFormObject(button);
		form.handleClick(new MouseClick(5, 5));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest2() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		form.addFormObject(button);
		form.handleClick(new MouseClick(0, 0));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest3() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		form.addFormObject(button);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest4() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		button.addCheckableConstraint(new RegexCheckedInputBox("aap", ".*", 0, 0, 10, 10));
		form.addFormObject(button);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest5() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		button.addCheckableConstraint(new RegexCheckedInputBox("aap", "10", 0, 0, 10, 10));
		form.addFormObject(button);
		form.handleClick(new MouseClick(10, 10));
		assertFalse(action);
	}

	@Test
	public void OkButtonTest6() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		button.addCheckableConstraint(new RegexCheckedInputBox("10", "10", 0, 0, 10, 10));
		form.addFormObject(button);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest7() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		button.addCheckableConstraint(new RegexCheckedInputBox("10", "10", 0, 0, 10, 10));
		form.addFormObject(button);
		button.checkConstraints();
		form.handleClick(new MouseClick(10, 10));
		assertTrue(action);
	}

	@Test
	public void OkButtonTest8() {
		Form form = new Form(100, 100);
		OkButton button = new OkButton(0, 0, 10, 10) {

			@Override
			protected void onOk() {
				action = true;
			}
		};
		button.addCheckableConstraint(new RegexCheckedInputBox("aap", "10", 0, 0, 10, 10));
		form.addFormObject(button);
		button.checkConstraints();
		form.handleClick(new MouseClick(10, 10));
		assertFalse(action);
	}

	@Before
	public void resetAction() {
		action = false;
	}

	private boolean action;
}
