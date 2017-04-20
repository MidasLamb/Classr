package gui.inputHandlers.keys;

/**
 * Class that represents a keystroke that is an ASCII character.
 */
public class AsciiKey extends Key {

	private final char value;

	/**
	 * Create a new AsciiKey with the given character.
	 * 
	 * @param c
	 *            ASCII character
	 */
	public AsciiKey(char c) {
		this.value = c;
	}

	/**
	 * Get the character from this AsciiKey
	 * @return character
	 */
	public char getValue() {
		return value;
	}

}
