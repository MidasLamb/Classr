package logicalobjects;

import static main.Constants.REGEX_ALPHANUMERIC_UNDERSCORE;

import visibilities.Visibility;

/**
 * An object that is content of a class
 */
public abstract class ClassContent extends LogicalObject {
	private LogicalClass realClass;
	private Visibility visibility;
	private String type;
	private boolean isStatic;

	/**
	 * Constructs a new ClassContent belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the ClassContent belongs.
	 */
	public ClassContent(LogicalClass rc) {
		this.setRealClass(rc);
		this.setVisibility(Visibility.PRIVATE);
	}

	/**
	 * Returns the RealClass belonging to this ClassContent
	 * 
	 * @return the RealClass belonging to this ClassContent
	 */
	public final LogicalClass getRealClass() {
		return realClass;
	}

	/**
	 * Sets the RealClass belonging to this ClassContent
	 * 
	 * @param rc
	 *            the RealClass to be set
	 */
	private void setRealClass(LogicalClass rc) {
		this.realClass = rc;
	}

	@Override
	public final void onDelete() {
		getRealClass().deleteChild(this);
	}

	/**
	 * @return the visibility of this object
	 */
	public final Visibility getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            The new visibility for this object
	 */
	public final void setVisibility(Visibility visibility) {
		this.visibility = visibility;
		notifyUpdateListeners();
	}

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * Sets the type
	 * 
	 * @param type
	 *            the new type
	 */
	public final void setType(String type) {
		this.type = type;
		notifyUpdateListeners();
	}

	/**
	 * @return True if it is static otherwise false
	 */
	public final boolean isStatic() {
		return isStatic;
	}

	/**
	 * Changes the isStatic attribute
	 * 
	 * @param isStatic
	 *            The new value for the isStatic attribute
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
		notifyUpdateListeners();
	}

	/**
	 * Returns whether or not this ClassContent object can have the stated type
	 * string as its type name
	 * 
	 * @param type
	 *            String to be checked
	 * @return boolean indicating whether the given type is valid
	 */
	public boolean canHaveAsType(String type) {
		return type.matches(REGEX_ALPHANUMERIC_UNDERSCORE);
	}

	/**
	 * Check if this visibility can be set for this ClassContent.
	 * 
	 * @param visibility
	 *            visibility to be checked
	 * @return true
	 */
	public boolean canHaveAsVisibility(Visibility visibility) {
		return true;
	}

	/**
	 * Check if this ClassContent can be set static
	 * 
	 * @param isStatic
	 *            value that needs to be checked
	 * @return true if this ClassContent can be static, false otherwise
	 */
	public abstract boolean canBeStatic(boolean isStatic);
}
