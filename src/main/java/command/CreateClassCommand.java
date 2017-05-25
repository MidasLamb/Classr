package command;

import decoupling.CoupleVisitor;
import decoupling.Decoupler;
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
	private Decoupler decoupler;

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
		if (getDecoupler() == null) {
			if (getX() >= 0 && getY() >= 0)
				setCreatedClass(getContainer().createNewClass(getX(), getY()));
			else
				setCreatedClass(getContainer().createNewClass());
			setDecoupler(getCreatedClass().decoupleVisitor(new CoupleVisitor()));
		} else {
			getDecoupler().recouple();
		}
	}

	@Override
	void unexecute() {
		if (getDecoupler() != null)
			getDecoupler().decouple();
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

	/**
	 * Returns the decoupler for the created class
	 * 
	 * @return the decoupler for the created class
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

	/**
	 * Sets the decoupler for the created class
	 * 
	 * @param decoupler
	 *            the decoupler for the created class
	 */
	private void setDecoupler(Decoupler decoupler) {
		this.decoupler = decoupler;
	}

}
