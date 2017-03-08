package objects;

import visualobjects.VisualObject;

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
