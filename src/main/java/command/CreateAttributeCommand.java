package command;

import decoupling.Decoupler;
import decoupling.PaddingBoxDecoupler;
import logicalobjects.Attribute;
import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

/**
 * A command for creating an attribute in a class
 */
public class CreateAttributeCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<Attribute, TextWrapper<Attribute>> attributePaddingBox;
	private Decoupler decoupler;

	/**
	 * The constructor
	 * 
	 * @param visualClass
	 *            the visual class in which the attribute needs to be created
	 */
	public CreateAttributeCommand(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

	@Override
	void execute() {
		if (getDecoupler() == null) {
			setAttributePaddingBox(this.getVisualClass().createAttribute());
			setDecoupler(new PaddingBoxDecoupler(getAttributePaddingBox()));
		} else {
			getDecoupler().recouple();
		}
	}

	@Override
	void unexecute() {
		if (getDecoupler() != null)
			getDecoupler().decouple();
	}

	/**
	 * Returns the padding box in which the created attribute is
	 * 
	 * @return the padding box in which the created attribute is
	 */
	private final PaddingBox<Attribute, TextWrapper<Attribute>> getAttributePaddingBox() {
		return attributePaddingBox;
	}

	/**
	 * Sets the padding box in which the created attribute is
	 * 
	 * @param attributePaddingBox
	 *            the padding box in which the created attribute is
	 */
	private final void setAttributePaddingBox(PaddingBox<Attribute, TextWrapper<Attribute>> attributePaddingBox) {
		this.attributePaddingBox = attributePaddingBox;
	}

	/**
	 * Returns the visual class in which the attribute is
	 * 
	 * @return the visual class in which the attribute
	 */
	private final VisualClass getVisualClass() {
		return visualClass;
	}

	/**
	 * Returns the decoupler for the attribute
	 * 
	 * @return the decoupler for the attribute
	 */
	private Decoupler getDecoupler() {
		return decoupler;
	}

	/**
	 * Sets the new decoupler for the attribute
	 * 
	 * @param decoupler
	 *            the new decoupler for the attribute
	 */
	private void setDecoupler(Decoupler decoupler) {
		this.decoupler = decoupler;
	}

}
