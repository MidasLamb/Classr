package gui.text.state;

import java.awt.Graphics;

import gui.text.Text;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public class PassiveState extends TextState {

	@Override
	public void handleAsciiKey(AsciiKey key) {
		
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {

	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawString(getText().getText(), x, y + g.getFontMetrics().getAscent());
	}

}
