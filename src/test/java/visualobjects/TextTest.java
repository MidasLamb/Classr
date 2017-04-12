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
		EditableTextWrapper t = new EditableTextWrapper(0, 0, 0,"Standaard", c , r);
		assertEquals("", t.getCurrentDisplayedString());
		t.setSelected(false);
		assertEquals("Standaard", t.getCurrentDisplayedString());
	}

	@Test
	public void typeATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		EditableTextWrapper t = new EditableTextWrapper(0, 0, 0,"", c , r);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("a", t.getCurrentDisplayedString());
	}

	@Test
	public void typeAaTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		EditableTextWrapper t = new EditableTextWrapper(0, 0, 0,"", c , r);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("aa", t.getCurrentDisplayedString());
	}

	@Test
	public void typeAaBackspaceTest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		EditableTextWrapper t = new EditableTextWrapper(0, 0, 0,"", c , r);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleFunctionKey(new FunctionKey(BACKSPACE));
		assertEquals("a", t.getCurrentDisplayedString());
	}	
	
	@Test
	public void typeCapitalATest() {
		Container c = new Container(0, 0, 100, 100);
		LogicalClass r = new LogicalClass();
		EditableTextWrapper t = new EditableTextWrapper(0, 0, 0,"", c , r);
		t.setSelected(true);
		t.handleAsciiKey(new AsciiKey('A'));
		assertEquals("A", t.getCurrentDisplayedString());
	}

}
