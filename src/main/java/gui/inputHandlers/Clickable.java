package gui.inputHandlers;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;

public interface Clickable {
	public void onClick(SingleClick click);
	public void onDoubleClick(DoubleClick click);
	public void onDragEnd(Drag drag);
	
}
