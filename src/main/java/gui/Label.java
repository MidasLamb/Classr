package gui;

import static gui.Constants.STANDARD_FONT;
import static gui.Constants.STANDARD_TEXT_ASCEND;
import static gui.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Canvas;
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
	void onClick(MouseClick click) {
		onAction();
	}

	@Override
	void draw(Graphics g) {
		draw(g, getX(), getY());
	}
	
	void draw(Graphics g, int x, int y) {
		g.drawString(getText(), x, y+getHeight());
	}
	
	@Override
	int getHeight(){
		return STANDARD_TEXT_HEIGHT;
	}
	
	int getAscent(){
		return STANDARD_TEXT_ASCEND;
	}
	
	@Override
	int getWidth(){
		Canvas c = new Canvas();
		FontMetrics m = c.getFontMetrics(STANDARD_FONT);
		return m.stringWidth(getText());
	}

	private String getText() {
		return text;
	}

	@Override
	protected void onAction() {}
	
}
