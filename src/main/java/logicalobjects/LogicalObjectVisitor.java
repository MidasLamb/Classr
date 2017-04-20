package logicalobjects;

/**
 * An interface for objects who 
 * 	want to visit other logicalObjects
 */
public interface LogicalObjectVisitor<T> {
	
	@SuppressWarnings("unchecked")
	public default T startVisit(LogicalObject lo){
		return ((T) lo.accept(this));
	}
	
	/**
	 * Visitor for LogicalClass
	 * @param 	c
	 * 			The logicalClass you have visited
	 */
	public T visit(LogicalClass c);
	
	/**
	 * Visitor for Methode
	 * @param 	c
	 * 			The Methode you have visited
	 */
	public T visit(Method c);
	
	/**
	 * Visitor for Attribute
	 * @param 	c
	 * 			The Attribute you have visited
	 */
	public T visit(Attribute c);
	
	/**
	 * Visitor for Association
	 * @param 	c
	 * 			The Association you have visited
	 */
	public T visit(Association c);
	
	/**
	 * Visitor for Parameter
	 * @param 	c
	 * 			The Parameter you have visited
	 */
	public T visit(Parameter c);
}
