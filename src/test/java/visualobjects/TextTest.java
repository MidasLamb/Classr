package visualobjects;

import static org.junit.Assert.*;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import static inputHandler.keys.FunctionKey.FunctionKeyType.*;

import org.junit.Test;

import inputHandler.keys.AsciiKey;
import inputHandler.keys.FunctionKey;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;

public class TextTest {

	@Test
	public void standardTextTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		assertEquals("Standaard", t.getText());
	}

	@Test
	public void typeATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("a", t.getText());
	}

	@Test
	public void typeAaTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("aa", t.getText());
	}

	@Test
	public void typeAaBackspaceTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleFunctionKey(new FunctionKey(BACKSPACE));
		assertEquals("a", t.getText());
	}	
	
	@Test
	public void typeCapitalATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('A'));
		assertTrue(t.getText().equals("A"));
	}

}
