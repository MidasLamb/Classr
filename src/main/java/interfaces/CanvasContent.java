package interfaces;

import java.awt.Graphics;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.Typable;

public interface CanvasContent extends Typable, Clickable {

	public void show(Graphics g);
}
