package visualobjects.visualclass;

import static main.Constants.*;

import java.awt.Graphics;

import mouse.clicks.Drag;
import visualobjects.VisualObject;

public class AssociationHandle extends VisualObject {

	public AssociationHandle(int x, int y, VisualClass parent) {
		super(x, y, ASSOCIATIONHANDLE_SIZE, ASSOCIATIONHANDLE_SIZE, parent);
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void onDragEnd(Drag d){
		VisualObject otherHandle = this.getContainer().select(d.getStartX(), d.getStartY());
		if (otherHandle instanceof AssociationHandle){
			VisualClass other = (VisualClass) otherHandle.getParent();
			this.createAssociation(other);
		}
	}
	
	private void createAssociation(VisualClass other){
		VisualClass parent = (VisualClass) this.getParent();
		VisualAssociation a = new VisualAssociation(parent, other);
	}
}
