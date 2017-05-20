package command;

import logicalobjects.Method;
import logicalobjects.Parameter;

public class addParameterCommand extends Command {
	private final Method method;
	private final Parameter parameter;

	public addParameterCommand(Method method, Parameter parameter) {
		this.method = method;
		this.parameter = parameter;
	}


	@Override
	void execute() {
		getMethod().addParameter(getParameter());
		
	}

	@Override
	void unexecute() {
		getMethod().removeParameter(getParameter());
		
	}

	@Override
	void cleanup() {
		
	}


	private final Method getMethod() {
		return method;
	}


	private final Parameter getParameter() {
		return parameter;
	}

}
