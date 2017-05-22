package command;

import logicalobjects.Method;
import logicalobjects.Parameter;

/**
 * A command to delete a parameter.
 * @author group11
 *
 */
public class DeleteParameterCommand extends Command {
	private final Parameter parameter;
	private final Method method;

	/**
	 * Constructs the command.
	 * 
	 * @param method
	 * 			The method from which to delete a parameter.
	 * @param parameter
	 * 			The parameter which to delete.
	 */
	public DeleteParameterCommand(Method method,Parameter parameter) {
		this.parameter = parameter;
		this.method = method;
	}

	@Override
	void execute() {
		getMethod().removeParameter(getParameter());
	}

	@Override
	void unexecute() {
		getMethod().addParameter(getParameter());
	}

	/**
	 * Returns the parameter which was given during construction.
	 * @return The parameter which was given during construction.
	 */
	private final Parameter getParameter() {
		return parameter;
	}

	/**
	 * Returns the Method which was given during construction.
	 * @return The Method which was given during construction.
	 */
	private final Method getMethod() {
		return method;
	}

}
