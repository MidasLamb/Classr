package visualobjects;

import java.awt.FontMetrics;
import java.awt.Graphics;

import formBuilder.AttributeFormBuilder;
import formBuilder.FormCreator;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import logicalobjects.Attribute;
import logicalobjects.LogicalObject;

public class Text extends VisualObject {


	public Text(int x, int y, int z, int width, int height, VisualObject parent, String standardstring,
			LogicalObject object) {
		super(x, y, z, width, height, parent);
		setLogicalObject(object);
	}

	public Text(int x, int y, int z, VisualObject parent, String standardstring, LogicalObject object) {
		// 50, 16 is the standard size of the font if the text is "New Text"
		this(x, y, z, 50, 16, parent, standardstring, object);
	}

	Text(int x, int y, int z, int width, int height, VisualObject parent) {
		super(x,y,z,width,height,parent);
	}


	/**
	 * Shows the text frame
	 */
	@Override
	public void draw(Graphics g) {

		// Get and set the width/height based on font
		FontMetrics m = g.getFontMetrics();
		if (m == null)
			System.out.println("What");
		if (this.getLogicalObject() == null)
			System.out.println("No logic");
		this.setWidth(m.stringWidth(this.getText()));
		this.setHeight(m.getHeight());

		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		g.drawString(this.getText(), this.getX(), this.getY() + this.getHeight());
	}

	String getText() {
		String s = getLogicalObject().getName();
		return s == null? "No Text": s;
	}
	
	@Override
	void onClick(SingleClick sc){
		this.getContainer().switchSelectedTo(this);
	}

	@Override
	void onDoubleClick(DoubleClick sc){
		FormCreator creator = new FormCreator(this.getLogicalObject(), this.getContainer().getCanvasWindow());
		this.getContainer().getCanvasWindow().addContent(creator.getForm());
	}

}
