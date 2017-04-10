package visualobjects;

import static inputHandlers.keys.FunctionKey.FunctionKeyType.BACKSPACE;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;

public class TextTest {

	@Test
	public void standardTextTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		assertEquals("Standaard", t.getString());
	}

	@Test
	public void typeATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("a", t.getString());
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
		assertEquals("aa", t.getString());
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
		assertEquals("a", t.getString());
	}	
	
	@Test
	public void typeCapitalATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		Attribute a = new Attribute(r);
		EditableText t = new EditableText(0, 0, 0, c, "Standaard", a);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('A'));
		assertEquals("A", t.getString());
	}

}
