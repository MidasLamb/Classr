package objects;

public class Attribute extends LogicalObject {
	public Attribute(RealClass rc) {
		this.setRealClass(rc);
	}

	public RealClass getRealClass() {
		return realClass;
	}

	private void setRealClass(RealClass rc) {
		this.realClass = rc;
	}

	private RealClass realClass;
}
