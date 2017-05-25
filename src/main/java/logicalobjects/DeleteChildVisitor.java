package logicalobjects;

/**
 * A visitor to delete a child of a LogicalClass
 *
 */
public class DeleteChildVisitor implements LogicalObjectVisitor<Void> {

	@Override
	/**
	 * Removes an Association correctly
	 * 
	 * @param association
	 *            the Association to remove
	 */
	public Void visit(Association association) {
		association.remove();
		return null;
	}

	@Override
	/**
	 * Removes an Attribute correctly
	 * 
	 * @param attribute
	 *            the Attribute to remove
	 */
	public Void visit(Attribute attribute) {
		attribute.getRealClass().deleteAttribute(attribute);
		return null;
	}

	/**
	 * Removes a Method correctly
	 * 
	 * @param Method
	 *            the Method to remove
	 */
	@Override
	public Void visit(Method method) {
		method.getRealClass().deleteMethod(method);
		return null;
	}

	@Override
	public Void visit(LogicalClass c) {
		return null;
	}

	@Override
	public Void visit(Parameter parameter) {
		return null;
	}

}
