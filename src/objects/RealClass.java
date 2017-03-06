package objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

import visualobjects.visualclass.VisualClass;

public class RealClass extends Logical_objects {
	private VisualClass visualClass;
	
	public RealClass(VisualClass vc){
		this.setVisualClass(vc);
	}
	
	public Attribute addAttribute() {
		Attribute attr = new Attribute();
		this.attributes.add(attr);
		return attr;
	}
	
	private void deleteAttribute(Attribute attribute) throws NoSuchElementException{
		if(!this.attributes.remove(attribute)){
			throw new NoSuchElementException();
		}
	}
	
	public void deleteChild(Attribute attr){
		deleteAttribute(attr);
	}
	
	public void deleteChild(Method method){
		deleteMethod(method);
	}
	
	public void deleteChild(Association ass){
		deleteAssociation(ass);
	}
	
	public void deleteChild(Logical_objects object){
		throw new IllegalStateException();
	}
	
	public Collection<Attribute> getAttributes() {
		return new ArrayList<>(attributes);
	}

	private void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	private ArrayList<Attribute> attributes = new ArrayList<>();
	
	public Method addMethod() {
		Method method = new Method();
		this.methods.add(method);
		return method;
	}
	
	private void deleteMethod(Method method) throws NoSuchElementException{
		if(!this.methods.remove(method)){
			throw new NoSuchElementException();
		}
	}
	
	public Collection<Method> getMethods() {
		return new ArrayList<>(this.methods);
	}

	private void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

	private ArrayList<Method> methods = new ArrayList<>();
	
	HashSet<Association> getAssociations() {
		return new HashSet<>(this.associations);
	}
	
	public void addAssociation(Association association) {
		this.associations.add(association);
	}
	
	public void deleteAssociation(Association association) throws NoSuchElementException{
		if(!this.associations.remove(association)){
			throw new NoSuchElementException();
		}
	}

	private void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}
	
	private HashSet<Association> associations = new HashSet<>();

	VisualClass getVisualClass() {
		return visualClass;
	}

	private void setVisualClass(VisualClass visualClass) {
		this.visualClass = visualClass;
	}

}
