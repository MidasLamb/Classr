package objects;

public abstract class ClassChild extends LogicalObject{

	@Override
	protected abstract void onDelete();
	
	/**
	 * Accepts a RealClassVisitor and calls the correct concrete visitor
	 * 
	 * @param 	rcv
	 * 			the RealClassVisitor to accept
	 */
	public abstract void accept(RealClassVisitor rcv);
}
