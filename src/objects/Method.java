package objects;

public class Method extends LogicalObject {
	public Method(RealClass rc){
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
