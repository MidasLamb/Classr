package gui.form.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class MenuItemTest {

	
	
	@Test
	public void enabledTest(){
		MenuItem item = new MenuItem("testItem", 100) {
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
		MenuItem item = new MenuItem("testItem", 100) {
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
