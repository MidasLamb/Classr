package logicalobjects;

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
