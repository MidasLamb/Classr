package gui.utility;

import java.awt.event.KeyEvent;

import gui.base.InputBox;

public abstract class RegexCheckedInputBox extends InputBox {
	private String regex;

	public RegexCheckedInputBox(String regex,int x, int y, int width, int height) {
		super(x, y, width, height);
		this.regex = regex;
	}

	@Override
	protected void onAction() {
		
	}
	
	@Override
	public void handleKeyEvent(KeyEvent e) {
		super.handleKeyEvent(e);
	}

}
