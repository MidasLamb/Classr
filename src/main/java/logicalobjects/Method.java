package logicalobjects;

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
		this.setName("New method");
		this.setType("void");
		this.setParameters(new ArrayList<Parameter>());
	}

	/**
	 * To get the parameters of this methode
	 * @return	the parameters of this methode
	 */
	public final Collection<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters of this methode
	 * @param 	parameters
	 * 			the new parameters
	 */
	private final void setParameters(Collection<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * To add a parameter to this methode
	 * @param 	p
	 * 			the parameter that needs to be added
	 */
	public void addParameter(Parameter p){
		this.parameters.add(p);
	}
	
	/**
	 * Removes a parameter from this methode
	 * @param	p
	 * 			the parameter that needs to be removed
	 */
	public void removeParameter(Parameter p){
		this.parameters.remove(p);
	}

	/**
	 * Returns if this methode is abstract
	 * @return true if this methode is abstract, false otherwise
	 */
	public boolean isAbstract() {
		return isAbstract;
	}

	/**
	 * @param 	isAbstract
	 * 			the value for the isAbstract attribute
	 */
	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	

	@Override
	public Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}
}
