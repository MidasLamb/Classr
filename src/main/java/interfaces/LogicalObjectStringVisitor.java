package interfaces;

import logicalobjects.*;

public interface LogicalObjectStringVisitor{
	public String visit(LogicalClass c);
	public String visit(Method c);
	public String visit(Attribute c);
	public String visit(Association c);
	public String visit(Parameter parameter);
}
