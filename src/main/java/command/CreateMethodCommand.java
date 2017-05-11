package command;

import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

/**
 * A command for creating a new Method in a class.
 */
public class CreateMethodCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<TextWrapper> methodPaddingBox;

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
		setMethodPaddingBox(this.getVisualClass().createMethod());
	}

	@Override
	void unexecute() {
		this.getMethodPaddingBox().delete();
	}

	/**
	 * Get the PaddingBox for the created Method
	 * 
	 * @return PaddingBox for the created Method
	 */
	private final PaddingBox<TextWrapper> getMethodPaddingBox() {
		return methodPaddingBox;
	}

	/**
	 * Set the PaddingBox for the created Method.
	 * 
	 * @param methodPaddingBox
	 *            the PaddingBox to be set
	 */
	private final void setMethodPaddingBox(PaddingBox<TextWrapper> methodPaddingBox) {
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

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
