package logicalobjects;

public abstract class ClassContent extends LogicalObject{
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
		this.setType("void");
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

	public final Visibility getVisibility() {
		return visibility;
	}

	public final void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

	public final boolean isStatic() {
		return isStatic;
	}

	public final void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
}
