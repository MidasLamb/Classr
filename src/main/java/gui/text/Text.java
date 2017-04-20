package gui.text;

import static main.Constants.*;

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
	private String text;
	private AttributedString attributedText;
	private boolean isAttributed;
	private int maxWidth = -1;

	/**
	 * Constructor for this class
	 * @param 	text
	 * 			The current text that need to be displayed
	 * @param 	startState
	 * 			The current state for this object
	 */
	public Text(String text, TextState startState) {
		this.setText(text);
		this.switchState(startState);
		this.setAttributed(false);
	}
	
	/**
	 * Constructor for this class
	 * @param 	text
	 * 			The current text that need to be displayed
	 * @param 	startState
	 * 			The current state for this object
	 * @param 	maxWidth
	 * 			the max width that the max may have if 
	 * 				it is drawn using the standard font matrics (see constants)
	 */
	public Text(String text, TextState startState, int maxWidth) {
		this(text, startState);
		setMaxWidth(maxWidth);
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
		this.setText(getStringFromAttributedString(as));
		this.setAttributed(true);
		this.switchState(startState);
	}
	
	/**
	 * Constructor for this class
	 * @param 	as
	 * 			The attributed string containing the text that need to be displayed
	 * @param 	startState
	 * 			The current state for this object
	 * @param 	maxWidth
	 * 			the max width that the max may have if 
	 * 				it is drawn using the standard font matrics (see constants)
	 */
	public Text(AttributedString as, TextState startState, int maxWidth) {
		this(as, startState);
		setMaxWidth(maxWidth);		
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
		if(getMaxWidth() > 0 && getDrawLength(getText() + c) > getMaxWidth())
			return;
		this.setText(this.getText() + c);
		if (isAttributed()) {
			this.setAttributedText(new AttributedString(this.getText()));
		}
	}

	/**
	 * Deletes the last char of the current text
	 */
	public void deleteChar() {
		if (this.getText().length() > 0)
			this.setText(this.getText().substring(0, this.getText().length() - 1));
		if (isAttributed()) {
			this.setAttributedText(new AttributedString(this.getText()));
		}
	}

	/**
	 * Returns the current text width given the graphics
	 * @param 	g
	 * 			the graphics
	 * @return the current text width
	 */
	public int getTextWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(this.getText());
	}

	/**
	 * Sets the attributedstring text and synchronizes it with the text in the string
	 * @param 	attributedText
	 * 			the new attributed string
	 */
	public final void setAttributedText(AttributedString attributedText) {
		this.attributedText = attributedText;
		this.setText(getStringFromAttributedString(attributedText));
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
	
	/**
	 * Returns the draw length of the given string using 
	 * 		the Standard FontMetrics from the constants file
	 * @param 	text
	 * 			the text from which you want to know the length
	 * @return	the length of the text when its drawn ussing 
	 * 				the standard font metrics from the constants file
	 */
	private int getDrawLength(String text){
		return STANDARD_FONTMETRICS.stringWidth(text);
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
	public final String getText() {
		return text;
	}

	/**
	 * Sets the text for this object
	 * @param 	text
	 * 			The new text
	 */
	private final void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the attributed string containing the current text
	 */
	public final AttributedString getAttributedText() {
		return attributedText;
	}
	
	/**
	 * Returns if this text is originally an attributed string text
	 * @return isAttributed
	 */
	public final boolean isAttributed() {
		return isAttributed;
	}

	/**
	 * @param 	isAttributed
	 * 			the new value for the isAttributed bool
	 */
	private final void setAttributed(boolean isAttributed) {
		this.isAttributed = isAttributed;
	}
	
	/**
	 * @return the maxWidth that this text may have
	 */
	private int getMaxWidth() {
		return maxWidth;
	}

	/**
	 * Sets the maxWidth that this text may have
	 * @param 	maxWidth
	 * 			the new maxWidth
	 */
	private void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}
}
