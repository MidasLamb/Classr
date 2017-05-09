package logicalobjects;
import static main.Constants.REGEX_START_NO_CAPITAL;
import static main.Constants.*;

/**
 * A class of associations, involving two real classes
 * 
 * @author team 11
 */
public class Association extends LogicalObject{
	private LogicalClass class1;
	private LogicalClass class2;

	/**
	 * Constructs a new Association within the two stated RealClasses.
	 * 
	 * @param class1
	 *            The First RealClass which is linked by the association.
	 * @param class2
	 *            The other RealClass which is linked by the association.
	 */
	public Association(LogicalClass class1, LogicalClass class2) {
		setClass1(class1);
		setClass2(class2);
		this.getClass1().addAssociation(this);
		this.getClass2().addAssociation(this);
		this.setName(DEFAULT_ASS_NAME);
	}
	

	/**
	 * Removes this association from it's participants.
	 */
	public final void remove() {
		this.getClass1().deleteAssociation(this);
		this.getClass2().deleteAssociation(this);
	}

	@Override
	public final void onDelete() {
		getClass1().deleteAssociation(this);
		if (!getClass1().equals(getClass2()))
			getClass2().deleteAssociation(this);
	}

	/**
	 * Returns the first RealClass belonging to this Association
	 * 
	 * @return the first RealClass belonging to this Association
	 */
	public final LogicalClass getClass1() {
		return class1;
	}

	/**
	 * Sets the first RealClass belonging to this Association
	 * 
	 * @param class1
	 *            the RealClass to be set
	 */
	private void setClass1(LogicalClass class1) {
		this.class1 = class1;
	}

	/**
	 * Returns the second RealClass belonging to this Association
	 * 
	 * @return the second RealClass belonging to this Association
	 */
	public final LogicalClass getClass2() {
		return class2;
	}

	/**
	 * Sets the second RealClass belonging to this Association
	 * 
	 * @param class1
	 *            the RealClass to be set
	 */
	private void setClass2(LogicalClass class2) {
		this.class2 = class2;
	}


	@Override
	Object accept(LogicalObjectVisitor<?> v) {
		return v.visit(this);
	}


	@Override
	public boolean canHaveAsName(String name) {
		return name.matches(REGEX_START_NO_CAPITAL);
	}
	
}
