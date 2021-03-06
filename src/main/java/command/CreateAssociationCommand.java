package command;

import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import visualobjects.Container;
import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

/**
 * A command to create an Association.
 * 
 * @author group11
 *
 */
public class CreateAssociationCommand extends Command {
	private final VisualClass class1;
	private final VisualClass class2;
	private final Container container;
	private VisualAssociation createdAssociation;
	private Decoupler decoupler;

	private final Controller controller;

	/**
	 * Constructs the parameter.
	 * 
	 * @param class1
	 *            The first class to create the Association with.
	 * @param class2
	 *            The second class to create the Association with.
	 * @param container
	 *            The Container in which the created Association should reside.
	 * @param controller
	 *            The Controller which the created Association should use.
	 */
	public CreateAssociationCommand(VisualClass class1, VisualClass class2, Container container,
			Controller controller) {
		this.class1 = class1;
		this.class2 = class2;
		this.container = container;
		this.controller = controller;
	}

	@Override
	void execute() {
		if (getDecoupler() == null) {
			setCreatedAssociation(new VisualAssociation(getClass1(), getClass2(), getContainer(), getController()));
			setDecoupler(getCreatedAssociation().decoupleVisitor(new CoupleVisitor()));

		} else {
			getDecoupler().recouple();
		}
	}

	@Override
	void unexecute() {
		if (getDecoupler() != null)
			getDecoupler().decouple();
	}

	/**
	 * Returns the first logical class
	 * 
	 * @return the first logical class
	 */
	private VisualClass getClass1() {
		return class1;
	}

	/**
	 * Returns the second logical class
	 * 
	 * @return the second logical class
	 */
	private VisualClass getClass2() {
		return class2;
	}

	/**
	 * Returns the created association
	 * 
	 * @return the created association
	 */
	private VisualAssociation getCreatedAssociation() {
		return createdAssociation;
	}

	/**
	 * Sets the created association
	 * 
	 * @param createdAssociation
	 *            the created association
	 */
	private void setCreatedAssociation(VisualAssociation createdAssociation) {
		this.createdAssociation = createdAssociation;
	}

	/**
	 * Returns the container in which this association is made
	 * 
	 * @return the container in which this association is made
	 */
	private Container getContainer() {
		return container;
	}

	/**
	 * Returns the used controller
	 * 
	 * @return the used controller
	 */
	private Controller getController() {
		return controller;
	}

	/**
	 * Returns the decoupler for the created association
	 * 
	 * @return the decoupler for the created association
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

	/**
	 * Sets the decoupler for the created association
	 * 
	 * @param decoupler
	 *            the decoupler for the created association
	 */
	private void setDecoupler(Decoupler decoupler) {
		this.decoupler = decoupler;
	}

}
