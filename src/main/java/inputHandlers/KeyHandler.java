package inputHandlers;

import java.awt.event.KeyEvent;

import visualobjects.Container;

public class KeyHandler {
	private CanvasContent content;

	public KeyHandler(CanvasContent content) {
		setCanvasContent(content);
	}

	public void handleInput(KeyEvent e) {
		if (e.getKeyCode() == 0)
			return;
		this.getContainer().handleKeyEvent(e);
	}

	private CanvasContent getContainer() {
		return content;
	}

	private void setCanvasContent(CanvasContent content) {
		this.content = content;
	}

}
