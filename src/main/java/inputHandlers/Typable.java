package inputHandlers;

import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public interface Typable {
	public void handleAsciiKey(AsciiKey key);
	public void handleFunctionKey(FunctionKey key);
}