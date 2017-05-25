package visualobjects;

import static main.Constants.ASSOCIATIONHANDLE_SIZE;

import java.awt.Graphics;

import command.Controller;
import command.CreateAssociationCommand;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.inputHandlers.clicks.Drag;
import logicalobjects.LogicalVoid;

/**
 * A handle on VisualClasses to help create VisualObjects
 */
public class AssociationHandle extends VisualObject<LogicalVoid> {
	private final VisualClass parent;

	public AssociationHandle(int x, int y, int z, VisualClass parent, Controller controller) {
		super(x, y, z, ASSOCIATIONHANDLE_SIZE, ASSOCIATIONHANDLE_SIZE, parent, controller);
		this.parent = parent;
	}

	@Override
	public final void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public final void onDragEnd(Drag d) {
		if (isIn(d.getEndX(), d.getEndY())) {
			VisualObject<?> otherHandle = getContainer().select(d.getStartX(), d.getStartY());
			if (otherHandle instanceof AssociationHandle) {
				VisualClass other = (VisualClass) otherHandle.getParent();
				this.createAssociation(other);
			}
		}
	}

	/**
	 * Creates an association with the VisualClass where this association handle
	 * is attached to and the given visualClass
	 * 
	 * @param other
	 *            The other visualClass to which the link needs to be made
	 */
	private void createAssociation(VisualClass other) {
		CreateAssociationCommand command = new CreateAssociationCommand(getParent(), other, getContainer(),
				getController());
		getController().executeCommand(command);
	}

	@Override
	public VisualClass getParent() {
		return parent;
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return visitor.visit(this);
	}
}
