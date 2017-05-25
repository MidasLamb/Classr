package decoupling;

import static visualobjects.Backend.closeFormBelogingTo;

import java.util.List;
import java.util.stream.Collectors;

import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

/**
 * This decoupler is created for decoupling a class in the logical and visual
 * layer
 */
public class ClassDecoupler extends Decoupler {

	private final VisualClass visualClass;
	private final List<Decoupler> associationDecouplers;

	public ClassDecoupler(VisualClass klasse) {
		this.visualClass = klasse;
		this.associationDecouplers = makeAssociationDecouplers();
	}

	@Override
	public void decouple() {
		if (!isDecoupled()) {
			closeAssociatedForms();
			setDecoupled(true);
			decoupleVisualClass();
			decoupleAssociations();
		}
	}

	/**
	 * Closes the forms associated to this class
	 */
	private void closeAssociatedForms() {
		getVisualClass().getChildren().stream().forEach(x -> closeFormBelogingTo(x.getLogicalObject()));
	}

	@Override
	public void recouple() {
		if (isDecoupled()) {
			setDecoupled(false);
			recoupleVisualClass();
			recoupleAssociations();
		}
	}

	/**
	 * Decouples the visual class and the visual associations from the other
	 * visual objects
	 */
	private void decoupleVisualClass() {
		getVisualClass().getParent().removeChild(getVisualClass());
		getVisualClass().getContainer().switchSelectedTo(null);
	}

	/**
	 * Re-couples the visual class and the visual associations from the other
	 * visual objects
	 */
	private void recoupleVisualClass() {
		getVisualClass().getParent().addChild(getVisualClass());
	}

	/**
	 * This function decouples the associations belonging to this VisualClass
	 */
	private void decoupleAssociations() {
		getAssociationDecouplers().forEach(x -> x.decouple());
	}

	/**
	 * This function re-couples the associations belonging to this VisualClass
	 */
	private void recoupleAssociations() {
		getAssociationDecouplers().forEach(x -> x.recouple());
	}

	/**
	 * This function returns new decouplers for the associations
	 * 
	 * @return the decouplers for the associations
	 */
	private final List<Decoupler> makeAssociationDecouplers() {
		return getVisualAssociations().stream().map(x -> x.decoupleVisitor(new CoupleVisitor()))
				.collect(Collectors.toList());
	}

	/**
	 * Decouples the associations belonging to this class
	 * 
	 * @return The list of associations which belonged to the class.
	 */
	private final List<VisualAssociation> getVisualAssociations() {
		return getVisualClass().getParent().getChildren().stream().filter(x -> x instanceof VisualAssociation)
				.map(x -> (VisualAssociation) x)
				.filter(x -> x.getP1().equals(getVisualClass()) || x.getP2().equals(getVisualClass()))
				.collect(Collectors.toList());
	}

	/**
	 * Returns the visual class that you want to remove
	 * 
	 * @return the visual class that you want to remove
	 */
	private VisualClass getVisualClass() {
		return visualClass;
	}

	/**
	 * Returns the decouplers for the associations
	 * 
	 * @return the decouplers for the associations
	 */
	private List<Decoupler> getAssociationDecouplers() {
		return associationDecouplers;
	}

}
