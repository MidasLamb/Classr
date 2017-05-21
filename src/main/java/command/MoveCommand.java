package command;

import visualobjects.ResizableAndMovableVisualObject;

public class MoveCommand extends Command {
	private final int startX, startY, endX, endY;
	private final ResizableAndMovableVisualObject<?> object;

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

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
