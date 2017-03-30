package gui;

import java.awt.FontMetrics;
import java.awt.Graphics;

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
		draw(g, getX(), getY());
	}
	
	void draw(Graphics g, int x, int y) {
		FontMetrics m = g.getFontMetrics();
		int height = m.getAscent();
		g.drawString(getText(), x, y+height);
	}
	
	int getHeight(Graphics g){
		FontMetrics m = g.getFontMetrics();
		return m.getMaxAscent();
	}
	
	int getWidth(Graphics g){
		FontMetrics m = g.getFontMetrics();
		return m.stringWidth(getText());
	}

	private String getText() {
		return text;
	}
	
}
