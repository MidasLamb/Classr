package objects;

import java.util.HashSet;
import java.util.NoSuchElementException;

import visualobjects.visualclass.VisualClass;

public class RealClass {
	private VisualClass visualClass;
	
	public RealClass(VisualClass vc){
		this.setVisualClass(vc);
	}

	private String getClassName() {
		return className;
	}

	private void setClassName(String className) {
		this.className = className;
	}
	
	private String className;
	
	public void addAttributes(Attribute attribute) {
		this.attributes.add(attribute);
	}
	
	public void deleteAttribute(Attribute attribute) throws NoSuchElementException{
		if(!this.attributes.remove(attribute)){
			throw new NoSuchElementException();
		}
	}
	
	public HashSet<Attribute> getAttributes() {
		return new HashSet<>(attributes);
	}

	public void setAttributes(HashSet<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	private HashSet<Attribute> attributes = new HashSet<>();
	
	public void addMethod(Method method) {
		this.methods.add(method);
	}
	
	public void deleteMethods(Method method) throws NoSuchElementException{
		if(!this.methods.remove(method)){
			throw new NoSuchElementException();
		}
	}
	
	public HashSet<Method> getMethodes() {
		return new HashSet<>(this.methods);
	}

	private void setMethodes(HashSet<Method> methodes) {
		this.methods = methodes;
	}

	private HashSet<Method> methods = new HashSet<>();
	
	public HashSet<Association> getAssociations() {
		return new HashSet<>(this.associations);
	}
	
	public void addAssociation(Association association) {
		this.associations.add(association);
	}
	
	public void deleteAssociations(Association association) throws NoSuchElementException{
		if(!this.associations.remove(association)){
			throw new NoSuchElementException();
		}
	}

	private void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}
	
	private HashSet<Association> associations = new HashSet<>();

	private VisualClass getVisualClass() {
		return visualClass;
	}

	private void setVisualClass(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

}
