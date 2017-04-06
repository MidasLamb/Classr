package logicalobjects;

import interfaces.LogicalObjectVisitor;

/**
 * A visitor to delete a child of a LogicalClass
 * @author midas
 *
 */
public class DeleteChildVisitor implements LogicalObjectVisitor{

	@Override
	/**
	 * Removes an Association correctly
	 * 
	 * @param	association
	 * 			the Association to remove
	 */
	public void visit(Association association) {
		association.remove();
	}

	@Override
	/**
	 * Removes an Attribute correctly
	 * 
	 * @param	attribute
	 * 			the Attribute to remove
	 */
	public void visit(Attribute attribute) {
		attribute.getRealClass().deleteAttribute(attribute);
	}

	/**
	 * Removes a Method correctly
	 * 
	 * @param	Method
	 * 			the Method to remove
	 */
	@Override
	public void visit(Method method) {
		method.getRealClass().deleteMethod(method);
	}

	/**
	 * 
	 */
	@Override
	public void visit(LogicalClass c) {
		//Do nothing
	}

	@Override
	public void visit(Parameter parameter) {
		//Do nothing
	}

}
