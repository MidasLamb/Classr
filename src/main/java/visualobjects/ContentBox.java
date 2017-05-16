package visualobjects;

import java.awt.Color;
import java.awt.Graphics;

import command.Controller;
import gui.form.base.Form;
import gui.form.base.FormContainer;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import guiToApplication.FormWrapper;
import interfaces.CanvasContent;

public class ContentBox extends ResizableAndMovableVisualObject implements FormContainer<FormWrapper>{
	private CanvasContent content;

	public ContentBox(int x, int y, int z, int width, int height, VisualObject parent, Controller controller) {
		super(x, y, z, width, height, parent, controller);

	}

	@Override
	protected boolean isInMoveActivator(int x, int y) {
		return false;
	}

	@Override
	void onClick(SingleClick sc) {
		if (isIn(sc.getX(), sc.getY())){
			this.getContainer().switchSelectedTo(this);
		}
		sc.translate(this.getX(), this.getY());
		getContent().onClick(sc);
	}

	@Override
	void onDoubleClick(DoubleClick dc) {

		dc.translate(this.getX(), this.getY());
		getContent().onDoubleClick(dc);
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		getContent().handleAsciiKey(key);
	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		getContent().handleFunctionKey(key);
	}

	private final CanvasContent getContent() {
		return content;
	}

	public final void setContent(CanvasContent content) {
		this.content = content;
		this.setWidth(content.getWidth());
		this.setHeight(content.getHeight());
	}

	@Override
	void draw(Graphics g) {
		
		
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.setColor(c);
		
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		g.translate(this.getX(), this.getY());
		getContent().show(g);
		g.translate(-this.getX(), -this.getY());
		
		
	}

	@Override
	public void close() {
		this.delete();
	}

	@Override
	public void switchTo(FormWrapper f) {
		ContentBox b = new ContentBox(10, 10, 0, 300, 300, getContainer(), getController());
		b.setContent(f);
	}

	@Override
	public int getMinimumWidth() {
		if (this.getContent() != null)
			return this.getContent().getWidth();
		else
			return 0;
	}

	@Override
	public int getMinimumHeight() {
		if (this.getContent() != null)
			return this.getContent().getHeight();
		else
			return 0;
	}
}
