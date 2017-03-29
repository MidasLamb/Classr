package visualobjects;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;

public class TestContainer extends Container {
	public TestContainer() {
		super(0,0,100,100);
	}
	
	@Override
	public void onClick(SingleClick sc){
		amountRecievedClicks++;
		recievedSingleClick = true;
	}
	
	@Override
	public void onDoubleClick(DoubleClick dc){
		amountRecievedClicks++;
		recievedDoubleClick = true;
	}
	
	@Override
	public void onDragEnd(Drag d){
		isDragged = true;
	}
	
	public int amountRecievedClicks;
	public boolean recievedSingleClick;
	public boolean recievedDoubleClick;
	public boolean isDragged;
}
