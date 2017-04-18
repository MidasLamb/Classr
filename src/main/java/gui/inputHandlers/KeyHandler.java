package gui.inputHandlers;

import java.awt.event.KeyEvent;

import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.inputHandlers.keys.FunctionKey.FunctionKeyType;

public class KeyHandler {
	private Typable content;

	public KeyHandler(Typable content) {
		setTypable(content);
	}

	public void handleInput(KeyEvent e) {
		if(e.getID()==KeyEvent.KEY_PRESSED && e.getKeyCode() != 0){
			if(keyEventIsAscii(e)){
				getTypable().handleAsciiKey(new AsciiKey(e.getKeyChar()));
			} else {
				FunctionKey key = getFunctionKey(e);
				if(key == null)
					return;
				getTypable().handleFunctionKey(key);
			}
		}
	}
	
	 static FunctionKey getFunctionKey(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			return new FunctionKey(FunctionKeyType.BACKSPACE);
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE){
			return new FunctionKey(FunctionKeyType.DELETE);
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			return new FunctionKey(FunctionKeyType.ESCAPE);
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			return new FunctionKey(FunctionKeyType.ENTER);
		}
		return null;
	}
	
	static boolean keyEventIsAscii(KeyEvent e){
		char c = e.getKeyChar();
		return !e.isActionKey() && c != KeyEvent.CHAR_UNDEFINED 
				&& (isAscii(c) || e.getKeyCode() == KeyEvent.VK_SPACE);
	}
	
	private static boolean isAscii(char c){
		return new String(new char[] {c}).matches("[A-Z]|[0-9]|[a-z]|_");
		
	}

	private Typable getTypable() {
		return content;
	}

	private void setTypable(Typable content) {
		this.content = content;
	}

}
