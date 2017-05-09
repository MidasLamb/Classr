package command;

import logicalobjects.Method;
import visualobjects.VisualClass;

public class CreateMethodCommand extends Command {

	private final VisualClass visualClass;
	private Method method;
	
	public CreateMethodCommand(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

	@Override
	void execute() {
		setMethod(this.getVisualClass().getLogicalObject().addMethod());
	}

	@Override
	void unexecute() {
		this.getMethod().delete();
	}

	private final Method getMethod() {
		return method;
	}

	private final void setMethod(Method method) {
		this.method = method;
	}

	private final VisualClass getVisualClass() {
		return visualClass;
	}
	
	

}
