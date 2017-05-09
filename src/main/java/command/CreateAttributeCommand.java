package command;

import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

/**
 * A command for creating an attribute in a class
 */
public class CreateAttributeCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<TextWrapper> attributePaddingBox;
	
	/**
	 * The constructor
	 * @param 	visualClass
	 * 			the visual class in which the attribute needs to be created
	 */
	public CreateAttributeCommand(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

	@Override
	void execute() {
		setAttributePaddingBox(this.getVisualClass().createAttribute());
	}

	@Override
	void unexecute() {
		this.getAttributePaddingBox().delete();
	}

	/**
	 * Returns the padding box in which the created attribute is
	 * @return the padding box in which the created attribute is
	 */
	private final PaddingBox<TextWrapper> getAttributePaddingBox() {
		return attributePaddingBox;
	}

	/**
	 * Sets the padding box in which the created attribute is
	 * @param 	attributePaddingBox
	 * 			the padding box in which the created attribute is
	 */
	private final void setAttributePaddingBox(PaddingBox<TextWrapper> attributePaddingBox) {
		this.attributePaddingBox = attributePaddingBox;
	}

	/**
	 * Returns the visual class in which the attribute is
	 * @return the visual class in which the attribute
	 */
	private final VisualClass getVisualClass() {
		return visualClass;
	}

}
