package objects;

import java.util.HashSet;

public class RealClass {

	private String getClassName() {
		return className;
	}

	private void setClassName(String className) {
		this.className = className;
	}
	
	private String className;
	
	private void addAttributes(Attributes attribute) {
		this.attributes.add(attribute);
	}
	
	private void deleteAttribute(Attributes attribute){
		
	}
	
	private HashSet<Attributes> getAttributes() {
		return attributes;
	}

	private void setAttributes(HashSet<Attributes> attributes) {
		this.attributes = attributes;
	}
	
	private HashSet<Attributes> attributes = new HashSet<>();
	
	public HashSet<Methodes> getMethodes() {
		return methodes;
	}

	public void setMethodes(HashSet<Methodes> methodes) {
		this.methodes = methodes;
	}

	private HashSet<Methodes> methodes = new HashSet<>();
	
	public HashSet<Association> getAssociations() {
		return associations;
	}

	public void setAssociations(HashSet<Association> associations) {
		this.associations = associations;
	}
	
	private HashSet<Association> associations = new HashSet<>();
}
