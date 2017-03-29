package visualobjects;

import java.awt.Graphics;

import interfaces.DeleteListener;
import interfaces.DeleteSubject;

import static main.Constants.*;
import objects.Association;
import objects.RealClass;

public class VisualAssociation extends VisualObject {
	private final VisualClass p1;
	private final VisualClass p2;
	private final PaddingBox text;
	
	//FIXME maybe make this class implement DeleteListener?
	private DeleteListener deleteListener;

	public VisualAssociation(VisualClass participant1, VisualClass participant2, VisualObject parent) {
		super(0, 0, 0, 0, 0, parent);
		//TODO check this
		Association association = new Association(participant1.getLogicalObject(), participant2.getLogicalObject());
		this.setLogicalObject(association);
		((RealClass) association.getClass1()).addAssociation(association);

		p1 = participant1;
		p2 = participant2;

		int centerX = getP1().getX() + (getP2().getX() - getP1().getX()) / 2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY()) / 2;
		this.text = new PaddingBox(centerX, centerY, Z_PADDING_BOX, this, "Nieuwe associatie", association);
		this.getContainer().switchSelectedTo(this.getText().getContent());
		
		this.setDeleteListener(new DeleteListener() {

			@Override
			public void notifyDelete(DeleteSubject subject) {
				delete();
			}
		});

		this.text.addDeleteListener(this.getDeleteListener());
		participant1.addDeleteListener(this.getDeleteListener());
		participant2.addDeleteListener(this.getDeleteListener());
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(getP1().getX(), getP1().getY(), getP2().getX(), getP2().getY());
	}

	@Override
	public boolean isIn(int x, int y) {
		return getText().isIn(x, y);
	}

	// Getters and setters

	private VisualClass getP1() {
		return p1;
	}

	private VisualClass getP2() {
		return p2;
	}

	public PaddingBox getText() {
		return this.text;
	}
	
	@Override
	public void onDelete(){
		getP1().removeDeleteListener(this.getDeleteListener());
	}

	private DeleteListener getDeleteListener() {
		return deleteListener;
	}

	private void setDeleteListener(DeleteListener deleteListener) {
		this.deleteListener = deleteListener;
	}

}
