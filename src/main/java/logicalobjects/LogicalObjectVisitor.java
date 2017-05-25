package logicalobjects;

/**
 * An interface for objects who want to visit other logicalObjects
 */
public interface LogicalObjectVisitor<T> {

	/**
	 * A method to start a visit. This is to make sure the returned object is of
	 * the desired type.
	 * 
	 * @param lo
	 *            The LogicalObject to visit.
	 * @return See corresponding visit method.
	 */
	@SuppressWarnings("unchecked")
	public default T startVisit(LogicalObject lo) {
		return ((T) lo.accept(this));
	}

	/**
	 * Visitor for LogicalClass
	 * 
	 * @param c
	 *            The logicalClass you have visited
	 * @return depends on implementation
	 */
	public T visit(LogicalClass c);

	/**
	 * Visitor for Methode
	 * 
	 * @param c
	 *            The Methode you have visited
	 * @return depends on implementation
	 */
	public T visit(Method c);

	/**
	 * Visitor for Attribute
	 * 
	 * @param c
	 *            The Attribute you have visited
	 * @return depends on implementation
	 */
	public T visit(Attribute c);

	/**
	 * Visitor for Association
	 * 
	 * @param c
	 *            The Association you have visited
	 * @return depends on implementation
	 */
	public T visit(Association c);

	/**
	 * Visitor for Parameter
	 * 
	 * @param c
	 *            The Parameter you have visited
	 * @return depends on implementation
	 */
	public T visit(Parameter c);
}
