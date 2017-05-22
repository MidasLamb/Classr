package decoupling;

import static visualobjects.Backend.closeFormBelogingTo;

import visualobjects.EditableTextWrapper;

/**
 * A class that decouples EditableTextWrappers
 */
public class EditableTextWrapperDecoupler extends Decoupler {
	
	private final EditableTextWrapper<?> textWrapper;
	private final Decoupler logicalDecoupler;
	private final Decoupler parentDecoupler;

	public EditableTextWrapperDecoupler(EditableTextWrapper<?> textWrapper) {
		this.textWrapper = textWrapper;
		CoupleVisitor visitor = new CoupleVisitor();
		this.logicalDecoupler = visitor.startVisit(getTextWrapper().getLogicalObject());
		this.parentDecoupler = getTextWrapper().getParent().decoupleVisitor(visitor);
	}

	@Override
	public void decouple() {
		if(!isDecoupled()){
			setDecoupled(true);
			decoupleVisual();
			decoupleLogical();
		}
	}

	@Override
	public void recouple() {
		if(isDecoupled()){
			setDecoupled(false);
			recoupleVisual();
			recoupleLogical();
		}
		
	}
	
	/**
	 * Decouples the visual part
	 */
	private void decoupleVisual(){
		closeFormBelogingTo(getTextWrapper().getLogicalObject());
		getParentDecoupler().decouple();
	}
	
	/**
	 * Recouples the visual part
	 */
	private void recoupleVisual(){
		getParentDecoupler().recouple();
	}
	
	/**
	 * Decouples the logical part
	 */
	private void decoupleLogical(){
		if(getTextWrapper().getLogicalObject() != getTextWrapper().getParent().getLogicalObject()){
			getLogicalDecoupler().decouple();
		}
	}
	
	/**
	 * Recouples the logical part
	 */
	private void recoupleLogical(){
		if(getTextWrapper().getLogicalObject() != getTextWrapper().getParent().getLogicalObject()){
			getLogicalDecoupler().recouple();
		}
	}

	/**
	 * Returns the textWrapper that need to be decoupled
	 * @return the textWrapper that need to be decoupled
	 */
	private EditableTextWrapper<?> getTextWrapper() {
		return textWrapper;
	}

	/**
	 * Returns the decoupler for the logical object
	 * @return the decoupler for the logical object
	 */
	private Decoupler getLogicalDecoupler() {
		return logicalDecoupler;
	}

	/**
	 * Returns the parent decoupler
	 * @return the parent decoupler
	 */
	private Decoupler getParentDecoupler() {
		return parentDecoupler;
	}

}
