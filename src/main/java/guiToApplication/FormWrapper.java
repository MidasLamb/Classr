package guiToApplication;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import canvaswindow.MyCanvasWindow;
import gui.base.Form;
import inputHandlers.CanvasContent;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;

public class FormWrapper extends Form implements CanvasContent{
	private MyCanvasWindow canvasWindow;
	
	public FormWrapper(int width, int height, MyCanvasWindow canvasWindow){
		super(width, height);
		this.canvasWindow = canvasWindow;
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
	
	public void close(){
		//TODO make canvaswindow have a queue of contents which to display.
		this.canvasWindow.switchContent(null);
	}

}
