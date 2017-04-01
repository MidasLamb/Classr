package logicalobjects;

import interfaces.LogicalObjectVisitor;

public abstract class ClassContent extends LogicalObject{
	private LogicalClass realClass;

	/**
	 * Constructs a new ClassContent belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the ClassContent belongs.
	 */
	public ClassContent(LogicalClass rc) {
		this.setRealClass(rc);
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
	
}
