package command;

import logicalobjects.ClassContent;
import visibilities.Visibility;

public class ChangeClassContentVisiblityCommand extends Command {
	private final ClassContent classContent;
	private final Visibility newVis, oldVis;

	public ChangeClassContentVisiblityCommand(ClassContent classContent, Visibility v) {
		this.classContent = classContent;
		this.newVis = v;
		this.oldVis = classContent.getVisibility();
	}

	@Override
	void execute() {
		getClassContent().setVisibility(getNewVis());
	}

	@Override
	void unexecute() {
		getClassContent().setVisibility(getOldVis());
	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub

	}

	private final ClassContent getClassContent() {
		return classContent;
	}

	private final Visibility getNewVis() {
		return newVis;
	}

	private final Visibility getOldVis() {
		return oldVis;
	}

}
