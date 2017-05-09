package visibilities;

public enum Visibility {
	PACKAGE("~"), PRIVATE("-"), PROTECTED("#"), PUBLIC("+");
	
	private final String umlRepresentation;

	private Visibility(String umlRepresentation) {
		this.umlRepresentation = umlRepresentation;
	}
	
	public String getUMLRepresentation() {
		return this.umlRepresentation;
	}
}
