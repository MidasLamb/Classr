package gui.text;

import static gui.form.base.Constants.STANDARD_FONTMETRICS;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import gui.inputHandlers.Typable;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import gui.text.state.TextState;

/**
 * A class to aid in handling and displaying text.
 */
public class Text implements Typable {
	private TextState state;
	private AttributedString attributedText;

	/**
	 * Constructor for this class
	 * @param 	text
	 * 			The current text that need to be displayed
	 * @param 	startState
	 * 			The current state for this object
	 */
	public Text(String text, TextState startState) {
		this(new AttributedString(text), startState);
	}
	
	/**
	 * Constructor for this class
	 * @param 	as
	 * 			The attributed string containing the text that need to be displayed
	 * @param 	startState
	 * 			The current state for this object
	 */
	public Text(AttributedString as, TextState startState) {
		this.setAttributedText(as);
		this.switchState(startState);
	}
	
	/**
	 * To change the state of this object
	 * @param 	state
	 * 			The new state
	 */
	public final void switchState(TextState state) {
		this.setState(state);
		state.setText(this);
	}

	/**
	 * Draws the current text
	 * @param g
	 * 			the graphics
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 */
	public void draw(Graphics g, int x, int y) {
		this.getState().draw(g, x, y);
	}

	/**
	 * Adds a char to the current text if it is not longer than the maxWidth
	 * @param 	c
	 * 			The char that needs to be added
	 */
	public void addLetter(char c) {
		String newText = getTextAsString() + c;
		setAttributedText(new AttributedString(newText));
	}

	/**
	 * Deletes the last char of the current text
	 */
	public void deleteChar() {
		String newText = getTextAsString().substring(0, getTextAsString().length()-1);
		setAttributedText(new AttributedString(newText));
	}

	/**
	 * Returns the current text width given the graphics
	 * @param 	g
	 * 			the graphics
	 * @return the current text width
	 */
	public int getTextWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(getTextAsString());
	}
	
	public int getTextWidth(){
		return STANDARD_FONTMETRICS.stringWidth(getTextAsString());
	}

	/**
	 * Sets the attributedstring text and synchronizes it with the text in the string
	 * @param 	attributedText
	 * 			the new attributed string
	 */
	public final void setAttributedText(AttributedString attributedText) {
		this.attributedText = attributedText;
	}

	/**
	 * Given an attributedstring this function returns the text value in a string
	 * @param 	as
	 * 			The attributed string
	 * @return	the corresponding string containing the text of the attributed string
	 */
	public static String getStringFromAttributedString(AttributedString as) {
		StringBuilder string = new StringBuilder();
		AttributedCharacterIterator itr = as.getIterator();
		string.append(itr.current());
		while (itr.getIndex() < itr.getEndIndex())
		        string.append(itr.next());
		if(string.length() > 0)
			string.delete(string.length()-1, string.length());
		return string.toString().replaceAll("#", "");
	}
	
	@Override
	public void handleAsciiKey(AsciiKey key) {
		this.getState().handleAsciiKey(key);

	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		this.getState().handleFunctionKey(key);

	}
	
	//Getters and setters
	
	/**
	 * Get state returns the current state of the object
	 * @return current state of the object
	 */
	public final TextState getState() {
		return state;
	}
	
	/**
	 * @param 	state
	 * 			The new state
	 */
	private final void setState(TextState state) {
		this.state = state;
	}

	/**
	 * @return the current text in a string
	 */
	public final String getTextAsString() {
		return getStringFromAttributedString(getAttributedText());
	}
	
	/**
	 * @return the attributed string containing the current text
	 */
	public final AttributedString getAttributedText() {
		return attributedText;
	}
	
	/**
	 * Returns if the current text is editable
	 * @return true if the current text is editable, otherwise false
	 */
	public boolean isEditable(){
		return getState().isEditable();
	}
}
