package logicalobjects;

import java.text.AttributedString;

import interfaces.LogicalObjectDeleteVisitor;
import interfaces.LogicalObjectStringVisitor;

public class Parameter extends LogicalObject {
	private String type;
	
	public Parameter(String name, String type){
		this.setName(name);
		this.setType(type);
	}

	@Override
	public void accept(LogicalObjectDeleteVisitor v) {
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

	@Override
	public AttributedString accept(LogicalObjectStringVisitor v) {
		return v.visit(this);
	}

}
