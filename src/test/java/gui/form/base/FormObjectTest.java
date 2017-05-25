package gui.form.base;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import gui.inputHandlers.clicks.MouseClick;

public class FormObjectTest {

	@Test
	public void receiveClickTest1() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(0, 0));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest2() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(15, 0));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest3() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {
			}
		};
		dummy.handleClick(new MouseClick(0, 25));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest4() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {
			}
		};
		dummy.handleClick(new MouseClick(15, 25));
		assertTrue(clicked);
	}

	@Test
	public void receiveClickTest5() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(10, 20));
		assertTrue(clicked);
	}

	@Test
	public void receiveClickTest7() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(40, 60));
		assertTrue(clicked);
	}

	@Test
	public void receiveClickTest8() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(40, 61));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest9() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(41, 60));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest10() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(50, 70));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest11() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(15, 70));
		assertFalse(clicked);
	}

	@Test
	public void receiveClickTest12() {
		FormObject dummy = new FormObject(10, 20, 30, 40) {

			@Override
			public void onClick(MouseClick click) {
				clicked = true;

			}

			@Override
			public void draw(Graphics g) {
			}

			@Override
			protected void onAction() {

			}
		};
		dummy.handleClick(new MouseClick(60, 30));
		assertFalse(clicked);
	}

	@Before
	public void setClickedFalse() {
		this.clicked = false;
	}

	private boolean clicked = false;

}
