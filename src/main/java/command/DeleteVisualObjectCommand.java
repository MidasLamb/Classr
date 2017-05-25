package command;

import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import visualobjects.VisualObject;

/**
 * A command to delete a VisualObject.
 */
public class DeleteVisualObjectCommand extends Command {
	private final VisualObject<?> visualObject;
	private final Decoupler decoupler;

	/**
	 * Constructs the Command
	 * 
	 * @param object
	 *            the VisualObject to delete
	 */
	public DeleteVisualObjectCommand(VisualObject<?> object) {
		this.visualObject = object;
		this.decoupler = getVisualObject().decoupleVisitor(new CoupleVisitor());
	}

	@Override
	void execute() {
		getDecoupler().decouple();
	}

	@Override
	void unexecute() {
		getDecoupler().recouple();
	}

	/**
	 * Returns the visual object that will be deleted
	 * 
	 * @return the visual object that will be deleted
	 */
	private VisualObject<?> getVisualObject() {
		return visualObject;
	}

	/**
	 * Returns the decoupler for decoupling the object
	 * 
	 * @return the decoupler for decoupling the object
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

}
