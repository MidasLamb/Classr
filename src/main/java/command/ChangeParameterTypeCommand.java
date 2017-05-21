package command;

import logicalobjects.Parameter;

/**
 * A command to change the type of a parameter.
 * @author group11
 *
 */
public class ChangeParameterTypeCommand extends Command {
	private final Parameter parameter;
	private final String newType, oldType;

	/**
	 * Constructs the command.
	 * @param parameter
	 * 			The Parameter of which to change the Type.
	 * @param newType
	 * 			The Type to which to change the Parameter's Type.
	 */
	public ChangeParameterTypeCommand(Parameter parameter, String newType) {
		this.parameter = parameter;
		this.newType = newType;
		this.oldType = parameter.getType();
	}

	@Override
	void execute() {
		getParameter().setType(getNewType());
	}

	@Override
	void unexecute() {
		getParameter().setType(getOldType());
		
	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Returns the Parameter which was given during construction.
	 * @return The Parameter which was given during construction.
	 */
	private final Parameter getParameter() {
		return parameter;
	}

	/**
	 * Returns the new type to which to set the Parameter's Type, which was given during construction.
	 * @return The new type to which to set the Parameter's Type, which was given during construction.
	 */
	private final String getNewType() {
		return newType;
	}

	/**
	 * Returns the type of the Parameter before this command is executed.
	 * @return The type of the Parameter before this command is executed.
	 */
	private final String getOldType() {
		return oldType;
	}

}
