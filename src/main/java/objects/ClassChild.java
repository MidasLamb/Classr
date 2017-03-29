package objects;

public abstract class ClassChild extends LogicalObject{

	@Override
	protected abstract void onDelete();
	
	public abstract void accept(RealClassVisitor rcv);
}
