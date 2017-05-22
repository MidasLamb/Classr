package decoupling;

import logicalobjects.Association;
import visualobjects.VisualAssociation;

/**
 * A class that decouples associations
 */
public class AssociationDecoupler extends Decoupler {
	private final VisualAssociation ass;

	public AssociationDecoupler(VisualAssociation ass) {
		this.ass = ass;
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
	 * Decouples the logical part of the association
	 */
	private void decoupleLogical(){
		Association ass = getAss().getLogicalObject();
		ass.getClass1().deleteAssociation(ass);
		ass.getClass2().deleteAssociation(ass);
	}
	
	/**
	 * Decouples the visual part of the association
	 */
	private void decoupleVisual(){
		getAss().getParent().removeChild(getAss());
	}
	
	/**
	 * Recouples the logical part of the association
	 */
	private void recoupleLogical(){
		Association ass = getAss().getLogicalObject();
		ass.getClass1().addAssociation(ass);
		ass.getClass2().addAssociation(ass);
	}
	
	/**
	 * Recouples the visual part of the association
	 */
	private void recoupleVisual(){
		getAss().getParent().addChild(getAss());	
	}

	/**
	 * Returns the association that needs to be removed
	 * @return the association that needs to be removed
	 */
	private VisualAssociation getAss() {
		return ass;
	}

}
