package logicalobjects;

import interfaces.LogicalObjectVisitor;

public class Parameter extends LogicalObject {
	private String type;
	
	public Parameter(String name, String type){
		this.setName(name);
		this.setType(type);
	}

	@Override
	public void accept(LogicalObjectVisitor v) {
		v.visit(this);
	}

	@Override
	protected void onDelete() {

	}

	public final String getType() {
		return type;
	}

	public final void setType(String type) {
		this.type = type;
	}

}
