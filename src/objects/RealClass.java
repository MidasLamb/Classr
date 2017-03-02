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
	
	public HashSet<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(HashSet<Attribute> attributes) {
		this.attributes = attributes;
	}
	
	private HashSet<Attribute> attributes = new HashSet<>();
	
	public HashSet<Methode> getMethodes() {
		return methodes;
	}

	public void setMethodes(HashSet<Methode> methodes) {
		this.methodes = methodes;
	}
	
	private HashSet<Methode> methodes = new HashSet<>();
}
