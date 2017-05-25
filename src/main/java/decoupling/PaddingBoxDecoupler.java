package decoupling;

import static visualobjects.Backend.closeFormBelogingTo;

import visualobjects.PaddingBox;

/**
 * A class that decouples PaddingBoxes
 */
public class PaddingBoxDecoupler extends Decoupler {
	private final PaddingBox<?, ?> paddingBox;
	private final Decoupler logicalDecoupler;

	public PaddingBoxDecoupler(PaddingBox<?, ?> paddingBox) {
		this.paddingBox = paddingBox;
		CoupleVisitor visitor = new CoupleVisitor();
		this.logicalDecoupler = visitor.startVisit(getPaddingBox().getLogicalObject());
	}

	@Override
	public void decouple() {
		if (!isDecoupled()) {
			decoupleLogical();
			decoupleVisual();
			setDecoupled(true);
		}
	}

	/**
	 * Decouples the logical part
	 */
	private void decoupleLogical() {
		getLogicalDecoupler().decouple();
	}

	/**
	 * Decouples the visual part
	 */
	private void decoupleVisual() {
		closeFormBelogingTo(getPaddingBox().getLogicalObject());
		getPaddingBox().getParent().removeChild(getPaddingBox());
		getPaddingBox().getContainer().switchSelectedTo(null);
	}

	@Override
	public void recouple() {
		if (isDecoupled()) {
			recoupleLogical();
			recoupleVisual();
			setDecoupled(false);
		}

	}

	/**
	 * Re-couples the logical part
	 */
	private void recoupleLogical() {
		getLogicalDecoupler().recouple();
	}

	/**
	 * Re-couples the visual part
	 */
	private void recoupleVisual() {
		getPaddingBox().getParent().addChild(getPaddingBox());
	}

	/**
	 * Returns the padddingBox that needs to be decoupled
	 * 
	 * @return the padddingBox that needs to be decoupled
	 */
	private PaddingBox<?, ?> getPaddingBox() {
		return paddingBox;
	}

	/**
	 * Returns the decoupler for the logical object
	 * 
	 * @return the decoupler for the logical object
	 */
	private Decoupler getLogicalDecoupler() {
		return logicalDecoupler;
	}

}
