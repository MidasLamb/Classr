package gui.text.state;

import java.awt.Graphics;

import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public class EditableState extends TextState {

	public EditableState() {
		
	}

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
		case ESCAPE:
			getText().switchState(new PassiveState());
			break;
		}


	}

	@Override
	public void draw(Graphics g, int x, int y) {
		g.drawString(getText().getText(), x, y + g.getFontMetrics().getAscent());
		
		g.drawLine(x + getText().getTextWidth(g) + 1, y, x + getText().getTextWidth(g) + 1,
				y + g.getFontMetrics().getAscent());
		g.drawLine(x + getText().getTextWidth(g) + 2, y, x + getText().getTextWidth(g) + 2,
				y + g.getFontMetrics().getAscent());

	}

}
