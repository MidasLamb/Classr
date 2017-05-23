package visualobjects;

import java.util.HashMap;

import command.AddParameterCommand;
import command.Command;
import command.Controller;
import command.CreateAttributeCommand;
import command.CreateClassCommand;
import command.CreateMethodCommand;
import command.DeleteVisualObjectCommand;
import formBuilder.FormCreator;
import formBuilder.MethodParameterFormBuilder;
import guiToApplication.FormWrapper;
import interfaces.DeleteListener;
import interfaces.DeleteSubject;
import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.Method;
import logicalobjects.Parameter;

/**
 * Collection of operations that are invoked from the menu bar and tool bar, or
 * by keystrokes and clicks or both.
 */
public class Backend {

	private static Controller controller;
	private static Container container;
	private static HashMap<LogicalObject, ContentBox> formsMap = new HashMap<>();

	/**
	 * Initialize the Backend with a Container and Controller.
	 * 
	 * @param container
	 *            Container for this Backend
	 * @param controller
	 *            Controller for this Backend
	 */
	public static void initialize(Container container, Controller controller) {
		Backend.setContainer(container);
		Backend.setController(controller);
	}

	/**
	 * Get the Controller for the Backend.
	 * 
	 * @return the controller
	 */
	private static final Controller getController() {
		return controller;
	}

	/**
	 * Set the Controller for the Backend.
	 * 
	 * @param controller
	 *            controller to be set as the Controller for the Backend
	 */
	private static final void setController(Controller controller) {
		if (Backend.controller == null)
			Backend.controller = controller;
	}

	/**
	 * Get the Container for the Backend
	 * 
	 * @return the container
	 */
	private static final Container getContainer() {
		return container;
	}

	/**
	 * Set the Container for the Backend
	 * 
	 * @param container
	 *            container to be set as the Container for the Backend
	 */
	private static final void setContainer(Container container) {
		if (Backend.container == null)
			Backend.container = container;
	}

	/**
	 * Create a new Class.
	 */
	public static final void createClass() {
		getController().executeCommand(new CreateClassCommand(getContainer()));
	}

	/**
	 * Add a new Attribute to the selected Class
	 */
	public static final void addAttribute() {
		if (canAddAttribute()) {
			Command c = new CreateAttributeCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}

	}

	/**
	 * Add a new Method to the selected Class
	 */
	public static final void addMethod() {
		if (canAddMethod()) {
			Command c = new CreateMethodCommand(((VisualClass) getContainer().getSelected()));
			getController().executeCommand(c);
		}
	}

	/**
	 * Add a new Parameter to the selected Method
	 */
	public static final void addParameter() {
		if (canAddParameter()) {
			Method method = (Method) getContainer().getSelected().getLogicalObject();
			Parameter p = new Parameter("name", "type");
			getController().executeCommand(new AddParameterCommand(method, p));
		}
	}

	/**
	 * Edit the name of the selected object, if it can be edited
	 */
	public static final void editName() {
		if (canEditName()) {
			((Editable) getContainer().getSelected()).setEditable();
		}
	}

	/**
	 * Open a form to edit the properties of the selected object, or edit the
	 * name in-place if no form can be opened.
	 */
	public static final void editTripleDot() {
		if (canEditTripleDotForm()) {
			createForm();
		} else if (canEditTripleDotName()) {
			editName();
		}

	}

	/**
	 * Delete the selected object, if the selected object can be deleted
	 */
	public static final void delete() {
		if (canDelete()) {
			DeleteVisualObjectCommand c = new DeleteVisualObjectCommand(getContainer().getContainer().getSelected());
			getController().executeCommand(c);
			getContainer().switchSelectedTo(null);
		}
	}

	/**
	 * Undo the last action
	 */
	public static final void undo() {
		getController().undo();
	}

	/**
	 * Redo the last action that is reverted
	 */
	public static final void redo() {
		getController().redo();
	}

	/**
	 * @return true if an attribute can be added to the selected object, false
	 *         otherwise
	 */
	public static final boolean canAddAttribute() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected() instanceof VisualClass;
	}

	/**
	 * @return true if a method can be added to the selected object, false
	 *         otherwise
	 */
	public static final boolean canAddMethod() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected() instanceof VisualClass;
	}

	/**
	 * @return true if a parameter can be added to the selected object, false
	 *         otherwise
	 */
	public static final boolean canAddParameter() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof Method;
	}

	/**
	 * @return true if the name of the selected object can be edited in-place,
	 *         false otherwise
	 */
	public static final boolean canEditName() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected() instanceof Editable;
		// return getContainer().getSelected() instanceof EditableTextWrapper;
	}

	/**
	 * @return true if the selected object can be edited in a form, or its name
	 *         can be edited in-place
	 */
	public static final boolean canEditTripleDot() {
		return canEditTripleDotForm() || canEditTripleDotName();
	}

	/**
	 * @return true if the selected object can be edited in a form, false
	 *         otherwise
	 */
	private static final boolean canEditTripleDotForm() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof Method
				|| getContainer().getSelected().getLogicalObject() instanceof Attribute;
	}

	/**
	 * @return true if the name of the selected object can be edited in-place,
	 *         false otherwise
	 */
	private static final boolean canEditTripleDotName() {
		if (getContainer().getSelected() == null || getContainer().getSelected().getLogicalObject() == null)
			return false;
		return getContainer().getSelected().getLogicalObject() instanceof LogicalClass
				|| getContainer().getSelected().getLogicalObject() instanceof Association;
	}

	/**
	 * @return true if the selected object can be deleted, false otherwise
	 */
	public static final boolean canDelete() {
		if (getContainer().getSelected() != null && getContainer().getSelected() instanceof Editable){
			if (((Editable)getContainer().getSelected()).isEditable())
				return false;
		}
		if (getContainer().getSelected() instanceof ContentBox)
			return false;
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
			ContentBox b = new ContentBox(100, 100, 1, 300, 300, getContainer(), getController(), "Dialog Box");
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
			getContainer().switchSelectedTo(getFormsMap().get(logicalObject));
		}
	}
	
	/**
	 * Creates a new form for the given parameter
	 * @param 	parameter
	 * 			the parameter for which the form needs to be created
	 */
	public static final void createForm(Parameter parameter) {	
		if(parameter != null){
			if (!getFormsMap().containsKey(parameter)) {
				ContentBox c = new ContentBox(100, 100, 1, 300, 300, getContainer(), getController(), "Edit Parameter");
				MethodParameterFormBuilder parabuilder = new MethodParameterFormBuilder(parameter, c, getController());
				c.switchTo(parabuilder.getForm());
				getFormsMap().put(parameter, c);
			} else {
				getContainer().bringToFront(getFormsMap().get(parameter));
			}
		}
	}
	
	/**
	 * Closes the form belonging to the given logical object
	 * @param 	lo
	 * 			the logical object for which the associated forms need to be closed
	 */
	public static void closeFormBelogingTo(LogicalObject lo){
		if(getFormsMap().containsKey(lo)){
			ContentBox form = getFormsMap().get(lo);
			form.close();
			getFormsMap().remove(lo);
		}
	}
}
