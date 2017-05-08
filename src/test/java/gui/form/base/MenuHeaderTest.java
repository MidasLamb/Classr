package gui.form.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuHeaderTest {

	@Test
	public void addMenuItemSizeTest() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item);
		assertEquals(1, header.getMenuItems().size());
	}

	@Test
	public void addTwoMenuItemsSizeTest() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		assertEquals(2, header.getMenuItems().size());
	}

	@Test
	public void addAndRemoveTwoMenuItemsSizeTest() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item1);
		header.deleteMenuItem(item2);
		assertEquals(0, header.getMenuItems().size());
	}

	@Test
	public void addTwoAndRemoveFirstMenuItemSizeTest() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item1);
		assertEquals(1, header.getMenuItems().size());
	}

	@Test
	public void addTwoAndRemoveSecondMenuItemSizeTest() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item2);
		assertEquals(1, header.getMenuItems().size());
	}

	@Test
	public void addTwoIdenticalMenuItemsDeleteTest1() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item2);
		assertEquals(item1, header.getMenuItems().get(0));
	}

	@Test
	public void addTwoIdenticalMenuItemsDeleteTest2() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item1);
		assertEquals(item2, header.getMenuItems().get(0));
	}

	@Test
	public void addTwoNonIdenticalMenuItemsDeleteTest1() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 1);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item2);
		assertEquals(item1, header.getMenuItems().get(0));
	}

	@Test
	public void addTwoNonIdenticalMenuItemsDeleteTest2() {
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item1 = new MenuItem(0, 0, 0, 0);
		MenuItem item2 = new MenuItem(0, 0, 0, 1);
		header.addMenuItem(item1);
		header.addMenuItem(item2);
		header.deleteMenuItem(item1);
		assertEquals(item2, header.getMenuItems().get(0));
	}
	
	@Test
	public void AddMenuItemCheckParentMenuHeaderTest(){
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		MenuItem item = new MenuItem(0, 0, 0, 0);
		header.addMenuItem(item);
		assertEquals(header, item.getMenuHeader());
	}

}
