package gui.form.utility;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import gui.form.base.Form;
import gui.inputHandlers.clicks.MouseClick;

public class OkButtonTest {
	
	@Test
	public void OkButtonTest1() {
		Form form = new Form(100,100);
		OkButton button = new OkButton(0,0,10,10) {
			
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
		Form form = new Form(100,100);
		OkButton button = new OkButton(0,0,10,10) {
			
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
		Form form = new Form(100,100);
		OkButton button = new OkButton(0,0,10,10) {
			
			@Override
			protected void onOk() {
				action = true;
			}
		};
		form.addFormObject(button);
		form.handleClick(new MouseClick(10, 10));
		assertTrue(action);
	}


	@Before
	public void resetAction(){
		action = false;
	}
	
	private boolean action;
}
