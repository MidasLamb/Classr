package objects;

public class Attribute extends LogicalObject {
	public Attribute(RealClass rc){
		this.setRealClass(rc);
	}
	
	private RealClass rc;

	public RealClass getRealClass() {
		return rc;
	}

	private void setRealClass(RealClass rc) {
		this.rc = rc;
	}
}
