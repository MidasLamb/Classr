package visualobjects.visualclass;

import java.awt.Graphics;

import visualobjects.VisualObject;

public class AssociationHandle extends VisualObject {

	public AssociationHandle(int x, int y) {
		super(x, y, 10, 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show(Graphics g) {
		super.show(g);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
}
