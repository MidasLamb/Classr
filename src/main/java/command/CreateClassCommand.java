package command;

import visualobjects.Container;
import visualobjects.VisualClass;

/**
 * A command for adding a class to a container
 *
 */
public class CreateClassCommand extends Command {

	private final Container container;
	private VisualClass createdClass;
	private int x = -1;
	private int y = -1;

	/**
	 * Creates the command
	 * 
	 * @param container
	 *            the container in which the class needs to be added
	 */
	public CreateClassCommand(Container container) {
		this.container = container;
		createdClass = null;
	}

	/**
	 * Creates the command
	 * 
	 * @param container
	 *            the container in which the class needs to be added
	 */
	public CreateClassCommand(Container container, int x, int y) {
		this(container);
		setX(x);
		setY(y);
	}

	@Override
	void execute() {
		if (getCreatedClass() == null) {
			if (getX() >= 0 && getY() >= 0)
				setCreatedClass(getContainer().createNewClass(getX(), getY()));
			else
				setCreatedClass(getContainer().createNewClass());
		} else {
			getContainer().addChild(getCreatedClass());
		}
	}

	@Override
	void unexecute() {
		if (getCreatedClass() != null)
			getContainer().removeChild(getCreatedClass());
	}

	@Override
	void cleanup() {
		getCreatedClass().delete();
	}

	/**
	 * @return the container of this object
	 */
	private Container getContainer() {
		return container;
	}

	/**
	 * Returns the created class
	 * 
	 * @return the created class
	 */
	private VisualClass getCreatedClass() {
		return createdClass;
	}

	/**
	 * Sets the createdClass
	 * 
	 * @param createdClass
	 *            the new created class
	 */
	private void setCreatedClass(VisualClass createdClass) {
		this.createdClass = createdClass;
	}

	/**
	 * Return the x-coordinate where the class is created
	 * 
	 * @return the x-coordinate where the class is created
	 */
	private int getX() {
		return x;
	}

	/**
	 * The x-coordinate where the class needs to be set
	 * 
	 * @param x
	 *            the x-coordinate where the class needs to be set
	 */
	private void setX(int x) {
		this.x = x;
	}

	/**
	 * Return the x-coordinate where the class is created
	 * 
	 * @return the x-coordinate where the class is created
	 */
	private int getY() {
		return y;
	}

	/**
	 * The y-coordinate where the class needs to be set
	 * 
	 * @param y
	 *            the y-coordinate where the class needs to be set
	 */
	private void setY(int y) {
		this.y = y;
	}


}
