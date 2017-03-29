package objects;

public class DeleteChildVisitor extends RealClassVisitor {

	@Override
	/**
	 * Removes an Association correctly
	 * 
	 * @param	association
	 * 			the Association to remove
	 */
	public void visitAssociation(Association association) {
		association.remove();
	}

	@Override
	/**
	 * Removes an Attribute correctly
	 * 
	 * @param	attribute
	 * 			the Attribute to remove
	 */
	public void visitAttribute(Attribute attribute) {
		attribute.getRealClass().deleteAttribute(attribute);
	}

	/**
	 * Removes a Method correctly
	 * 
	 * @param	Method
	 * 			the Method to remove
	 */
	@Override
	public void visitMethod(Method method) {
		method.getRealClass().deleteMethod(method);
	}

}
