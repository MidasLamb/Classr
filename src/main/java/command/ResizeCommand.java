package command;

import visualobjects.ResizableAndMovableVisualObject;

public class ResizeCommand extends Command {
	private final int startX, startY, startWidth, startHeight, endX, endY, endWidth, endHeight;
	private final ResizableAndMovableVisualObject object;

	public ResizeCommand(ResizableAndMovableVisualObject object, int startX, int startY, int startWidth, int startHeight, int endX, int endY, int endWidth, int endHeight) {
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

	@Override
	void cleanup() {
		// TODO Auto-generated method stub

	}

	private final int getStartX() {
		return startX;
	}

	private final int getStartY() {
		return startY;
	}

	private final int getStartWidth() {
		return startWidth;
	}

	private final int getStartHeight() {
		return startHeight;
	}

	private final int getEndX() {
		return endX;
	}

	private final int getEndY() {
		return endY;
	}

	private final int getEndWidth() {
		return endWidth;
	}

	private final int getEndHeight() {
		return endHeight;
	}

	private final ResizableAndMovableVisualObject getObject() {
		return object;
	}

}
