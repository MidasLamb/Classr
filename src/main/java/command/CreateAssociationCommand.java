package command;

import visualobjects.Container;
import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

public class CreateAssociationCommand extends Command {
	private final VisualClass class1;
	private final VisualClass class2;
	private final Container container;
	private VisualAssociation createdAssociation;
	
	private final Controller controller;
	
	public CreateAssociationCommand(VisualClass class1, VisualClass class2, Container container, Controller controller) {
		this.class1 = class1;
		this.class2 = class2;
		this.container = container;
		this.controller = controller;
	}

	@Override
	void execute() {
		setCreatedAssociation(new VisualAssociation(getClass1(), getClass2(), getContainer(), this.controller));
	}

	@Override
	void unexecute() {
		getCreatedAssociation().delete();
	}

	/**
	 * Returns the first logical class
	 * @return the first logical class
	 */
	private VisualClass getClass1() {
		return class1;
	}
	
	/**
	 * Returns the second logical class
	 * @return the second logical class
	 */
	private VisualClass getClass2() {
		return class2;
	}

	/**
	 * Returns the created association
	 * @return the created association
	 */
	private VisualAssociation getCreatedAssociation() {
		return createdAssociation;
	}

	/**
	 * Sets the created association
	 * @param 	createdAssociation
	 * 			the created association
	 */
	private void setCreatedAssociation(VisualAssociation createdAssociation) {
		this.createdAssociation = createdAssociation;
	}

	/**
	 * Returns the container in which this association is made
	 * @return the container in which this association is made
	 */
	private Container getContainer() {
		return container;
	}

}
