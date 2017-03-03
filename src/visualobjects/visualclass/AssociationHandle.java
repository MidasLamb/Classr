package visualobjects.visualclass;

import static main.Constants.*;

import java.awt.Graphics;

import mouse.MouseClickSort;
import mouse.clicks.Drag;
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
	public void onDragEnd(Drag d){
		VisualClass parent = (VisualClass) this.getParent();
		VisualObject otherHandle = this.getContainer().select(d.getStartX(), d.getStartY());
		if (otherHandle instanceof AssociationHandle){
			VisualClass other = (VisualClass) otherHandle.getParent();
			Association a = new Association(parent, other);
		}
	}
}
