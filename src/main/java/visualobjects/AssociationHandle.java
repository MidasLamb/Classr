package visualobjects;

import static main.Constants.ASSOCIATIONHANDLE_SIZE;

import java.awt.Graphics;

import gui.inputHandlers.clicks.Drag;

public class AssociationHandle extends VisualObject {

	public AssociationHandle(int x, int y, int z, VisualClass parent) {
		super(x, y, z, ASSOCIATIONHANDLE_SIZE, ASSOCIATIONHANDLE_SIZE, parent);
	}

	@Override
	public final void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public final void onDragEnd(Drag d) {
		VisualObject otherHandle = this.getContainer().select(d.getStartX(), d.getStartY());
		if (otherHandle instanceof AssociationHandle) {
			VisualClass other = (VisualClass) otherHandle.getParent();
			this.createAssociation(other);
		}
	}

	private void createAssociation(VisualClass other) {
		VisualClass parent = (VisualClass) this.getParent();

		VisualAssociation a = new VisualAssociation(parent, other, this.getContainer());
	}
}
