package command;

import logicalobjects.Parameter;

/**
 * A command to change the name of a parameter.
 * 
 * @author group11
 *
 */
public class ChangeParameterNameCommand extends Command {
	private final Parameter parameter;
	private final String newName, oldName;

	/**
	 * Constructs the command.
	 * 
	 * @param parameter
	 *            The parameter of which to change the name.
	 * @param newName
	 *            The new name to which to change the parameter's name.
	 */
	public ChangeParameterNameCommand(Parameter parameter, String newName) {
		this.parameter = parameter;
		this.newName = newName;
		this.oldName = parameter.getName();
	}

	@Override
	void execute() {
		getParameter().setName(getNewName());

	}

	@Override
	void unexecute() {
		getParameter().setName(getOldName());

	}

	/**
	 * Returns the Parameter given during construction.
	 * 
	 * @return The Parameter given during construction.
	 */
	private final Parameter getParameter() {
		return parameter;
	}

	/**
	 * Returns the new name of the Parameter.
	 * 
	 * @return The new name of the Parameter.
	 */
	private final String getNewName() {
		return newName;
	}

	/**
	 * Returns the name of the parameter before this command was executed.
	 * 
	 * @return The name of the parameter before this command was executed.
	 */
	private final String getOldName() {
		return oldName;
	}

}
