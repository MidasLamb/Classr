package gui.text.state;

import java.awt.Graphics;

import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;

/**
 * A state for the Text object in which it is not editable
 */
public class PassiveState extends TextState {

	@Override
	public void handleAsciiKey(AsciiKey key) {
		
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {

	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if (getText().isAttributed()) {
			g.drawString(getText().getAttributedText().getIterator(), x, y + g.getFontMetrics().getAscent());

		} else {
			g.drawString(getText().getText(), x, y + g.getFontMetrics().getAscent());

		}
	}

}
