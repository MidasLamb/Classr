package command;

import logicalobjects.ClassContent;
import visibilities.Visibility;

/**
 * A command to change the visibility of a class content object
 */
public class ChangeClassContentVisibilityCommand extends Command {
	private final ClassContent classContent;
	private final Visibility oldVisibility;
	private final Visibility newVisibility;
	
	/**
	 * Creates the command.
	 * @param classContent
	 * 			The ClassContent of which to change the Visibility.
	 * @param newVisibility
	 * 			The Visibility to change the visibility of the ClassContent to.
	 */
	public ChangeClassContentVisibilityCommand(ClassContent classContent, Visibility newVisibility) {
		this.classContent = classContent;
		this.newVisibility = newVisibility;
		this.oldVisibility = classContent.getVisibility();
	}

	@Override
	void execute() {
		getClassContent().setVisibility(getNewVisibility());
	}

	@Override
	void unexecute() {
		getClassContent().setVisibility(getOldVisibility());
	}

	/**
	 * Returns the class content of which the type needs to be changed
	 * @return
	 */
	private ClassContent getClassContent() {
		return classContent;
	}


	/**
	 * Returns the old visibility for the class content
	 * @return the old visibility for the class content
	 */
	private Visibility getOldVisibility() {
		return oldVisibility;
	}

	
	/**
	 * Returns the new visibility for the class content
	 * @return the new visibility for the class content
	 */
	private Visibility getNewVisibility() {
		return newVisibility;
	}

}
