package logicalobjects;

import java.util.ArrayList;
import java.util.Collection;

import interfaces.LogicalObjectVisitor;

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
		this.setParameters(new ArrayList<Parameter>());
	}


	public final Collection<Parameter> getParameters() {
		return parameters;
	}

	private final void setParameters(Collection<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(Parameter p){
		this.parameters.add(p);
	}
	
	public void removeParameter(Parameter p){
		this.parameters.remove(p);
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	

	@Override
	public Object accept(LogicalObjectVisitor v) {
		return v.visit(this);
	}
}
