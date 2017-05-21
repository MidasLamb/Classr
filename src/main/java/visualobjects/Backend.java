package visualobjects;

import java.util.HashMap;

import command.Command;
import command.Controller;
import command.CreateAttributeCommand;
import command.CreateClassCommand;
import command.CreateMethodCommand;
import formBuilder.FormCreator;
import guiToApplication.FormWrapper;
import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.Method;

class Backend {

	private static Controller controller;
	private static Container container;
	private static HashMap<LogicalObject, ContentBox> formsMap = new HashMap<>();

	public static void initialize(Container container, Controller controller) {
		Backend.setContainer(container);
		Backend.setController(controller);
	}

	private static final Controller getController() {
		return controller;
	}

	private static final void setController(Controller controller) {
		if (Backend.controller == null)
			Backend.controller = controller;
	}

	private static final Container getContainer() {
		return container;
	}

	private static final void setContainer(Container container) {
		if (Backend.container == null)
			Backend.container = container;
	}

	public static final void createClass() {
		getController().executeCommand(new CreateClassCommand(getContainer()));
	}

	public static final void addAttribute() {
		if (canAddAttribute()) {
			Command c = new CreateAttributeCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}

	}

	public static final void addMethod() {
		if (canAddMethod()) {
			Command c = new CreateMethodCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}
	}

	public static final void addParameter() {
		// TODO
	}

	public static final void editName() {
		if (canEditName()) {
			//TODO a bit much coupling? Maybe give visualClass a "setEditable"?
			if (getContainer().getSelected() instanceof VisualClass) {
				EditableTextWrapper<?> e = ((VisualClass) getContainer().getSelected()).getName().getContent();
				getContainer().switchSelectedTo(e);
				e.setEditable();
			} else {
				((EditableTextWrapper<?>) getContainer().getSelected()).setEditable();
			}
		}
	}

	public static final void editTripleDot() {
		if (canEditTripleDot()) {
			createForm();
		}
	}

	public static final void delete() {
		if (canDelete()) {
			// TODO use command
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
		// return getContainer().getSelected() instanceof EditableTextWrapper;
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
	 * 
	 * @return Returns true if a undo operation can be executed, false
	 *         otherwise.
	 */
	public static final boolean canUndo() {
		return getController().canUndo();
	}

	/**
	 * Indicates whether an redo operation can be executed.
	 * 
	 * @return Returns true if a redo operation can be executed, false
	 *         otherwise.
	 */
	public static final boolean canRedo() {
		return getController().canRedo();
	}

	/**
	 * Get the HashMap with LogicalObjects and corresponding Forms
	 * 
	 * @return
	 */
	private static final HashMap<LogicalObject, ContentBox> getFormsMap() {
		return formsMap;
	}

	/**
	 * Create a new form for the selected object in Container, if no form
	 * already exists. Otherwise bring the existing form to the front.
	 */
	private static final void createForm() {
		LogicalObject logicalObject = getContainer().getSelected().getLogicalObject();
		if (!getFormsMap().containsKey(logicalObject)) {
			ContentBox b = new ContentBox(10, 10, 0, 300, 300, getContainer(), getController(), "Dialog Box");
			FormWrapper formWrapper = new FormCreator(logicalObject, b, getController()).getForm();
			b.setContent(formWrapper);
			logicalObject.addDeleteListener(b);
			getFormsMap().put(logicalObject, b);
			b.addDeleteListener(new DeleteListener() {

				@Override
				public void getNotifiedSubjectDeleted(DeleteSubject subject) {
					assert (Backend.getFormsMap().containsValue(subject));
					getFormsMap().values().remove(subject);
				}

			});
		} else {
			getContainer().bringToFront(getFormsMap().get(logicalObject));
		}
	}
}
