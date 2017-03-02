package visualobjects.visualclass;

import static main.Constants.*;

import java.awt.Graphics;

import mouse.MouseClickSort;
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
	public VisualObject select(int x, int y, MouseClickSort mc){
		super.select(x, y, mc);
		if (mc.equals(MouseClickSort.DRAG) && this.getContainer().hasHandleStart()){
			VisualClass other = (VisualClass) this.getContainer().getHandleStart().getParent();
			VisualClass thisParent = (VisualClass) this.getParent();
			Association a = new Association(thisParent, other);
			return a.getText();
		}
		return this;
	}
}
