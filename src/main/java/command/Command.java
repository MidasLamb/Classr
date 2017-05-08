package command;

/**
 * An abstract class for commands which can be ran by a controller
 */
public abstract class Command {

	 abstract void execute();
	 
	 abstract void unexecute();

}
