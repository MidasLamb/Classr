package command;

import formBuilder.FormCreator;
import logicalobjects.LogicalObject;
import visualobjects.Container;
import visualobjects.ContentBox;

public class OpenFormCommand extends Command {
	private final boolean isNew;
	private final LogicalObject logicalObject;
	private final Container container;
	private final Controller controller;
	private ContentBox contentBox;

	public OpenFormCommand(LogicalObject o, boolean isNew, Container container,
			Controller controller) {
		this.logicalObject = o;
		this.isNew = isNew;
		this.container = container;
		this.controller = controller;
	}

	@Override
	void execute() {
		if(contentBox != null)
			throw new IllegalStateException("Cannot redo this command");
		ContentBox b = new ContentBox(10, 10, 0, 200, 200, getContainer(), getController());
		FormCreator creator = new FormCreator(getLogicalObject(), b, isNew());
		b.setContent(creator.getForm());
		setContentBox(b);
	}

	@Override
	void unexecute() {
		if(getContentBox() != null){
			getContentBox().close();
			setContentBox(null);
		}
	}

	@Override
	void cleanup() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Returns if the form that needs to be created is new
	 * @return if the form that needs to be created is new
	 */
	private boolean isNew() {
		return isNew;
	}

	/**
	 * Returns the logical object for which the form needs to be created
	 * @return the logical object for which the form needs to be created
	 */
	private LogicalObject getLogicalObject() {
		return logicalObject;
	}

	/**
	 * Returns the container in which the form needs to be opened
	 * @return the container in which the form needs to be opened
	 */
	private Container getContainer() {
		return container;
	}

	/**
	 * 
	 * @return
	 */
	private Controller getController() {
		return controller;
	}

	/**
	 * Returns the created contentBox
	 * @return the created contentbox
	 */
	public ContentBox getContentBox() {
		return contentBox;
	}

	/**
	 * To set the created contentbox
	 * @param 	contentBox
	 * 			the created contentBox
	 */
	public void setContentBox(ContentBox contentBox) {
		this.contentBox = contentBox;
	}

}
