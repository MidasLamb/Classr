package visualobjects.visualclass;

import visualobjects.VisualObject;
import java.awt.Graphics;
import visualobjects.Text;

public class ClassBody extends VisualObject {

	public ClassBody(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.addChild(new ContentPlaceholder(x, y, width, height));
	}
	
	@Override
	public void show(Graphics g) {
		super.show(g);
		g.drawLine(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY());
	}


}
