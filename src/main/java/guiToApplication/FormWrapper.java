package guiToApplication;

import java.awt.Graphics;

import gui.form.base.Form;
import gui.form.base.FormContainer;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.SingleClick;
import gui.inputHandlers.keys.AsciiKey;
import gui.inputHandlers.keys.FunctionKey;
import interfaces.CanvasContent;

/**
 * A bridge to let a form be displayed in a canvas window
 */
public class FormWrapper extends Form implements CanvasContent {
	private FormContainer<?> formContainer;

	/**
	 * @param width
	 *            the width of the form
	 * @param height
	 *            the heigth of the form
	 * @param formContainer
	 *            the container for this form
	 */
	public FormWrapper(int width, int height, FormContainer<?> formContainer) {
		super(width, height);
		this.formContainer = formContainer;
	}

	@Override
	public void onClick(SingleClick click) {
		super.onClick(click);
	}

	@Override
	public void onDoubleClick(DoubleClick click) {
		super.onDoubleClick(click);

	}

	@Override
	public void onDragEnd(Drag drag) {
		super.onDragEnd(drag);
	}

	@Override
	public void show(Graphics g) {
		super.draw(g);
	}

	/**
	 * Closes this form.
	 */
	public void close() {
		this.getFormContainer().close();
	}

	/**
	 * Returns the FormContainer
	 * 
	 * @return formContainer
	 */
	public FormContainer<?> getFormContainer() {
		return this.formContainer;
	}

	@Override
	public void handleAsciiKey(AsciiKey key) {
		super.handleAsciiKey(key);

	}

	@Override
	public void handleFunctionKey(FunctionKey key) {
		super.handleFunctionKey(key);
	}

	@Override
	public void clearFocus() {
		super.clearFocus();
	}

}
