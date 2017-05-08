package command;

import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;

/**
 * A command for changing the name of a logical object
 *
 */
public class ChangeLogicalObjectNameCommand extends Command {
	private final LogicalObject logicalObject;
	private final String newText;
	private final String oldText;
	
	/**
	 * Creates the command
	 * @param 	logicalObject
	 * 			the logical object from which the name needs to be changed
	 * @param 	newName
	 * 			the new name for the logical object
	 */
	public ChangeLogicalObjectNameCommand(LogicalClass logicalObject, String newName){
		this.logicalObject = logicalObject;
		this.newText = newName;
		this.oldText = logicalObject.getName();
	}

	@Override
	void execute() {
		getLogicalObject().setName(getNewText());
	}

	@Override
	void unexecute() {
		getLogicalObject().setName(getOldText());
	}

	/**
	 * @return the logical object in which the name needs to be changed
	 */
	private LogicalObject getLogicalObject() {
		return logicalObject;
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
