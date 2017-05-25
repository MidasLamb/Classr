package command;

import visualobjects.ResizableAndMovableVisualObject;

/**
 * A command to resize a ResizableAndMovableVisualObject
 */
public class ResizeCommand extends Command {
	private final int startX, startY, startWidth, startHeight, endX, endY, endWidth, endHeight;
	private final ResizableAndMovableVisualObject<?> object;

	/**
	 * Constructs the Command
	 * 
	 * @param object
	 *            the VisualObject to resize
	 * @param startX
	 *            the initial x-coordinate
	 * @param startY
	 *            the initial y-coordinate
	 * @param startWidth
	 *            the initial width
	 * @param startHeight
	 *            the initial height
	 * @param endX
	 *            the x-coordinate to which the VisualObject should be resized
	 * @param endY
	 *            the y-coordinate to which the VisualObject should be resized
	 * @param endWidth
	 *            the width to which the VisualObject should be resized
	 * @param endHeight
	 *            the height to which the VisualObject should be resized
	 */
	public ResizeCommand(ResizableAndMovableVisualObject<?> object, int startX, int startY, int startWidth,
			int startHeight, int endX, int endY, int endWidth, int endHeight) {
		this.startX = startX;
		this.startY = startY;
		this.startWidth = startWidth;
		this.startHeight = startHeight;
		this.endX = endX;
		this.endY = endY;
		this.endWidth = endWidth;
		this.endHeight = endHeight;

		this.object = object;
	}

	@Override
	void execute() {
		getObject().resizeTo(getEndX(), getEndY(), getEndWidth(), getEndHeight());
	}

	@Override
	void unexecute() {
		getObject().resizeTo(getStartX(), getStartY(), getStartWidth(), getStartHeight());

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
	 * @return the start width
	 */
	private final int getStartWidth() {
		return startWidth;
	}

	/**
	 * @return the start height
	 */
	private final int getStartHeight() {
		return startHeight;
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
	 * @return the end width
	 */
	private final int getEndWidth() {
		return endWidth;
	}

	/**
	 * @return the end height
	 */
	private final int getEndHeight() {
		return endHeight;
	}

	/**
	 * @return the object
	 */
	private final ResizableAndMovableVisualObject<?> getObject() {
		return object;
	}

}
