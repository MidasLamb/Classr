package objects;

import visualobjects.VisualObject;

public class Logical_objects {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private String name;
	
	private VisualObject visualObject;
	
	public void setVisualObject(VisualObject vo){
		this.visualObject = vo;
	}
	
	public VisualObject getVisualObject(){
		return this.visualObject;
	}
}
