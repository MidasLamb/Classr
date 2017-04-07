package formBuilder;

import gui.base.Displayable;
import logicalobjects.Parameter;

public class ParameterWrapper implements Displayable {
	public Parameter parameter;
	public ParameterWrapper(Parameter p){
		this.setParameter(p);
	}

	@Override
	public String getDisplayableString() {
		return getParameter().getName();
	}

	public final Parameter getParameter() {
		return parameter;
	}

	private final void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
