package formBuilder;

import static main.Constants.STANDARD_FONTMETRICS;

import gui.form.base.Displayable;
import logicalobjects.Parameter;

/**
 * A wrapper to be able to display a parameter in a ListBox
 */
public class ParameterWrapper implements Displayable {
	public Parameter parameter;

	/**
	 * Create a new ParameterWrapper.
	 * 
	 * @param p
	 *            Parameter that needs to be wrapped
	 */
	public ParameterWrapper(Parameter p) {
		this.setParameter(p);
	}

	@Override
	public String getDisplayableString() {
		return getParameter().getName() + ": " + getParameter().getType();
	}

	/**
	 * Get the Parameter of this ParameterWrapper
	 * 
	 * @return Parameter of this ParameterWrapper
	 */
	public final Parameter getParameter() {
		return parameter;
	}

	/**
	 * Set the Parameter of this ParameterWrapper
	 * 
	 * @param parameter
	 *            Parameter to set
	 */
	private final void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ParameterWrapper))
			return false;
		ParameterWrapper op = (ParameterWrapper) other;
		return op.getParameter().equals(getParameter());
	}

	@Override
	public int getHeight() {
		return STANDARD_FONTMETRICS.getHeight();
	}

	@Override
	public int getWidth() {
		return STANDARD_FONTMETRICS.stringWidth(getDisplayableString());
	}

}
