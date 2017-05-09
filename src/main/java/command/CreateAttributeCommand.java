package command;

import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualClass;

public class CreateAttributeCommand extends Command {

	private final VisualClass visualClass;
	private PaddingBox<TextWrapper> attributePaddingBox;
	
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

	private final PaddingBox<TextWrapper> getAttributePaddingBox() {
		return attributePaddingBox;
	}

	private final void setAttributePaddingBox(PaddingBox<TextWrapper> attributePaddingBox) {
		this.attributePaddingBox = attributePaddingBox;
	}

	private final VisualClass getVisualClass() {
		return visualClass;
	}

}
