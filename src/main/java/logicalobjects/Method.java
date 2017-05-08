package logicalobjects;

import static main.Constants.REGEX_START_NO_CAPITAL;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class of logical objects, involving a real class
 * 
 * @author team 11
 */
public class Method extends ClassContent {
	private Collection<Parameter> parameters;
	private boolean isAbstract;

	/**
	 * Constructs a new Method belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the Method belongs.
	 */
	public Method(LogicalClass rc) {
		super(rc);
		String originalName = "newMethod";
		String name = originalName;
		if (getRealClass().methodNameAlreadyExists(name, this)) {
			int i = 1;
			do {
				name = originalName + i;
				i++;
			} while (getRealClass().methodNameAlreadyExists(name, this));
		}
		this.setName(name);
		this.setType("void");
		this.setParameters(new ArrayList<Parameter>());
	}

	/**
	 * To get the parameters of this method
	 * 
	 * @return the parameters of this method
	 */
	public final Collection<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters of this method
	 * 
	 * @param parameters
	 *            the new parameters
	 */
	private final void setParameters(Collection<Parameter> parameters) {
		this.parameters = parameters;
	}

	/**
	 * To add a parameter to this method
	 * 
	 * @param p
	 *            the parameter that needs to be added
	 */
	public void addParameter(Parameter p) {
		this.parameters.add(p);
	}

	/**
	 * Removes a parameter from this method
	 * 
	 * @param p
	 *            the parameter that needs to be removed
	 */
	public void removeParameter(Parameter p) {
		this.parameters.remove(p);
	}

	/**
	 * Returns if this method is abstract
	 * 
	 * @return true if this method is abstract, false otherwise
	 */
	public boolean isAbstract() {
		return isAbstract;
	}

	/**
	 * @param isAbstract
	 *            the value for the isAbstract attribute
	 */
	public void setAbstract(boolean isAbstract) {
		if (this.isStatic() && isAbstract) {
			throw new IllegalStateException("Methods cannot be both static and abstract.");
		}
		this.isAbstract = isAbstract;
	}

	@Override
	public void setStatic(boolean isStatic) {
		if (this.isAbstract && isStatic) {
			throw new IllegalStateException("Methods cannot be both static and abstract.");
		} else {
			super.setStatic(isStatic);
		}
	}

	@Override
	public Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}

	@Override
	public boolean canHaveAsName(String name) {
		return (name.matches(REGEX_START_NO_CAPITAL) && !getRealClass().methodNameAlreadyExists(name, this));
	}
}
