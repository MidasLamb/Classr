package interfaces;

import java.text.AttributedString;

import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObject;
import logicalobjects.Method;
import logicalobjects.Parameter;

public interface LogicalObjectVisitor<T> {
	public default T startVisit(LogicalObject lo){
		return ((T) lo.accept(this));
	}
	
	public T visit(LogicalClass c);
	public T visit(Method c);
	public T visit(Attribute c);
	public T visit(Association c);
	public T visit(Parameter parameter);
}
