package command;

import logicalobjects.ClassContent;

public class ChangeClassContentStaticCommand extends Command {
	private final ClassContent classContent;
	private final boolean setStatic, lastStatic;
	
	public ChangeClassContentStaticCommand(ClassContent classContent, boolean setStatic) {
		this.classContent = classContent;
		this.setStatic = setStatic;
		this.lastStatic = classContent.isStatic();
	}

	@Override
	void execute() {
		getClassContent().setStatic(isSetStatic());

	}

	@Override
	void unexecute() {
		getClassContent().setStatic(isLastStatic());

	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub

	}

	private final ClassContent getClassContent() {
		return classContent;
	}

	private final boolean isSetStatic() {
		return setStatic;
	}

	private final boolean isLastStatic() {
		return lastStatic;
	}

}
