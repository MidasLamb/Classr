package visualobjects;

import static gui.form.base.Constants.STANDARD_LABEL_PADDING;
import static gui.form.base.Constants.STANDARD_TEXT_ASCEND;
import static gui.form.base.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;

import command.Controller;
import gui.form.base.FormContainer;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import guiToApplication.FormWrapper;
import interfaces.CanvasContent;
import logicalobjects.LogicalVoid;

/**
 * Box that contains CanvasContent, can be resized and moved.
 */
public class ContentBox extends ResizableAndMovableVisualObject<LogicalVoid> implements FormContainer<FormWrapper> {
	private CanvasContent content;
	private String name;
	private static final int TITLEBAR_HEIGHT = STANDARD_TEXT_HEIGHT + 2 * STANDARD_LABEL_PADDING;

	/**
	 * Construct a new ContentBox with given coordinates, dimensions, parent,
	 * controller and name.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate (depth)
	 * @param width
	 *            width
	 * @param height
	 *            height
	 * @param parent
	 *            parent
	 * @param controller
	 *            controller
	 * @param name
	 *            name
	 */
	public ContentBox(int x, int y, int z, int width, int height, VisualObject<?> parent, Controller controller,
			String name) {
		super(x, y, z, width, height, parent, controller);
		this.setName(name);
		getContainer().switchSelectedTo(this);
	}

	@Override
	protected boolean isInMoveActivator(int x, int y) {
		// return true if is in the title bar
		return isInTitleBar(x, y);
	}

	/**
	 * Checks if the given coordinates are in the title bar of this ContentBox
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return Returns true if the coordinate is in the title bar, false
	 *         otherwise
	 */
	private boolean isInTitleBar(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + TITLEBAR_HEIGHT, y);
	}

	@Override
	void onClick(SingleClick sc) {
		if (isIn(sc.getX(), sc.getY())) {
			this.getContainer().switchSelectedTo(this);
		}
		sc.translate(this.getX(), this.getY() + TITLEBAR_HEIGHT);
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

	/**
	 * Get the content of this ContentBox
	 * 
	 * @return content
	 */
	private final CanvasContent getContent() {
		return content;
	}

	/**
	 * Set the content of this ContentBox
	 * 
	 * @param content
	 *            content to be set in this ContentBox
	 */
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
		if (isSelected())
			g.setColor(Color.red);
		else
			g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), TITLEBAR_HEIGHT);
		g.setColor(Color.WHITE);
		g.drawString(this.getName(), this.getX() + STANDARD_LABEL_PADDING,
				this.getY() + STANDARD_TEXT_ASCEND + STANDARD_LABEL_PADDING);

		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

		// Calculate begin position
		int beginX = this.getX();
		int beginY = this.getY() + TITLEBAR_HEIGHT;
		g.translate(beginX, beginY);
		g.setColor(Color.black);
		getContent().show(g);
		// Undo translate
		g.translate(-beginX, -beginY);
		g.setColor(c);
	}

	@Override
	public void close() {
		this.delete();
	}

	@Override

	public void switchTo(FormWrapper f) {
		this.setContent(f);
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
			return this.getContent().getHeight() + STANDARD_TEXT_HEIGHT + 2 * STANDARD_LABEL_PADDING;
		else
			return 0;
	}

	/**
	 * @return the name
	 */
	private final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	private final void setName(String name) {
		this.name = name;
	}

	@Override
	public FormContainer<FormWrapper> getExtraContainer() {
		ContentBox b = new ContentBox(10, 10, 0, 300, 300, getContainer(), getController(), "Dialog box");
		this.addDeleteListener(b);
		return b;
	}

}
