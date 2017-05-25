package gui.form.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import formBuilder.ParameterWrapper;
import logicalobjects.Parameter;

public class ListBoxTest {
	
	@Test
	public void selectedTest1(){
		ListBox<ParameterWrapper> box = new ListBox<ParameterWrapper>(0,0,0,0) {
			@Override
			protected void onAction() {}
		};
		assertEquals(null, box.getSelectedObject());
	}
	
	@Test
	public void selectedTest2(){
		ListBox<ParameterWrapper> box = new ListBox<ParameterWrapper>(0,0,0,0) {
			@Override
			protected void onAction() {}
		};
		ParameterWrapper wrapper = (new ParameterWrapper(new Parameter("naam", "type")));
		box.addElement(wrapper);
		box.switchSelectedElementWith(wrapper);
		assertEquals(wrapper, box.getSelectedObject());
	}
	
	@Test
	public void selectedTest3(){
		ListBox<ParameterWrapper> box = new ListBox<ParameterWrapper>(0,0,0,0) {
			@Override
			protected void onAction() {}
		};
		ParameterWrapper wrapper = (new ParameterWrapper(new Parameter("naam", "type")));
		box.addElement(wrapper);
		assertEquals(wrapper, box.getSelectedObject());
	}
}
