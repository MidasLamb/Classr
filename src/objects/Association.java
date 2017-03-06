package objects;

public class Association extends Logical_objects {
	
	public Association(RealClass class1, RealClass class2){
		setClass1(class1);
		setClass2(class2);
		this.getClass1().addAssociation(this);
		this.getClass2().addAssociation(this);
	}
	
	private RealClass getClass1() {
		return class1;
	}
	private void setClass1(RealClass class1) {
		this.class1 = class1;
	}
	
	private RealClass class1;

	private RealClass getClass2() {
		return class2;
	}
	private void setClass2(RealClass class2) {
		this.class2 = class2;
	}
	
	private RealClass class2 ;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private String name;
}
