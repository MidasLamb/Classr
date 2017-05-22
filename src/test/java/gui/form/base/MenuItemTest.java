package gui.form.base;

import gui.form.base.MenuItem;
import visualobjects.Container;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import canvaswindow.MyCanvasWindow;


public class MenuItemTest {

	
	
	@Test
	public void enabledTest(){
		MenuItem item = new MenuItem("testItem", 100, 0) {
			@Override
			protected boolean canBeEnabled() {
				return true;
			}
			
			@Override
			protected void onAction() {
			}
		};
		assertEquals(item.canBeEnabled(),true);
	}
	
	@Test
	public void disabledTest(){
		MenuItem item = new MenuItem("testItem", 100, 0) {
			@Override
			protected boolean canBeEnabled() {
				return false;
			}
			
			@Override
			protected void onAction() {
			}
		};
		assertEquals(item.canBeEnabled(),false);
	}
	
	
	/*@Test
	public void undoEnabledAfterFirstClassCreationTest(){
		MyCanvasWindow mcw = new MyCanvasWindow("testWindow");
		Container container = new Container(0,0,900,800,mcw);
		
	}*/

}
