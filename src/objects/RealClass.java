package objects;

import java.util.HashSet;
import java.util.NoSuchElementException;

public class RealClass {

	private String getClassName() {
		return className;
	}

	private void setClassName(String className) {
		this.className = className;
	}
	
	private String className;
	
	private void addAttributes(Attribute attribute) {
		this.attributes.add(attribute);
	}
	
	private void deleteAttribute(Attribute attribute) throws NoSuchElementException{
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
	
	private void addMethode(Methode methode) {
		this.methodes.add(methode);
	}
	
	private void deleteMethodes(Methode methode) throws NoSuchElementException{
		if(!this.methodes.remove(methode)){
			throw new NoSuchElementException();
		}
	}
	
	public HashSet<Methode> getMethodes() {
		return new HashSet<>(this.methodes);
	}

	private void setMethodes(HashSet<Methode> methodes) {
		this.methodes = methodes;
	}

	private HashSet<Methode> methodes = new HashSet<>();
	
	public HashSet<Association> getAssociations() {
		return new HashSet<>(this.associations);
	}
	
	private void addAssociation(Association association) {
		this.associations.add(association);
	}
	
	private void deleteAssociations(Association association) throws NoSuchElementException{
		if(!this.associations.remove(association)){
			throw new NoSuchElementException();
		}
	}

	private void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}
	
	private HashSet<Association> associations = new HashSet<>();

}
