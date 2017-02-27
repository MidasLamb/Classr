package visualobjects.visualclass;

import static main.Constants.*;

import java.awt.Graphics;

import mouse.MouseClick;
import visualobjects.Association;
import visualobjects.VisualObject;

public class AssociationHandle extends VisualObject {

	public AssociationHandle(int x, int y, VisualObject parent) {
		super(x, y, ASSOCIATIONHANDLE_SIZE, ASSOCIATIONHANDLE_SIZE, parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show(Graphics g) {
		super.show(g);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc){
		super.select(x, y, mc);
		if (mc.equals(MouseClick.DRAG) && this.getContainer().hasHandleStart()){
			Class other = (Class) this.getContainer().getHandleStart().getParent();
			Class thisParent = (Class) this.getParent();
			Association a = new Association(thisParent, other);
			return a.getText();
		}
		return this;
	}
}
