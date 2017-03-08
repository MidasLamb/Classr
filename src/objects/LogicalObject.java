package objects;

import visualobjects.VisualObject;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public class LogicalObject {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;

	public void setVisualObject(VisualObject vo) {
		this.visualObject = vo;
	}

	public VisualObject getVisualObject() {
		return this.visualObject;
	}

	private VisualObject visualObject;
}
