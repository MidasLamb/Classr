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
}
