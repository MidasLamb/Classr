package visualobjects;

import canvaswindow.MyCanvasWindow;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;

public class TestContainer extends Container {
	public TestContainer() {
		super(0, 0, 100, 100, new MyCanvasWindow("test"));
	}

	@Override
	public void onClick(SingleClick sc) {
		amountRecievedClicks++;
		recievedSingleClick = true;
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		amountRecievedClicks++;
		recievedDoubleClick = true;
	}

	@Override
	public void onDragEnd(Drag d) {
		isDragged = true;
	}

	public int amountRecievedClicks;
	public boolean recievedSingleClick;
	public boolean recievedDoubleClick;
	public boolean isDragged;
}
