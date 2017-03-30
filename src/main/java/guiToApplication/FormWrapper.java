package guiToApplication;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import gui.base.Form;
import inputHandlers.CanvasContent;
import inputHandlers.Clickable;
import inputHandlers.Typable;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;

public class FormWrapper extends Form implements CanvasContent{
	public FormWrapper(int width, int height){
		super(width, height);
	}
	
	

	@Override
	public void handleKeyEvent(KeyEvent e) {
		//TODO handle keys;
		
	}

	@Override
	public void onClick(SingleClick click) {
		super.onClick(click);
		
	}

	@Override
	public void onDoubleClick(DoubleClick click) {
		super.onDoubleClick(click);
		
	}

	@Override
	public void onDragEnd(Drag drag) {
		super.onDragEnd(drag);
		
	}



	@Override
	public void show(Graphics g) {
		super.draw(g);
	}

}
