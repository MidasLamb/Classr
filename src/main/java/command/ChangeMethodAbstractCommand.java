package command;

import logicalobjects.Method;

public class ChangeMethodAbstractCommand extends Command {
	private final Method method;
	private final boolean newAbstract, lastAbstract;
	
	public ChangeMethodAbstractCommand(Method method, boolean setAbstract) {
		this.method = method;
		this.newAbstract = setAbstract;
		this.lastAbstract = method.isAbstract();
	}

	@Override
	void execute() {
		getMethod().setAbstract(isNewAbstract());
	}

	@Override
	void unexecute() {
		getMethod().setAbstract(isLastAbstract());

	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub

	}

	private final Method getMethod() {
		return method;
	}

	private final boolean isNewAbstract() {
		return newAbstract;
	}

	private final boolean isLastAbstract() {
		return lastAbstract;
	}

}
