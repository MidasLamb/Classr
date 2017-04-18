package gui.inputHandlers;

import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;

public interface Typable {
	public void handleAsciiKey(AsciiKey key);
	public void handleFunctionKey(FunctionKey key);
}