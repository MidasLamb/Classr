package command;

import decoupling.CoupleVisitor;
import decoupling.Decoupler;
import visualobjects.VisualObject;

public class DeleteVisualObjectCommand extends Command {
	private final VisualObject<?> visualObject;
	private final Decoupler decoupler;
	
	public DeleteVisualObjectCommand(VisualObject<?> object) {
		this.visualObject = object;
		this.decoupler = getVisualObject().decoupleVisitor(new CoupleVisitor());
	}

	@Override
	void execute() {
		System.out.println("executed");
		getDecoupler().decouple();
	}

	@Override
	void unexecute() {
		getDecoupler().recouple();
	}

	@Override
	void cleanup() {
		
	}

	/**
	 * Returns the visual object that will be deleted
	 * @return the visual object that will be deleted
	 */
	private VisualObject<?> getVisualObject() {
		return visualObject;
	}
	
	/**
	 * Returns the decoupler for decoupeling the object
	 * @return the decoupler for decoupeling the object
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

}
