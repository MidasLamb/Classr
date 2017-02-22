package visualobjects;

import java.awt.Graphics;

public class Text extends VisualObject {

	private String text;
	
	public Text(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString("Text", this.getX(), this.getY());
	}

	@Override
	public VisualObject select(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub

	}

}
