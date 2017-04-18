package gui.inputHandlers;

import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;

public interface Clickable {
	public void onClick(SingleClick click);
	public void onDoubleClick(DoubleClick click);
	public void onDragEnd(Drag drag);
	
}
