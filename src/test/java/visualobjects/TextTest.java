package visualobjects;

import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.BACKSPACE;
import static org.junit.Assert.assertEquals;
import static main.Constants.*;

import org.junit.Test;

import canvaswindow.MyCanvasWindow;
import command.Controller;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import logicalobjects.LogicalClass;

public class TextTest {

	@Test
	public void standardTextTest() {
		Container c = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		LogicalClass r = new LogicalClass();
		EditableTextWrapper<LogicalClass> t = new EditableTextWrapper<LogicalClass>(0, 0, 0, c , r, new Controller());
		assertEquals("", t.getCurrentDisplayedString());
		t.setSelected(false);
		assertEquals(DEFAULT_CLASS_NAME, t.getCurrentDisplayedString());
	}

	@Test
	public void typeATest() {
		Container c = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		LogicalClass r = new LogicalClass();
		EditableTextWrapper<LogicalClass> t = new EditableTextWrapper<LogicalClass>(0, 0, 0,c , r, new Controller());
		t.setSelected(true);
		t.setEditable();
		while(!t.getCurrentDisplayedString().equals("")) {
			t.handleFunctionKey(new FunctionKey(BACKSPACE));
		}		
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("a", t.getCurrentDisplayedString());
	}

	@Test
	public void typeAaTest() {
		Container c = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		LogicalClass r = new LogicalClass();
		EditableTextWrapper<LogicalClass> t = new EditableTextWrapper<LogicalClass>(0, 0, 0, c , r, new Controller());
		t.setSelected(true);
		t.setEditable();
		while(!t.getCurrentDisplayedString().equals("")) {
			t.handleFunctionKey(new FunctionKey(BACKSPACE));
		}
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		assertEquals("aa", t.getCurrentDisplayedString());
	}

	@Test
	public void typeAaBackspaceTest() {
		Container c = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		LogicalClass r = new LogicalClass();
		EditableTextWrapper<LogicalClass> t = new EditableTextWrapper<LogicalClass>(0, 0, 0,c , r, new Controller());
		t.setSelected(true);
		t.setEditable();
		while(!t.getCurrentDisplayedString().equals("")) {
			t.handleFunctionKey(new FunctionKey(BACKSPACE));
		}
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleAsciiKey(new AsciiKey('a'));
		t.handleFunctionKey(new FunctionKey(BACKSPACE));
		assertEquals("a", t.getCurrentDisplayedString());
	}	
	
	@Test
	public void typeCapitalATest() {
		Container c = new Container(0, 0, 100, 100, new MyCanvasWindow("test"));
		LogicalClass r = new LogicalClass();
		EditableTextWrapper<LogicalClass> t = new EditableTextWrapper<LogicalClass>(0, 0, 0,c , r, new Controller());
		t.setSelected(true);
		t.setEditable();
		while(!t.getCurrentDisplayedString().equals("")) {
			t.handleFunctionKey(new FunctionKey(BACKSPACE));
		}
		t.handleAsciiKey(new AsciiKey('A'));
		assertEquals("A", t.getCurrentDisplayedString());
	}

}
