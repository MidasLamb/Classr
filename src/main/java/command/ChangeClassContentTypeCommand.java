package command;

import logicalobjects.ClassContent;

/**
 * A command to change the type of a class content object
 */
public class ChangeClassContentTypeCommand extends Command {
	private final ClassContent classContent;
	private final String newType;
	private final String oldType;

	/**
	 * Creates the command.
	 * 
	 * @param classContent
	 *            The ClassContent of which to change the type.
	 * @param newType
	 *            The new Type that should be given to the ClassContent.
	 */
	public ChangeClassContentTypeCommand(ClassContent classContent, String newType) {
		this.classContent = classContent;
		this.newType = newType;
		this.oldType = classContent.getType();
	}

	@Override
	void execute() {
		getClassContent().setType(getNewType());
	}

	@Override
	void unexecute() {
		getClassContent().setType(getOldType());
	}

	/**
	 * Returns the class content of which the type needs to be changed
	 * 
	 * @return the class content.
	 */
	private ClassContent getClassContent() {
		return classContent;
	}

	/**
	 * Returns the new type for the class content
	 * 
	 * @return the new type for the class content
	 */
	private String getNewType() {
		return newType;
	}

	/**
	 * Returns the old type for the class content
	 * 
	 * @return the old type for the class content
	 */
	private String getOldType() {
		return oldType;
	}

}
