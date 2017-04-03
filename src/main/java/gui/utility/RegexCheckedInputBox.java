package gui.utility;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gui.base.InputBox;

public abstract class RegexCheckedInputBox extends InputBox implements Checkable {
	private String regex;

	public RegexCheckedInputBox(String text, String regex, int x, int y, int width, int height) {
		super(text, x, y, width, height);
		this.regex = regex;
	}

	@Override
	protected void onAction() {

	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		super.handleKeyEvent(e);
	}

	@Override
	public boolean check() {
		return super.getText().matches(this.regex);
	}

	@Override
	protected void draw(Graphics g) {
		Color prev = g.getColor();
		if (!this.check())
			g.setColor(Color.RED);
		super.draw(g);
		g.setColor(prev);
	}

}
