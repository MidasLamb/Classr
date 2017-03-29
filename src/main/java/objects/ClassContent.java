package objects;

public abstract class ClassContent extends LogicalObject {
	private RealClass realClass;

	/**
	 * Constructs a new ClassContent belonging to the stated RealClass.
	 * 
	 * @param rc
	 *            The RealClass to which the ClassContent belongs.
	 */
	public ClassContent(RealClass rc) {
		this.setRealClass(rc);
	}

	/**
	 * Returns the RealClass belonging to this ClassContent
	 * 
	 * @return the RealClass belonging to this ClassContent
	 */
	public final RealClass getRealClass() {
		return realClass;
	}

	/**
	 * Sets the RealClass belonging to this ClassContent
	 * 
	 * @param rc
	 *            the RealClass to be set
	 */
	private void setRealClass(RealClass rc) {
		this.realClass = rc;
	}

	@Override
	public void onDelete() {
		getRealClass().deleteChild(this);
	}
}
