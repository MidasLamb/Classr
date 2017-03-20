package objects;

/**
 * A class of associations, involving two real classes
 * 
 * @author team 11
 */
public class Association extends LogicalObject {

	/**
	 * Constructs a new Association within the two stated RealClasses. 
	 * 
	 * @param 	class1	
	 * 			The First RealClass which is linked by the association.
	 * @param 	class2	
	 * 			The other RealClass which is linked by the association.
	 */
	public Association(RealClass class1, RealClass class2) {
		setClass1(class1);
		setClass2(class2);
		this.getClass1().addAssociation(this);
		this.getClass2().addAssociation(this);
	}

	
	/**
	 * Removes this association from it's participants.
	 */
	public void remove() {
		this.getClass1().deleteAssociation(this);
		this.getClass2().deleteAssociation(this);
	}
	
	@Override
	public void delete() {
		getClass1().deleteAssociation(this);
		getClass2().deleteAssociation(this);
	}

	/**
	 * Returns the first RealClass belonging to this Association
	 * 	
	 * @return the first RealClass belonging to this Association
	 */
	public RealClass getClass1() {
		return class1;
	}

	/**
	 * Sets the first RealClass belonging to this Association
	 * 
	 * @param 	class1
	 * 			the RealClass to be set
	 */
	private void setClass1(RealClass class1) {
		this.class1 = class1;
	}

	private RealClass class1;

	/**
	 * Returns the second RealClass belonging to this Association
	 * 	
	 * @return the second RealClass belonging to this Association
	 */
	public RealClass getClass2() {
		return class2;
	}

	/**
	 * Sets the second RealClass belonging to this Association
	 * 
	 * @param 	class1
	 * 			the RealClass to be set
	 */
	private void setClass2(RealClass class2) {
		this.class2 = class2;
	}

	private RealClass class2;
}
