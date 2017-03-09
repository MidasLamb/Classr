package visualobjects;

import java.awt.Graphics;

import static main.Constants.*;
import objects.Association;
import objects.RealClass;

public class VisualAssociation extends VisualObject {

	public VisualAssociation(VisualClass participant1, VisualClass participant2, VisualObject parent) {
		super(0, 0, 0, 0, 0, parent);
		Association association = new Association(participant1.getLogicalObject(), participant2.getLogicalObject());
		this.setLogicalObject(association);
		((RealClass) association.getClass1()).addAssociation(association);
		association.setVisualObject(this);

		p1 = participant1;
		p2 = participant2;

		int centerX = getP1().getX() + (getP2().getX() - getP1().getX()) / 2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY()) / 2;
		this.text = new PaddingBox(centerX, centerY, Z_PADDING_BOX, this, "Nieuwe associatie", association);
		this.getContainer().switchSelectedTo(this.getText().getContent());
	}

	@Override
	public void draw(Graphics g) {
		g.drawLine(getP1().getX(), getP1().getY(), getP2().getX(), getP2().getY());
	}

	@Override
	public boolean isIn(int x, int y) {
		return getText().isIn(x, y);
	}

	@Override
	protected void afterDeleteChild(VisualObject v) {
		if (v.equals(this.getText())) {
			((Association) this.getLogicalObject()).remove();
			this.delete();
		}
	}

	// Getters and setters

	private VisualClass getP1() {
		return p1;
	}

	private final VisualClass p1;

	private VisualClass getP2() {
		return p2;
	}

	private final VisualClass p2;

	public PaddingBox getText() {
		return this.text;
	}

	private final PaddingBox text;

}
