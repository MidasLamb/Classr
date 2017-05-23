package gui.form.base;

import static gui.form.base.Constants.STANDARD_FONT;
import static gui.form.base.Constants.STANDARD_TEXT_ASCEND;
import static gui.form.base.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;

/**
 * Label that can be added to a Form.
 */
public class Label extends FormObject {
	
	final private String text;

	/**
	 * Create a new Label and set its text and coordinates.
	 * @param text
	 * @param x
	 * @param y
	 */
	public Label(String text, int x, int y) {
		super(x, y, 0, 0);
		this.text = text;
		setFocusable(false);
	}

	@Override
	public void onClick(MouseClick click) {
		onAction();
	}

	@Override
	public
	void draw(Graphics g) {
		Color c = g.getColor();
		if (this.isFocused()) {
			g.setColor(Color.BLUE);
		}
		g.drawString(getText(), this.getX(), this.getY()+this.getHeight());
		g.setColor(c);
	}
	
	@Override
	protected int getHeight(){
		return STANDARD_TEXT_HEIGHT;
	}
	
	int getAscent(){
		return STANDARD_TEXT_ASCEND;
	}
	
	@Override
	protected int getWidth(){
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
