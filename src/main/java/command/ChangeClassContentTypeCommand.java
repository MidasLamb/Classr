package command;

import logicalobjects.ClassContent;

public class ChangeClassContentTypeCommand extends Command {
	private final ClassContent classContent;
	
	public ChangeClassContentTypeCommand(ClassContent classContent, String newType) {
		this.classContent = classContent;
	}

	@Override
	void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void unexecute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}

	private ClassContent getClassContent() {
		return classContent;
	}

}
