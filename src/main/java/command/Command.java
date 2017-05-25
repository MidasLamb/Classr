package command;

/**
 * An abstract class for commands which can be ran by a controller
 */
public abstract class Command {

	/**
	 * Execute the command
	 */
	abstract void execute();

	/**
	 * Rollback the execution of the command
	 */
	abstract void unexecute();
}
