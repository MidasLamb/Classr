package visualobjects;

import static main.Constants.Z_PADDING_BOX;

import java.awt.Graphics;

import gui.inputHandlers.clicks.SingleClick;
import logicalobjects.Association;

/**
 * The visualization of a logical association
 */
public class VisualAssociation extends VisualObject {
	private final VisualClass p1;
	private final VisualClass p2;
	private final PaddingBox<EditableTextWrapper> text;

	/**
	 * 
	 * @param 	participant1
	 * 			one VisualClass of the association
	 * @param 	participant2
	 * 			the second visualClass of the association
	 * @param 	parent
	 * 			The parent object of this VisualObject
	 */
	public VisualAssociation(VisualClass participant1, VisualClass participant2, VisualObject parent) {
		super(0, 0, 0, 0, 0, parent);
		Association association = new Association(participant1.getLogicalObject(), participant2.getLogicalObject());
		this.setLogicalObject(association);
		
		p1 = participant1;
		p2 = participant2;

		int centerX = getP1().getX() + (getP2().getX() - getP1().getX()) / 2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY()) / 2;
		this.text = new PaddingBox<EditableTextWrapper>(centerX, centerY, Z_PADDING_BOX, new EditableTextWrapper(0,0,0,"associatie","^[a-z][a-zA-Z0-9_]*" , null, association), this, association);
		this.getContainer().switchSelectedTo(this.getText().getContent());
		this.text.addDeleteListener(this);
	}

	@Override
	public final void draw(Graphics g) {
		g.drawLine(getP1().getX(), getP1().getY(), getP2().getX(), getP2().getY());
	}

	@Override
	public final boolean isIn(int x, int y) {
		return getText().isIn(x, y);
	}

	// Getters and setters

	/**
	 * @return the first visualClass from this association
	 */
	private VisualClass getP1() {
		return p1;
	}

	/**
	 * @return the second VisualClass from this association
	 */
	private VisualClass getP2() {
		return p2;
	}

	/**
	 * @return the paddingBox in which the text object is placed
	 */
	public final PaddingBox<EditableTextWrapper> getText() {
		return this.text;
	}

	@Override
	public void onDelete(){
		getP1().removeDeleteListener(this);
		getP2().removeDeleteListener(this);
	}

	@Override
	protected void onClick(SingleClick sc) {
		if (!this.isSelected() && !this.getText().getContent().isSelected())
			this.getContainer().switchSelectedTo(this);
		else if (this.isSelected())
			this.getContainer().switchSelectedTo(this.getText().getContent());
	}

}
