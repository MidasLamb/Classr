package command;

import logicalobjects.Method;
import logicalobjects.Parameter;

/**
 * A command to add a parameter to a method.
 * @author group11
 *
 */
public class AddParameterCommand extends Command {
	private final Method method;
	private final Parameter parameter;

	/**
	 * Creates the command to add a parameter to a method.
	 * @param method
	 * 			The method to which to add the parameter.
	 * @param parameter
	 * 			The parameter which to add to the method.
	 */
	public AddParameterCommand(Method method, Parameter parameter) {
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


	/**
	 * Returns the method which was given during construction.
	 * @return The method which was given during construction.
	 */
	private final Method getMethod() {
		return method;
	}


	/**
	 * Returns the parameter which was given during construction.
	 * @return The parameter which was given during construction.
	 */
	private final Parameter getParameter() {
		return parameter;
	}

}
