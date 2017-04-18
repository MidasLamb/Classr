package gui.text;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import gui.inputHandlers.Typable;
import gui.text.state.PassiveState;
import gui.text.state.TextState;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;
import logicalobjects.StringVisitor;

public class Text implements Typable {
	private TextState state;
	private String text;
	private AttributedString attributedText;
	private boolean isAttributed;

	public Text(String text, TextState startState) {
		this.setText(text);
		this.switchState(startState);
		this.setAttributed(false);
	}

	public Text(AttributedString as, TextState startState) {
		this.setAttributedText(as);
		this.setText(getStringFromAttributedString(as));
		this.setAttributed(true);
		this.switchState(startState);
	}

	public final void switchState(TextState state) {
		this.setState(state);
		state.setText(this);
	}

	public final TextState getState() {
		return state;
	}

	private final void setState(TextState state) {
		this.state = state;
	}

	public final String getText() {
		return text;
	}

	public final void setText(String text) {
		this.text = text;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		this.getState().handleAsciiKey(key);

	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		this.getState().handleFunctionKey(key);

	}

	public void draw(Graphics g, int x, int y) {
		this.getState().draw(g, x, y);
	}

	public void addLetter(char c) {
		this.setText(this.getText() + c);
		if (isAttributed) {
			this.setAttributedText(new AttributedString(this.getText()));
		}
		

	}

	public void deleteChar() {
		if (this.getText().length() > 0)
			this.setText(this.getText().substring(0, this.getText().length() - 1));
		if (isAttributed) {
			this.setAttributedText(new AttributedString(this.getText()));
		}
	}

	public int getTextWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(this.getText());
	}

	public final AttributedString getAttributedText() {
		return attributedText;
	}

	public final void setAttributedText(AttributedString attributedText) {
		this.attributedText = attributedText;
		this.setText(getStringFromAttributedString(attributedText));
	}

	public final boolean isAttributed() {
		return isAttributed;
	}

	private final void setAttributed(boolean isAttributed) {
		this.isAttributed = isAttributed;
	}

	private String getStringFromAttributedString(AttributedString as) {
		AttributedCharacterIterator x = as.getIterator();
		String a = "";

		a += x.current();
		while (x.getIndex() < x.getEndIndex())
			a += x.next();
		a = a.substring(0, a.length() - 1);
		return a;
	}
}
