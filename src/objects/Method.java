package objects;

public class Method extends LogicalObject {
	public Method(RealClass rc){
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
