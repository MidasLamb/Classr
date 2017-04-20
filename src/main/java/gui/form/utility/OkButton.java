package gui.form.utility;

import java.util.ArrayList;
import java.util.Collection;

import gui.form.base.Button;

/**
 * OK Button that can be added to Forms. The OK Button can check constraints.
 */
public abstract class OkButton extends Button implements Checker {
	private Collection<Checkable> constraints;

	/**
	 * Create a new OkButton with the given coordinates and dimensions.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            width
	 * @param height
	 *            height
	 */
	public OkButton(int x, int y, int width, int height) {
		super("Ok", x, y, width, height);
		this.constraints = new ArrayList<Checkable>();
	}

	/**
	 * Check the constraints and execute onOk().
	 */
	@Override
	protected void onAction() {
		this.checkConstraints();
		this.onOk();
	}

	/**
	 * Implement to adjust the desired operations executed when the OkButton is
	 * triggered.
	 */
	protected abstract void onOk();

	@Override
	public void addCheckableConstraint(Checkable c) {
		this.constraints.add(c);
		this.checkConstraints();
		c.addChecker(this);
	}

	@Override
	public void checkConstraints() {
		for (Checkable c : constraints) {
			if (!c.check()) {
				this.setEnabled(false);
				return;
			}
		}
		this.setEnabled(true);
	}
}
