package visualobjects;

import static main.Constants.Z_PADDING_BOX;

import java.awt.Graphics;

import command.Controller;
import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import gui.inputHandlers.clicks.SingleClick;
import interfaces.UpdateListener;
import interfaces.UpdateSubject;
import logicalobjects.Association;

/**
 * The visualization of a logical association
 */
public class VisualAssociation extends VisualObject<Association> implements UpdateListener, Editable {
	private final VisualClass p1;
	private final VisualClass p2;
	private final PaddingBox<Association, EditableTextWrapper<Association>> text;

	/**
	 * 
	 * @param participant1
	 *            one VisualClass of the association
	 * @param participant2
	 *            the second visualClass of the association
	 * @param parent
	 *            The parent object of this VisualObject
	 * @param controller
	 *            The controller for this object.
	 */
	public VisualAssociation(VisualClass participant1, VisualClass participant2, VisualObject<?> parent,
			Controller controller) {
		super(0, 0, 0, 0, 0, parent, controller);
		Association association = new Association(participant1.getLogicalObject(), participant2.getLogicalObject());
		this.setLogicalObject(association);

		p1 = participant1;
		p2 = participant2;

		p1.addUpdateListener(this);
		p2.addUpdateListener(this);

		int centerX = getP1().getX() + (getP2().getX() - getP1().getX()) / 2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY()) / 2;
		this.text = new PaddingBox<Association, EditableTextWrapper<Association>>(centerX, centerY, Z_PADDING_BOX,
				new EditableTextWrapper<Association>(0, 0, 0, null, association, getController()), this, association,
				getController());
		this.getContainer().switchSelectedTo(this.getText().getContent());
		this.getText().getContent().setEditable();
		this.text.addDeleteListener(this);
		this.text.getContent().addUpdateListener(this);
		this.updateTextPosition();
	}

	@Override
	public final void draw(Graphics g) {
		updateTextPosition();
		g.drawLine(getP1().getX(), getP1().getY(), getP2().getX(), getP2().getY());
	}

	/**
	 * Updates the current position of the text.
	 */
	private void updateTextPosition() {
		if (getText() != null) {
			getText().setX(getP1().getX() + (getP2().getX() - getP1().getX()) / 2 - getText().getWidth());
			getText().setY(getP1().getY() + (getP2().getY() - getP1().getY()) / 2);
		}
	}

	@Override
	public final boolean isIn(int x, int y) {
		return getText().isIn(x, y);
	}

	// Getters and setters

	/**
	 * @return the first visualClass from this association
	 */
	public VisualClass getP1() {
		return p1;
	}

	/**
	 * @return the second VisualClass from this association
	 */
	public VisualClass getP2() {
		return p2;
	}

	/**
	 * @return the paddingBox in which the text object is placed
	 */
	public final PaddingBox<Association, EditableTextWrapper<Association>> getText() {
		return this.text;
	}

	@Override
	public void onDelete() {
		getP1().removeDeleteListener(this);
		getP2().removeDeleteListener(this);
	}

	@Override
	protected void onClick(SingleClick sc) {
		if (!this.isSelected() && !this.getText().getContent().isSelected()) {
			this.getContainer().switchSelectedTo(this);
		} else if (this.isSelected()) {
			this.getContainer().switchSelectedTo(this.getText().getContent());
			this.getText().getContent().setEditable();
		}
	}

	@Override
	public void getNotifiedOfUpdate(UpdateSubject updateSubject) {
		this.updateTextPosition();
	}

	@Override
	public Decoupler decoupleVisitor(CoupleVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public void setEditable() {
		getText().getContent().setEditable();

	}

	@Override
	public boolean isEditable() {
		return getText().getContent().isEditable();
	}

}
