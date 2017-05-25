package command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * The controller is the class that runs the commands. It also maintains a list
 * of commands to undo and redo them
 */
public class Controller {
	private final Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	/**
	 * Executes a given command. After the command is executed, the redo stack
	 * will be made empty
	 * 
	 * @param command
	 *            The command that needs to be executed
	 */
	public void executeCommand(Command command) {
		if (getUndoStack().contains(command) || getRedoStack().contains(command))
			throw new IllegalArgumentException("You cannot run the same command twice");
		emptyRedoStack();
		getUndoStack().add(command);
		command.execute();
	}

	/**
	 * Undoes all the given commands
	 * 
	 * @param commands
	 *            the commands that need to be undone
	 */
	public void undoCommands(Collection<Command> commands) {
		HashSet<Command> set = new HashSet<>(commands);
		Iterator<Command> iterator = getUndoStack().iterator();
		ArrayList<Command> removeList = new ArrayList<>();
		while (!commands.isEmpty() && iterator.hasNext()) {
			Command next = iterator.next();
			if (set.contains(next)) {
				next.unexecute();
				removeList.add(next);
			}
		}
		getUndoStack().remove(removeList);
	}

	/**
	 * Undo the last action if there is one
	 */
	public void undo() {
		if (getUndoStack().isEmpty())
			return;
		Command command = getUndoStack().pop();
		getRedoStack().push(command);
		command.unexecute();
	}

	/**
	 * Redo the last action if there is one
	 */
	public void redo() {
		if (getRedoStack().isEmpty())
			return;
		Command command = getRedoStack().pop();
		getUndoStack().push(command);
		command.execute();
	}

	/**
	 * Empty's the redo stack
	 */
	private void emptyRedoStack() {
		setRedoStack(new Stack<Command>());
	}

	/**
	 * Returns the undo stack.
	 * 
	 * @return the undo stack.
	 */
	private Stack<Command> getUndoStack() {
		return undoStack;
	}

	/**
	 * @return the redo stack
	 */
	private Stack<Command> getRedoStack() {
		return redoStack;
	}

	/**
	 * Sets the redo stack
	 * 
	 * @param redoStack
	 *            The Stack to which to set the redostack.
	 */
	private void setRedoStack(Stack<Command> redoStack) {
		this.redoStack = redoStack;
	}

	/**
	 * Returns whether an undo operation can be executed.
	 * 
	 * @return returns true if the undo stack is not empty
	 */
	public boolean canUndo() {
		return !getUndoStack().isEmpty();
	}

	/**
	 * Returns whether an redo operation can be executed.
	 * 
	 * @return returns true if the redo stack is not empty
	 */
	public boolean canRedo() {
		return !getRedoStack().isEmpty();
	}
}
