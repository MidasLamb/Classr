package formBuilder;

import static main.Constants.CONTAINER_HEIGHT;
import static main.Constants.CONTAINER_WIDTH;

import canvaswindow.MyCanvasWindow;
import gui.utility.FormBuilder;
import gui.utility.RegexCheckedInputBox;
import guiToApplication.FormWrapper;
import logicalobjects.Attribute;
import logicalobjects.Method;

public class MethodFormBuilder extends FormBuilder<FormWrapper> {
	private Method method;
	private MyCanvasWindow window;
	
	public MethodFormBuilder(Method method, MyCanvasWindow window) {
		this.method = method;
		this.window = window;
		
	}

	@Override
	protected void buildForm() {
		this.setForm(new FormWrapper(CONTAINER_WIDTH, CONTAINER_HEIGHT, this.window));

		RegexCheckedInputBox methName = new RegexCheckedInputBox(".*", 10, 10, 100, 16){
			@Override
			public void onAction() {
				// TODO Should check conditions
			}
		};
		this.addFormObject(methName);
		this.addLabelToTopOfLastFormObject("Method name");

		RegexCheckedInputBox methType = new RegexCheckedInputBox(".*", 10, 100, 100, 16) {
			@Override
			public void onAction() {
				// TODO Should check conditions
			}
		};
		
		this.addFormObject(methType);
		this.addLabelToTopOfLastFormObject("Method name");
	}

}
