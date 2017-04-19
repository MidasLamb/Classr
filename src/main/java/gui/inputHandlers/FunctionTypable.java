package gui.inputHandlers;

import gui.inputHandlers.keys.FunctionKey;

/**
 * Interface with methods to provide support for handling function keystrokes.
 */
public interface FunctionTypable {

	/**
	 * Handle a function keystroke.
	 * 
	 * @param key
	 *            Keystroke to be handled.
	 */
	public void handleFunctionKey(FunctionKey key);

}
