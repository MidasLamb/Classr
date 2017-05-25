package command;

import visualobjects.ResizableAndMovableVisualObject;

/**
 * A command to move a ResizableAndMovableVisualObject
 */
public class MoveCommand extends Command {
	private final int startX, startY, endX, endY;
	private final ResizableAndMovableVisualObject<?> object;

	/**
	 * Constructs the Command
	 * 
	 * @param object
	 *            the VisualObject to move
	 * @param startX
	 *            the initial x-position
	 * @param startY
	 *            the initial y-position
	 * @param endX
	 *            the x-coordinate to which the VisualObject should be moved
	 * @param endY
	 *            the y-coordinate to which the VisualObject should be moved
	 */
	public MoveCommand(ResizableAndMovableVisualObject<?> object, int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.object = object;
	}

	@Override
	void execute() {
		this.getObject().moveTo(this.getEndX(), this.getEndY());
	}

	@Override
	void unexecute() {
		this.getObject().moveTo(this.getStartX(), this.getStartY());
	}

	/**
	 * @return the start x-coordinate
	 */
	private final int getStartX() {
		return startX;
	}

	/**
	 * @return the start y-coordinate
	 */
	private final int getStartY() {
		return startY;
	}

	/**
	 * @return the end x-coordinate
	 */
	private final int getEndX() {
		return endX;
	}

	/**
	 * @return the end y-coordinate
	 */
	private final int getEndY() {
		return endY;
	}

	/**
	 * @return the object
	 */
	private final ResizableAndMovableVisualObject<?> getObject() {
		return object;
	}

}
