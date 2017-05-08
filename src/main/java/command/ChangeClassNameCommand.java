package command;

import logicalobjects.LogicalClass;

/**
 * A command for changing a classes name
 *
 */
public class ChangeClassNameCommand extends Command {
	private final LogicalClass logicalClass;
	private final String newText;
	private final String oldText;
	
	/**
	 * Creates the command
	 * @param 	logicalClass
	 * 			the class from which the name needs to be changed
	 * @param 	newName
	 * 			the new name for the class
	 */
	public ChangeClassNameCommand(LogicalClass logicalClass, String newName){
		this.logicalClass = logicalClass;
		this.newText = newName;
		this.oldText = logicalClass.getName();
	}

	@Override
	void execute() {
		getLogicalClass().setName(getNewText());
	}

	@Override
	void unexecute() {
		getLogicalClass().setName(getOldText());
	}

	/**
	 * @return the logical class in which the name needs to be changed
	 */
	private LogicalClass getLogicalClass() {
		return logicalClass;
	}

	/**
	 * Returns the new text
	 * @return the new text
	 */
	private String getNewText() {
		return newText;
	}

	/**
	 * Returns the old text
	 * @return the old text
	 */
	private String getOldText() {
		return oldText;
	}


}
