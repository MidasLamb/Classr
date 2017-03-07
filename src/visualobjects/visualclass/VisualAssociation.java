package visualobjects.visualclass;

import java.awt.Graphics;

import objects.Association;
import objects.RealClass;
import visualobjects.PaddingBox;
import visualobjects.VisualObject;

public class VisualAssociation extends VisualObject {

	public VisualAssociation(Association association, VisualObject parent) {
		super(0, 0, 0, 0, 0, parent);
		this.setLogicalObject(association);
		((RealClass) association.getClass1()).addAssociation(association);
		association.setVisualObject(this);

		p1 = (VisualClass) association.getClass1().getVisualObject();
		p2 = (VisualClass) association.getClass2().getVisualObject();

		int centerX = getP1().getX() + (getP2().getX() - getP1().getX()) / 2;
		int centerY = getP1().getY() + (getP2().getY() - getP1().getY()) / 2;
		// TODO fix z version.
		this.text = new PaddingBox(centerX, centerY, 0, this, "Nieuwe associatie",  association);
		this.addChild(getText());
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
