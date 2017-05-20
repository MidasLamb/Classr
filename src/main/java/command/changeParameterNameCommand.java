package command;

import logicalobjects.Parameter;

public class changeParameterNameCommand extends Command {
	private final Parameter parameter;
	private final String newName, oldName;

	public changeParameterNameCommand(Parameter parameter, String newName) {
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

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}

	private final Parameter getParameter() {
		return parameter;
	}

	private final String getNewName() {
		return newName;
	}

	private final String getOldName() {
		return oldName;
	}

}
