package command;

import logicalobjects.Parameter;

public class changeParameterTypeCommand extends Command {
	private final Parameter parameter;
	private final String newType, oldType;

	public changeParameterTypeCommand(Parameter parameter, String newType) {
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

	private final Parameter getParameter() {
		return parameter;
	}

	private final String getNewType() {
		return newType;
	}

	private final String getOldType() {
		return oldType;
	}

}
