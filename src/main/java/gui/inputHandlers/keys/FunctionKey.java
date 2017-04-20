package gui.inputHandlers.keys;

/**
 * Class that represents a keystroke that is a function key.
 */
public class FunctionKey extends Key {

	private final FunctionKeyType keyType;

	/**
	 * Create a new FunctionKey given its FunctionKeyType
	 * 
	 * @param keyType
	 *            FunctionKeyType of this keystroke
	 */
	public FunctionKey(FunctionKeyType keyType) {
		this.keyType = keyType;
	}

	/**
	 * Get the FunctionKeyType of this FunctionKey
	 * 
	 * @return FunctionKeyType of this FunctionKey
	 */
	public FunctionKeyType getKeyType() {
		return keyType;
	}

	/**
	 * Boolean that indicates whether this FunctionKey corresponds with an arrow
	 * key.
	 * 
	 * @return true if the FuntionKeyType of this FunctionKey is RIGHT, LEFT, UP
	 *         or DOWN
	 */
	public boolean isArrowKey() {
		return this.getKeyType() == FunctionKeyType.RIGHT || this.getKeyType() == FunctionKeyType.LEFT
				|| this.getKeyType() == FunctionKeyType.UP || this.getKeyType() == FunctionKeyType.DOWN;
	}

	/**
	 * Enumeration of supported function keys.
	 */
	public static enum FunctionKeyType {
		ENTER, BACKSPACE, ESCAPE, DELETE, RIGHT, LEFT, UP, DOWN
	};

}
