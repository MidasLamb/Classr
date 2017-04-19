package gui.inputHandlers;

import gui.inputHandlers.keys.AsciiKey;

/**
 * Interface with methods to provide support for handling ASCII keystrokes.
 */
public interface AsciiTypable {
	/**
	 * Handle an ASCII keystroke.
	 * 
	 * @param key
	 *            Keystroke to be handled.
	 */
	public void handleAsciiKey(AsciiKey key);
}
