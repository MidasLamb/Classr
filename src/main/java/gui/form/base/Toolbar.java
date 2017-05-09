package gui.form.base;

import java.awt.Graphics;

import gui.inputHandlers.clicks.MouseClick;
import static gui.form.base.Constants.*;

public class Toolbar extends FormObject {
	
	public Toolbar(int width) {
		super(0, 0, width, STANDARD_TOOLBAR_HEIGHT);
	}

	public Toolbar(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	@Override
	void draw(Graphics g) {
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	protected void onAction() {
		// TODO Auto-generated method stub

	}

}
