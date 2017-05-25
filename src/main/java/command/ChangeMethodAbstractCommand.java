package command;

import logicalobjects.Method;

/**
 * A command to change whether or not a method is abstract.
 * 
 * @author group11
 *
 */
public class ChangeMethodAbstractCommand extends Command {
	private final Method method;
	private final boolean newAbstract, lastAbstract;

	/**
	 * Constructs the command.
	 * 
	 * @param method
	 *            The Method of which to change whether or not it is abstract.
	 * @param setAbstract
	 *            The value to which to set the being abstract of the Method to.
	 */
	public ChangeMethodAbstractCommand(Method method, boolean setAbstract) {
		this.method = method;
		this.newAbstract = setAbstract;
		this.lastAbstract = method.isAbstract();
	}

	@Override
	void execute() {
		getMethod().setAbstract(isNewAbstract());
	}

	@Override
	void unexecute() {
		getMethod().setAbstract(isLastAbstract());
	}

	/**
	 * Returns the Method given during construction.
	 * 
	 * @return The Method given during construction.
	 */
	private final Method getMethod() {
		return method;
	}

	/**
	 * Returns the value to which to set the being abstract of the Method to.
	 * 
	 * @return The value to which to set the being abstract of the Method to.
	 */
	private final boolean isNewAbstract() {
		return newAbstract;
	}

	/**
	 * Returns the value of being abstract that the Method had before execution
	 * of this command.
	 * 
	 * @return The value of being abstract that the Method had before execution
	 *         of this command.
	 */
	private final boolean isLastAbstract() {
		return lastAbstract;
	}

}
