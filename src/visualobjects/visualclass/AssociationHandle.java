package visualobjects.visualclass;

import static main.Constants.ASSOCIATIONHANDLE_SIZE;

import java.awt.Graphics;

import inputHandlers.clicks.Drag;
import objects.Association;
import visualobjects.VisualObject;

public class AssociationHandle extends VisualObject {

	public AssociationHandle(int x, int y, int z, VisualClass parent) {
		super(x, y, z, ASSOCIATIONHANDLE_SIZE, ASSOCIATIONHANDLE_SIZE, parent);
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void onDragEnd(Drag d) {
		VisualObject otherHandle = this.getContainer().select(d.getStartX(), d.getStartY());
		if (otherHandle instanceof AssociationHandle) {
			VisualClass other = (VisualClass) otherHandle.getParent();
			this.createAssociation(other);
		}
	}

	private void createAssociation(VisualClass other) {
		VisualClass parent = (VisualClass) this.getParent();
		Association ass = new Association(parent.getLogicalObject(), other.getLogicalObject());
		VisualAssociation a = new VisualAssociation(ass, this.getContainer());
		this.getContainer().addChild(a);

	}
}
