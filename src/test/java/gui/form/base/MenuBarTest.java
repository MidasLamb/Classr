package gui.form.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MenuBarTest {

	@Test
	public void addMenuHeaderSizeTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header);
		assertEquals(1, bar.getMenuHeaders().size());
	}

	@Test
	public void addTwoMenuHeadersSizeTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		assertEquals(2, bar.getMenuHeaders().size());
	}

	@Test
	public void addAndRemoveTwoMenuHeadersSizeTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header1);
		bar.deleteMenuHeader(header2);
		assertEquals(0, bar.getMenuHeaders().size());
	}

	@Test
	public void addAndRemoveFirstHeadersSizeTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header1);
		assertEquals(1, bar.getMenuHeaders().size());
	}

	@Test
	public void addAndRemoveSecondHeadersSizeTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header2);
		assertEquals(1, bar.getMenuHeaders().size());
	}

	@Test
	public void addTwoIdenticalMenuHeadersTest1() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header2);
		assertEquals(header1, bar.getMenuHeaders().get(0));
	}

	@Test
	public void addTwoIdenticalMenuHeadersTest2() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header1);
		assertEquals(header2, bar.getMenuHeaders().get(0));
	}

	@Test
	public void addTwoNonIdenticalMenuHeadersTest1() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 1);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header2);
		assertEquals(header1, bar.getMenuHeaders().get(0));
	}

	@Test
	public void addTwoNonIdenticalMenuHeadersTest2() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header1 = new MenuHeader(0, 0, 0, 0);
		MenuHeader header2 = new MenuHeader(0, 0, 0, 2);
		bar.addMenuHeader(header1);
		bar.addMenuHeader(header2);
		bar.deleteMenuHeader(header1);
		assertEquals(header2, bar.getMenuHeaders().get(0));
	}

	@Test
	public void addMenuHeaderCheckParentMenuBarTest() {
		MenuBar bar = new MenuBar(0, 0, 0, 0);
		MenuHeader header = new MenuHeader(0, 0, 0, 0);
		bar.addMenuHeader(header);
		assertEquals(bar, header.getMenuBar());
	}

}
