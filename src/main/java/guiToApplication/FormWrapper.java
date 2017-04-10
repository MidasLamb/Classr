package guiToApplication;

import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;
import gui.base.Form;
import inputHandlers.CanvasContent;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.Drag;
import inputHandlers.clicks.SingleClick;
import inputHandlers.keys.AsciiKey;
import inputHandlers.keys.FunctionKey;

public class FormWrapper extends Form implements CanvasContent {
	private MyCanvasWindow canvasWindow;

	public FormWrapper(int width, int height, MyCanvasWindow canvasWindow) {
		super(width, height);
		this.canvasWindow = canvasWindow;
	}

	@Override
	public void onClick(SingleClick click) {
		super.handleClick(click);

	}

	@Override
	public void onDoubleClick(DoubleClick click) {
		super.handleClick(click);

	}

	@Override
	public void onDragEnd(Drag drag) {
	}

	@Override
	public void show(Graphics g) {
		super.draw(g);
	}

	public void close() {
		this.canvasWindow.closeContent(this);
	}
	
	public MyCanvasWindow getCanvasWindow(){
		return this.canvasWindow;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		super.handleAsciiKey(key);
		
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		super.handleFunctionKey(key);		
	}

}
