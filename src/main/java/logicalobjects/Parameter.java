package logicalobjects;

import interfaces.LogicalObjectVisitor;

public class Parameter extends LogicalObject {
	private String type;
	
	public Parameter(String name, String type){
		this.setName(name);
		this.setType(type);
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


	@Override
	public Object accept(LogicalObjectVisitor v) {
		return v.visit(this);
	}
}
