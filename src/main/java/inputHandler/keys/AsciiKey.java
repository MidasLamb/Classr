package inputHandler.keys;

public class AsciiKey extends Key {

	private final char value;
	
	public AsciiKey(char c) {
		this.value = c;
	}

	public char getValue() {
		return value;
	}

}
