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
		object.moveTo(endX, endY);
	}

	@Override
	void unexecute() {
		object.moveTo(startX, startY);
	}

	private final int getStartX() {
		return startX;
	}

	private final int getStartY() {
		return startY;
	}

	private final int getEndX() {
		return endX;
	}

	private final int getEndY() {
		return endY;
	}

	private final ResizableAndMovableVisualObject getObject() {
		return object;
	}

}
