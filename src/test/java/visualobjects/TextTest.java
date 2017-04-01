package visualobjects;

import static org.junit.Assert.*;

import java.awt.Canvas;
import java.awt.event.KeyEvent;

import org.junit.Test;

import logicalobjects.Attribute;
import logicalobjects.LogicalClass;

public class TextTest {

	@Test
	public void standardTextTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		assertTrue(t.getText().equals("Standaard"));
	}

	@Test
	public void typeATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		Canvas canvas = new Canvas();

		t.setSelected(true);

		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When
																	// timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'a');

		t.handleKeyEvent(ke);

		assertTrue(t.getText().equals("a"));

	}

	@Test
	public void typeAaTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		Canvas canvas = new Canvas();

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		t.setSelected(true);

		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When
																	// timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'a');

		t.handleKeyEvent(ke);

		ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'a');

		t.handleKeyEvent(ke);

		assertTrue(t.getText().equals("aa"));
	}

	@Test
	public void typeAaBackspaceTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		Canvas canvas = new Canvas();

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		t.setSelected(true);

		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When
																	// timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'a');

		t.handleKeyEvent(ke);

		ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'a');

		t.handleKeyEvent(ke);

		ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When timeStamp
				0, // Modifier
				KeyEvent.VK_BACK_SPACE, // Key Code
				'\b');

		t.handleKeyEvent(ke);

		assertTrue(t.getText().equals("a"));

	}

	@Test
	public void typeShiftTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		Canvas canvas = new Canvas();

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		t.setSelected(true);

		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When
																	// timeStamp
				0, // Modifier
				KeyEvent.VK_SHIFT, // Key Code
				Character.MIN_VALUE);

		t.handleKeyEvent(ke);

		assertTrue(t.getText().equals(""));
		
	}
	
	
	@Test
	public void typeCapitalATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		Canvas canvas = new Canvas();

		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);

		t.setSelected(true);
		
		KeyEvent ke = new KeyEvent(canvas, KeyEvent.KEY_PRESSED, 0, // When timeStamp
				0, // Modifier
				KeyEvent.VK_A, // Key Code
				'A');

		t.handleKeyEvent(ke);

		assertTrue(t.getText().equals("A"));
	}

}
