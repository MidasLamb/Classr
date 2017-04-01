package interfaces;

import logicalobjects.*;

public interface LogicalObjectVisitor {
	public void visit(LogicalClass c);
	public void visit(Method c);
	public void visit(Attribute c);
	public void visit(Association c);
}
