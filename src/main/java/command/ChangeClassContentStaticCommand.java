package command;

import logicalobjects.ClassContent;

/**
 * A command to change the being static of a ClassContent.
 * 
 * @author group11
 *
 */
public class ChangeClassContentStaticCommand extends Command {
	private final ClassContent classContent;
	private final boolean setStatic, lastStatic;

	/**
	 * Constructs the command.
	 * 
	 * @param classContent
	 *            The ClassContent of which the being static will be changed.
	 * @param setStatic
	 *            The value to which to set the being static of the
	 *            ClassContent.
	 */
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

	/**
	 * Returns the ClassContent which was given during construction.
	 * 
	 * @return The ClassContent which was given during construction.
	 */
	private final ClassContent getClassContent() {
		return classContent;
	}

	/**
	 * Returns the value of to which to set the being static of the
	 * ClassContent, given during construction.
	 * 
	 * @return The value of to which to set the being static of the
	 *         ClassContent, given during construction.
	 */
	private final boolean isSetStatic() {
		return setStatic;
	}

	/**
	 * Returns the value of what whether or not the ClassContent was static
	 * before this command was executed.
	 * 
	 * @return The value of what whether or not the ClassContent was static
	 *         before this command was executed.
	 */
	private final boolean isLastStatic() {
		return lastStatic;
	}

}
