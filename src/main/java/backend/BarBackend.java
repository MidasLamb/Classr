package backend;

import command.Controller;
import visualobjects.Container;

public class BarBackend {
	
	private static Controller controller;
	private static Container container;

	public BarBackend(Container container, Controller controller) {
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

}
