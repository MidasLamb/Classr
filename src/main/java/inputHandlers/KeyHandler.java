package inputHandlers;

import java.awt.event.KeyEvent;

import inputHandler.keys.AsciiKey;
import inputHandler.keys.FunctionKey;
import inputHandler.keys.FunctionKey.FunctionKeyType;

public class KeyHandler {
	private CanvasContent content;

	public KeyHandler(CanvasContent content) {
		setCanvasContent(content);
	}

	public void handleInput(KeyEvent e) {
		if(e.getID()==KeyEvent.KEY_PRESSED && e.getKeyCode() != 0){
			if(keyEventIsAscii(e)){
				getContainer().handleAsciiKey(new AsciiKey(e.getKeyChar()));
			} else {
				FunctionKey key = getFunctionKey(e);
				if(key == null)
					return;
				getContainer().handleFunctionKey(key);
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

	private CanvasContent getContainer() {
		return content;
	}

	private void setCanvasContent(CanvasContent content) {
		this.content = content;
	}

}
