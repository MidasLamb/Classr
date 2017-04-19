package gui.inputHandlers.keys;

public class FunctionKey extends Key {

	private final FunctionKeyType keyType;

	public FunctionKey(FunctionKeyType keyType) {
		this.keyType = keyType;
	}

	public FunctionKeyType getKeyType() {
		return keyType;
	}

	public boolean isArrowKey() {
		return this.getKeyType() == FunctionKeyType.RIGHT || this.getKeyType() == FunctionKeyType.LEFT
				|| this.getKeyType() == FunctionKeyType.UP || this.getKeyType() == FunctionKeyType.DOWN;
	}

	public static enum FunctionKeyType {
		ENTER, BACKSPACE, ESCAPE, DELETE, RIGHT, LEFT, UP, DOWN
	};

}
