package inputHandlers;

import inputHandler.keys.AsciiKey;
import inputHandler.keys.FunctionKey;

public interface Typable {
	public void handleAsciiKey(AsciiKey key);
	public void handleFunctionKey(FunctionKey key);
}