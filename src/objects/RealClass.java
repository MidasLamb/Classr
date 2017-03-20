package objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * A class of real classes, involving associations, attributes and methods
 * 
 * @author team 11
 */
public class RealClass extends LogicalObject {
	
	/**
	 * Generates a new attribute belonging to this RealClass, adds it to the attributes of this RealClass and returns it.
	 * 
	 * @return	The newly added Attribute
	 */
	public Attribute addAttribute() {
		Attribute attr = new Attribute(this);
		this.attributes.add(attr);
		return attr;
	}

	/**
	 * Deletes a given attribute from the list of attributes.
	 * 
	 * @param 	attribute		
	 * 			The attribute to be deleted
	 * @throws 	NoSuchElementException
	 * 			The attribute is not present
	 */
	private void deleteAttribute(Attribute attribute) throws NoSuchElementException {
		if (!this.attributes.remove(attribute)) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Deletes a given child, being an attribute, from the list of attributes.
	 * 
	 * @param 	attr	
	 * 			The attribute to be deleted
	 * @throws 	NoSuchElementException
	 * 			The attribute is not present
	 */
	private void deleteChild(Attribute attr) throws NoSuchElementException{
		deleteAttribute(attr);
	}

	/**
	 * Deletes a given child, being a method, from the list of methods.
	 * 
	 * @param 	method	
	 * 			The method to be deleted
	 * @throws 	NoSuchElementException
	 * 			The method is not present
	 */
	public void deleteChild(Method method) throws NoSuchElementException{
		deleteMethod(method);
	}

	/**
	 * Deletes a given child, being an association, from the list of associations.
	 * 
	 * @param 	ass		
	 * 			The association to be deleted
	 * @throws 	NoSuchElementException
	 * 			The association is not present
	 */
	public void deleteChild(Association ass) throws NoSuchElementException{
		deleteAssociation(ass);
	}

	/**
	 * Deletes a given child, being a logical object, from the appropriate list of logical objects.
	 * 
	 * @param 	object		
	 * 			The logical object to be deleted
	 * @throws 	NoSuchElementException
	 * 			The logical object is not present
	 */
	public void deleteChild(LogicalObject object) throws NoSuchElementException,IllegalStateException{
		if (object instanceof Method) {
			this.deleteChild((Method) object);
			return;
		}

		if (object instanceof Association) {
			this.deleteChild((Association) object);
			return;
		}

		if (object instanceof Attribute) {
			this.deleteChild((Attribute) object);
			return;
		}

		throw new IllegalStateException();
	}

	/**
	 * Generates a new method belonging to this RealClass, adds it to the methods of this RealClass and returns it.
	 * 
	 * @return	The newly added Method
	 */
	public Method addMethod() {
		Method method = new Method(this);
		this.methods.add(method);
		return method;
	}

	/**
	 * Deletes a given method from the list of methods.
	 * 
	 * @param 	method		
	 * 			The method to be deleted
	 * @throws 	NoSuchElementException
	 * 			The method is not present
	 */
	private void deleteMethod(Method method) throws NoSuchElementException {
		if (!this.methods.remove(method)) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Adds the given association to the list of associations
	 * 
	 * @param	association	
	 * 			The association to be added
	 */
	public void addAssociation(Association association) {
		this.associations.add(association);
	}

	/**
	 * Deletes a given association from the list of associations.
	 * 
	 * @param 	association		
	 * 			The association to be deleted
	 * @throws 	NoSuchElementException
	 * 			The association is not present
	 */
	public void deleteAssociation(Association association) throws NoSuchElementException {
		if (!this.associations.remove(association)) {
			throw new NoSuchElementException();
		}
	}

	// getters and setters

	/**
	 * Returns the collection of Attributes belonging to this RealClass
	 * 
	 * @return the collection of Attributes belonging to this RealClass
	 */
	public Collection<Attribute> getAttributes() {
		return new ArrayList<>(attributes);
	}

	/**
	 * Sets the collection of Attributes belonging to this RealClass
	 * 
	 * @param 	attributes
	 * 			the collection of Attributes to be set
	 */
	private void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	private ArrayList<Attribute> attributes = new ArrayList<>();

	/**
	 * Returns the collection of Methods belonging to this RealClass
	 * 
	 * @return the collection of Methods belonging to this RealClass
	 */
	public Collection<Method> getMethods() {
		return new ArrayList<>(this.methods);
	}

	/**
	 * Sets the collection of Methods belonging to this RealClass
	 * 
	 * @param 	attributes
	 * 			the collection of Methods to be set
	 */
	private void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

	private ArrayList<Method> methods = new ArrayList<>();

	/**
	 * Returns the collection of Associations belonging to this RealClass
	 * 
	 * @return the collection of Associations belonging to this RealClass
	 */
	public HashSet<Association> getAssociations() {
		return new HashSet<>(this.associations);
	}

	/**
	 * Sets the collection of Associations belonging to this RealClass
	 * 
	 * @param 	attributes
	 * 			the collection of Associations to be set
	 */
	private void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}

	private HashSet<Association> associations = new HashSet<>();

	@Override
	public void delete() {
		//TODO		
	}

}
