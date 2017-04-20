package logicalobjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * A class of real classes, involving associations, attributes and methods
 * 
 * @author team 11
 */
public class LogicalClass extends LogicalObject {
	private ArrayList<Attribute> attributes;
	private ArrayList<Method> methods;
	private HashSet<Association> associations;
	

	public LogicalClass() {
		super();
		this.setAttributes(new ArrayList<>());
		this.setMethods(new ArrayList<>());
		this.setAssociations(new HashSet<>());
	}

	/**
	 * Generates a new attribute belonging to this RealClass, adds it to the
	 * attributes of this RealClass and returns it.
	 * 
	 * @return The newly added Attribute
	 */
	public final Attribute addAttribute() {
		Attribute attr = new Attribute(this);
		this.attributes.add(attr);
		return attr;
	}

	/**
	 * Deletes a given attribute from the list of attributes.
	 * 
	 * @param attribute
	 *            The attribute to be deleted
	 * @throws NoSuchElementException
	 *             The attribute is not present
	 */
	void deleteAttribute(Attribute attribute) throws NoSuchElementException {
		if (!this.attributes.remove(attribute)) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Deletes a given child, being a logical object, from the appropriate list
	 * of logical objects.
	 * 
	 * @param object
	 *            The logical object to be deleted
	 * @throws NoSuchElementException
	 *             The logical object is not present
	 */

	public void deleteChild(LogicalObject child){
		new DeleteChildVisitor().startVisit(child);
	}

	/**
	 * Generates a new method belonging to this RealClass, adds it to the
	 * methods of this RealClass and returns it.
	 * 
	 * @return The newly added Method
	 */
	public final Method addMethod() {
		Method method = new Method(this);
		this.methods.add(method);
		return method;
	}

	/**
	 * Deletes a given method from the list of methods.
	 * 
	 * @param method
	 *            The method to be deleted
	 * @throws NoSuchElementException
	 *             The method is not present
	 */
	void deleteMethod(Method method) throws NoSuchElementException {
		if (!this.methods.remove(method)) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Adds the given association to the list of associations
	 * 
	 * @param association
	 *            The association to be added
	 */
	public final void addAssociation(Association association) {
		this.associations.add(association);
	}

	/**
	 * Deletes a given association from the list of associations.
	 * 
	 * @param association
	 *            The association to be deleted
	 * @throws NoSuchElementException
	 *             The association is not present
	 */

	public final void deleteAssociation(Association association) throws NoSuchElementException {
		if (!this.associations.remove(association)) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the collection of Attributes belonging to this RealClass
	 * 
	 * @return the collection of Attributes belonging to this RealClass
	 */
	public final Collection<Attribute> getAttributes() {
		return new ArrayList<>(attributes);
	}

	/**
	 * Sets the collection of Attributes belonging to this RealClass
	 * 
	 * @param attributes
	 *            the collection of Attributes to be set
	 */
	private void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Returns the collection of Methods belonging to this RealClass
	 * 
	 * @return the collection of Methods belonging to this RealClass
	 */
	public final Collection<Method> getMethods() {
		return new ArrayList<>(this.methods);
	}

	/**
	 * Sets the collection of Methods belonging to this RealClass
	 * 
	 * @param attributes
	 *            the collection of Methods to be set
	 */
	private void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

	/**
	 * Returns the collection of Associations belonging to this RealClass
	 * 
	 * @return the collection of Associations belonging to this RealClass
	 */
	public final HashSet<Association> getAssociations() {
		return new HashSet<>(this.associations);
	}

	/**
	 * Sets the collection of Associations belonging to this RealClass
	 * 
	 * @param attributes
	 *            the collection of Associations to be set
	 */
	private void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}

	@Override
	public final void onDelete() {
		for (Association a : this.getAssociations())
			a.delete();
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public Object accept(LogicalObjectVisitor v) {
		return v.visit(this);
	}

}
