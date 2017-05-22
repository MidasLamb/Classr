package command;

import decoupling.Decoupler;
import decoupling.PaddingBoxDecoupler;
import logicalobjects.Method;
import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

/**
 * A command for creating a new Method in a class.
 */
public class CreateMethodCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<TextWrapper<Method>> methodPaddingBox;
	private Decoupler decoupler;

	/**
	 * Construct a new Command for creating a Method for the given VisualClass
	 *
	 * @param visualClass
	 *            VisualClass in which the method needs to be created.
	 */
	public CreateMethodCommand(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

	@Override
	void execute() {
		if(getDecoupler() == null){
			setMethodPaddingBox(this.getVisualClass().createMethod());
			setDecoupler(new PaddingBoxDecoupler(getMethodPaddingBox()));
		} else {
			getDecoupler().recouple();
		}
			
	}

	@Override
	void unexecute() {
		if(getDecoupler() != null)
			getDecoupler().decouple();
	}

	/**
	 * Get the PaddingBox for the created Method
	 * 
	 * @return PaddingBox for the created Method
	 */
	private final PaddingBox<TextWrapper<Method>> getMethodPaddingBox() {
		return methodPaddingBox;
	}

	/**
	 * Set the PaddingBox for the created Method.
	 * 
	 * @param methodPaddingBox
	 *            the PaddingBox to be set
	 */
	private final void setMethodPaddingBox(PaddingBox<TextWrapper<Method>> methodPaddingBox) {
		this.methodPaddingBox = methodPaddingBox;
	}

	/**
	 * VisualClass for which the Method needs to be created
	 * 
	 * @return VisualClass of this Command
	 */
	private final VisualClass getVisualClass() {
		return visualClass;
	}
	
	/**
	 * Returns the decoupler for the attribute
	 * @return the decoupler for the attribute
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

	/**
	 * Sets the new decoupler for the attribute
	 * @param 	decoupler
	 * 			the new decoupler for the attribute
	 */
	private void setDecoupler(Decoupler decoupler) {
		this.decoupler = decoupler;
	}

}
