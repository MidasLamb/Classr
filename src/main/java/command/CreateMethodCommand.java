package command;

import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

public class CreateMethodCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<TextWrapper> methodPaddingBox;
	
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

	private final PaddingBox<TextWrapper> getMethodPaddingBox() {
		return methodPaddingBox;
	}

	private final void setMethodPaddingBox(PaddingBox<TextWrapper> methodPaddingBox) {
		this.methodPaddingBox = methodPaddingBox;
	}

	private final VisualClass getVisualClass() {
		return visualClass;
	}

}
