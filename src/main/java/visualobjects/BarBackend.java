package visualobjects;

import command.Controller;

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
	
	public static final void createClass(){
		//TODO
	}
	
	public static final void addAttribute(){
		//TODO
	}
	
	public static final void addMethod(){
		//TODO
	}
	
	public static final void addParameter(){
		//TODO
	}
	
	public static final void editName(){
		//TODO
	}
	
	public static final void editTripleDot(){
		//TODO
	}
	
	public static final void delete(){
		//TODO
	}
	
	public static final void undo(){
		getController().undo();
	}
	
	public static final void redo(){
		getController().redo();
	}
	
	public static final boolean canAddAttribute(){
		//TODO
		return false;
	}
	
	public static final boolean canAddMethod(){
		//TODO
		return false;
	}
	public static final boolean canAddParameter(){
		//TODO
		return false;
	}
	public static final boolean canEditName(){
		//TODO
		return false;
	}
	public static final boolean canEditTripleDot(){
		//TODO
		return false;
	}
	public static final boolean canDelete(){
		//TODO
		return false;
	}
	
	

}
