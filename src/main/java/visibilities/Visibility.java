package visibilities;

/**
 * Enumeration of all visibility levels of attributes and methods.
 */
public enum Visibility {
	PACKAGE("~"), PRIVATE("-"), PROTECTED("#"), PUBLIC("+");

	private final String umlRepresentation;

	private Visibility(String umlRepresentation) {
		this.umlRepresentation = umlRepresentation;
	}

	/**
	 * Get the UML representation in String format of this Visibility.
	 * 
	 * @return the UML representation in String format
	 */
	public String getUMLRepresentation() {
		return this.umlRepresentation;
	}
}
