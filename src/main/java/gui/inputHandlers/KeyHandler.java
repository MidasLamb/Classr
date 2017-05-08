package gui.inputHandlers;

import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.BACKSPACE;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.DELETE;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.DOWN;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.ENTER;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.ESCAPE;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.LEFT;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.RIGHT;
import static gui.inputHandlers.keys.FunctionKey.FunctionKeyType.*;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;

/**
 * Handles the input keystrokes and converts them Key objects.
 */
public class KeyHandler {
	private Typable content;

	/**
	 * Create a new KeyHandler and set its Typable.
	 * 
	 * @param content
	 *            Typable to which the keystrokes are passed
	 */
	public KeyHandler(Typable content) {
		setTypable(content);
	}

	/**
	 * @param e
	 */
	public void handleInput(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() != 0) {
			if (keyEventIsAscii(e)) {
				getTypable().handleAsciiKey(new AsciiKey(e.getKeyChar()));
			} else {
				FunctionKey key = getFunctionKey(e);
				if (key == null)
					return;
				getTypable().handleFunctionKey(key);
			}
		}
	}

	static FunctionKey getFunctionKey(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			return new FunctionKey(BACKSPACE);
		case KeyEvent.VK_DELETE:
			return new FunctionKey(DELETE);
		case KeyEvent.VK_ESCAPE:
			return new FunctionKey(ESCAPE);
		case KeyEvent.VK_ENTER:
			return new FunctionKey(ENTER);
		case KeyEvent.VK_RIGHT:
			return new FunctionKey(RIGHT);
		case KeyEvent.VK_LEFT:
			return new FunctionKey(LEFT);
		case KeyEvent.VK_UP:
			return new FunctionKey(UP);
		case KeyEvent.VK_DOWN:
			return new FunctionKey(DOWN);
		case 90:
			if(InputEvent.CTRL_MASK == e.getModifiers())
				return new FunctionKey(CTRL_Z);
		default:
			return null;
		}
	}

	static boolean keyEventIsAscii(KeyEvent e) {
		char c = e.getKeyChar();
		return !e.isActionKey() && c != KeyEvent.CHAR_UNDEFINED && (isAscii(c) || e.getKeyCode() == KeyEvent.VK_SPACE);
	}

	private static boolean isAscii(char c) {
		return new String(new char[] { c }).matches("[A-Z]|[0-9]|[a-z]|_");

	}

	private Typable getTypable() {
		return content;
	}

	private void setTypable(Typable content) {
		this.content = content;
	}

}
