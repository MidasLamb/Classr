package inputHandlers;

import java.awt.event.KeyEvent;

import visualobjects.Container;

public class KeyHandler {
	
	public KeyHandler(Container container){
		setContainer(container);
	}
	
	public void handleInput(KeyEvent e){
		if (e.getKeyCode() == 0)
			return;
		this.getContainer().sendKeyToSelected(e);
	}
	
	private Container getContainer() {
		return container;
	}

	private void setContainer(Container container) {
		this.container = container;
	}
	
	private Container container;
}
