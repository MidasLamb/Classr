package objects;

/**
 * A class of logical objects, involving a name and visual object
 * 
 * @author team 11
 */
public abstract class LogicalObject {
	private boolean isDeleted = false;

	private String name;

	/**
	 * Returns the name of this LogicalObject
	 * 
	 * @return the name of this LogicalObject
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this LogicalObject
	 * 
	 * @param name
	 *            the name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void delete(){
		if (this.isDeleted()){
			System.out.println("This object is already deleted!");
		} else {
			this.onDelete();
		}
		this.setDeleted(true);
	}
	
	

	protected abstract void onDelete();
	
	public boolean isDeleted(){
		return this.isDeleted;
	}

	private void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
