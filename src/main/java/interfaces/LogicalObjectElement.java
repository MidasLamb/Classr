package interfaces;

public interface LogicalObjectElement {
	/**
	 * The accept method for the visitor pattern.
	 * It is important that you implement this in the lowest possible level of the hierarchy,
	 * because else the double dispatching aspect of the visitor pattern won't work.
	 * The implementation of this method should be:
	 * 		v.visit(this)
	 * 
	 * @param v
	 */
	public void accept(LogicalObjectVisitor v);
}
