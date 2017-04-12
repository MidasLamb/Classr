package visualobjects;

import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;
import static main.Constants.MAX_TEXT_WIDTH;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import formBuilder.FormCreator;
import formBuilder.NewObjectFormCreator;
import gui.text.Text;
import gui.text.state.PassiveState;
import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import logicalobjects.LogicalObject;
import logicalobjects.StringVisitor;

public class TextWrapper extends VisualObject {
	private Text textObject;

	public TextWrapper(int x, int y, int z, VisualObject parent, LogicalObject object) {
		super(x, y, z, MAX_TEXT_WIDTH, STANDARD_TEXT_HEIGHT, parent);
		setLogicalObject(object);
		this.setTextObject(new Text("", new PassiveState()));
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public void draw(Graphics g) {	
		
		this.getTextObject().draw(g, this.getX(), this.getY());
		// Draw the string
		// Add the height with the Y value since drawing strings
		// begins bottom left
		//g.drawString(getText().getIterator(), getX(), getY() + getHeight());
	}
	
	/**
	 * @return Returns the text of the Logical Object
	 */
	protected AttributedString getText(){
		StringVisitor strVis = new StringVisitor();
		return strVis.startVisit(this.getLogicalObject());
	}
	
	protected String getString(){
		StringBuilder string = new StringBuilder();
		StringVisitor strVis = new StringVisitor();
		AttributedCharacterIterator itr = strVis.startVisit(getLogicalObject()).getIterator();
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
		openForm();
	}
	
	public final void openForm(){
		FormCreator creator = new FormCreator(this.getLogicalObject(), this.getContainer().getCanvasWindow());
		this.getContainer().getCanvasWindow().addContentAndSwitchTo(creator.getForm());
	}
	
	public final void openNewForm(){
		NewObjectFormCreator creator = new NewObjectFormCreator(this.getLogicalObject(), this.getContainer().getCanvasWindow());
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

	private final Text getTextObject() {
		return textObject;
	}

	private final void setTextObject(Text textObject) {
		this.textObject = textObject;
	}

}
