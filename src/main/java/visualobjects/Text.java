package visualobjects;

import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;
import static main.Constants.MAX_TEXT_WIDTH;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import formBuilder.FormCreator;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalObject;
import logicalobjects.StringVisitor;

public class Text extends VisualObject {


	public Text(int x, int y, int z, VisualObject parent, LogicalObject object) {
		super(x, y, z, MAX_TEXT_WIDTH, STANDARD_TEXT_HEIGHT, parent);
		setLogicalObject(object);
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public void draw(Graphics g) {

		// Get and set the width/height based on font
		if (this.getLogicalObject() == null)
			System.out.println("No logic");

		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		g.drawString(getText().getIterator(), getX(), getY() + getHeight());
	}
	
	/**
	 * @return Returns the text of the Logical Object
	 */
	protected AttributedString getText(){
		StringVisitor strVis = new StringVisitor();
		return getLogicalObject().accept(strVis);
	}
	
	protected String getString(){
		StringBuilder string = new StringBuilder();
		StringVisitor strVis = new StringVisitor();
		AttributedCharacterIterator itr = getLogicalObject().accept(strVis).getIterator();
		string.append(itr.current());
		while (itr.getIndex() < itr.getEndIndex())
		        string.append(itr.next());
		if(string.length() > 0)
			string.delete(string.length()-1, string.length());
		return string.toString().replaceAll("#", "");
	}
	
	@Override
	void onClick(SingleClick sc){
		this.getContainer().switchSelectedTo(this);
	}

	@Override
	void onDoubleClick(DoubleClick sc){
		FormCreator creator = new FormCreator(this.getLogicalObject(), this.getContainer().getCanvasWindow());
		this.getContainer().getCanvasWindow().addContentAndSwitchTo(creator.getForm());
	}
	
	@Override
	int getWidth(){
		return STANDARD_FONTMETRICS.stringWidth(getString());
	}
	
	@Override
	int getHeight(){
		return STANDARD_TEXT_HEIGHT;
	}

}
