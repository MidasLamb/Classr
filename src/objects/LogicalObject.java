package objects;

import visualobjects.VisualObject;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public class LogicalObject {
	
	/**
	 * Returns the name of this LogicalObject
	 * 
	 * @return	the name of this LogicalObject
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this LogicalObject
	 * 
	 * @param 	name
	 * 			the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String name;

	/**
	 * Sets the VisualObject belonging to this LogicalObject
	 * 
	 * @param 	vo
	 * 			the VisualObject to be set
	 */
	public void setVisualObject(VisualObject vo) {
		this.visualObject = vo;
	}

	/**
	 * Returns the VisualObject belonging to this LogicalObject
	 * 
	 * @return	the VisualObject belonging to this LogicalObject
	 */
	public VisualObject getVisualObject() {
		return this.visualObject;
	}

	private VisualObject visualObject;
}
