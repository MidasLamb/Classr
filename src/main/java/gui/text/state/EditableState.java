package gui.text.state;

import java.awt.Graphics;

import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;

/**
 * A state for the text object in 
 * 	which the text object is editable
 */
public class EditableState extends TextState {

	@Override
	public void handleAsciiKey(AsciiKey key) {
		getText().addLetter(key.getValue());
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		switch (key.getKeyType()) {
		case BACKSPACE:
			getText().deleteChar();
			break;
		case ENTER:
			break;
		case ESCAPE:
			getText().switchState(new PassiveState());
			break;
		default:
			break;
		}

	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if (getText().isAttributed()) {
			g.drawString(getText().getAttributedText().getIterator(), x, y + g.getFontMetrics().getAscent());
		} else {
			g.drawString(getText().getText(), x, y + g.getFontMetrics().getAscent());

		}
		g.drawLine(x + getText().getTextWidth(g) + 1, y, x + getText().getTextWidth(g) + 1,
				y + g.getFontMetrics().getAscent());
		g.drawLine(x + getText().getTextWidth(g) + 2, y, x + getText().getTextWidth(g) + 2,
				y + g.getFontMetrics().getAscent());

	}

	@Override
	public boolean isEditable() {
		return true;
	}

}
