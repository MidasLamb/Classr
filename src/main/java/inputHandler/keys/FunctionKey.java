package inputHandler.keys;

public class FunctionKey extends Key {
	
	private final FunctionKeyType keyType;

	public FunctionKey(FunctionKeyType keyType) {
		this.keyType = keyType;
	}

	public FunctionKeyType getKeyType() {
		return keyType;
	}

	public static enum FunctionKeyType {ENTER, BACKSPACE, ESCAPE, DELETE};

}
