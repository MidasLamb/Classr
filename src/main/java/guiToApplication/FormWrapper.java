package guiToApplication;

import java.awt.Graphics;

import canvaswindow.MyCanvasWindow;
import gui.form.base.Form;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import interfaces.CanvasContent;

public class FormWrapper extends Form implements CanvasContent {
	private MyCanvasWindow canvasWindow;

	public FormWrapper(int width, int height, MyCanvasWindow canvasWindow) {
		super(width, height);
		this.canvasWindow = canvasWindow;
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
