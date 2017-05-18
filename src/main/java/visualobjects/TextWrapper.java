package visualobjects;

import static main.Constants.MAX_TEXT_WIDTH;
import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_TEXT_HEIGHT;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import command.Controller;
import formBuilder.FormCreator;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.SingleClick;
import gui.text.Text;
import gui.text.state.PassiveState;
import guiToApplication.FormWrapper;
import logicalobjects.LogicalObject;

/**
 * A wrapper to use the Text box from the GUI as a VisualObject
 */
public class TextWrapper extends VisualObject {
	private Text textObject;

	/**
	 * 
	 * @param 	x
	 * 			the x-coordinate
	 * @param 	y
	 * 			the y-coordinate
	 * @param 	z
	 * 			the z-coordinate
	 * @param 	parent
	 * 			the parent of this visualObject
	 * @param 	object
	 * 			the corresponding logicalObject
	 */
	public TextWrapper(int x, int y, int z, VisualObject parent, LogicalObject object, Controller controller) {
		super(x, y, z, MAX_TEXT_WIDTH, STANDARD_TEXT_HEIGHT, parent, controller);
		setLogicalObject(object);
		this.setTextObject(new Text(new AttributedString(""), new PassiveState()));
	}

	/**
	 * Shows the text frame
	 */
	@Override
	public void draw(Graphics g) {	
		// Other things can change the logical object, so check if it has changed.
		this.getTextObject().setAttributedText(getText());
		this.getTextObject().draw(g, this.getX(), this.getY());
	}
	
	/**
	 * @return Returns the text of the Logical Object
	 */
	protected AttributedString getText(){
		StringVisitor strVis = new StringVisitor();
		return strVis.startVisit(this.getLogicalObject());
	}
	
	/**
	 * @return the text inside the wrapper
	 */
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
	
	/**
	 * Opens the form to edit this text
	 */
	public final void openForm(){
		Backend.editTripleDot();
	}
	
	/**
	 * Opens the form to add new text
	 */
	public final void openNewForm(){
		ContentBox b = new ContentBox(10, 10, 0, 300, 300, getContainer(), getController(), "Dialog Box");
		FormCreator creator = new FormCreator(getLogicalObject(), b, true);
		b.setContent(creator.getForm());
		getLogicalObject().addDeleteListener(b);
	}
	
	@Override
	int getWidth(){
		return STANDARD_FONTMETRICS.stringWidth(getString());
	}
	
	@Override
	int getHeight(){
		return STANDARD_TEXT_HEIGHT;
	}

	/**
	 * 
	 * @return the corresponding textObject
	 */
	protected final Text getTextObject() {
		return textObject;
	}

	/**
	 * Sets the textObject
	 * @param 	textObject
	 * 			The new textObject
	 */
	protected final void setTextObject(Text textObject) {
		this.textObject = textObject;
	}

}
