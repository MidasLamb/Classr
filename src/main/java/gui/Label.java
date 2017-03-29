package gui;

import java.awt.FontMetrics;
import java.awt.Graphics;

import static gui.Constants.*;

import inputHandlers.clicks.MouseClick;

public class Label extends FormObject {
	
	final private String text;

	public Label(String text, int x, int y) {
		super(x, y, 0, 0);
		this.text = text;		
	}

	@Override
	void onClick(MouseClick click) {}

	@Override
	void draw(Graphics g) {
		FontMetrics m = g.getFontMetrics();
		int height = m.getHeight();
		g.drawString(getText(), getX(), getY()+height);
	}

	private String getText() {
		return text;
	}
	
}
