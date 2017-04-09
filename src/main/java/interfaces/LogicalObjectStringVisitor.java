package interfaces;

import java.text.AttributedString;

import logicalobjects.*;

public interface LogicalObjectStringVisitor{
	public AttributedString visit(LogicalClass c);
	public AttributedString visit(Method c);
	public AttributedString visit(Attribute c);
	public AttributedString visit(Association c);
	public AttributedString visit(Parameter parameter);
}
