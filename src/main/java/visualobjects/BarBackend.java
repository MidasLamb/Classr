package visualobjects;

import command.Command;
import command.Controller;
import command.CreateAttributeCommand;
import command.CreateClassCommand;
import command.CreateMethodCommand;
import formBuilder.FormCreator;
import guiToApplication.FormWrapper;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.Method;

class BarBackend {

	private static Controller controller;
	private static Container container;

	public static void initialize(Container container, Controller controller) {
		BarBackend.setContainer(container);
		BarBackend.setController(controller);
	}

	private static final Controller getController() {
		return controller;
	}

	private static final void setController(Controller controller) {
		if (BarBackend.controller == null)
			BarBackend.controller = controller;
	}

	private static final Container getContainer() {
		return container;
	}

	private static final void setContainer(Container container) {
		if (BarBackend.container == null)
			BarBackend.container = container;
	}

	public static final void createClass() {
		getController().executeCommand(new CreateClassCommand(getContainer()));
	}

	public static final void addAttribute() {
		if (canAddAttribute()){	
			Command c = new CreateAttributeCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}
			
	}

	public static final void addMethod() {
		if (canAddMethod()){	
			Command c = new CreateMethodCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}
	}

	public static final void addParameter() {
		// TODO
	}

	public static final void editName() {
		// TODO
	}

	public static final void editTripleDot() {
		if (canEditTripleDot()) {
			LogicalObject logicalObject = getContainer().getSelected().getLogicalObject();
			ContentBox b = new ContentBox(10, 10, 0, 300, 300, getContainer(), getController(), "Dialog Box");
			FormWrapper formWrapper = new FormCreator(logicalObject, b, getController()).getForm();
			b.setContent(formWrapper);
			logicalObject.addDeleteListener(b);
		}
	}

	public static final void delete() {
		if (canDelete()){
			//TODO update with command
			getContainer().getSelected().delete();
		}
	}

	public static final void undo() {
		getController().undo();
	}

	public static final void redo() {
		getController().redo();
	}

	public static final boolean canAddAttribute() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected() instanceof VisualClass;
	}

	public static final boolean canAddMethod() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected() instanceof VisualClass;
	}

	public static final boolean canAddParameter() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof Method;
	}

	public static final boolean canEditName() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof Method
				|| getContainer().getSelected().getLogicalObject() instanceof Attribute
				|| getContainer().getSelected().getLogicalObject() instanceof LogicalClass;
	}

	public static final boolean canEditTripleDot() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof Method
				|| getContainer().getSelected().getLogicalObject() instanceof Attribute;
	}

	public static final boolean canDelete() {
		return getContainer().getSelected() != null;
	}
	
	/**
	 * Indicates whether an undo operation can be executed.
	 * @return Returns true if a undo operation can be executed, false otherwise.
	 */
	public static final boolean canUndo() {
		return getController().canUndo();
	}
	
	/**
	 * Indicates whether an redo operation can be executed.
	 * @return Returns true if a redo operation can be executed, false otherwise.
	 */
	public static final boolean canRedo() {
		return getController().canRedo();
	}

}
