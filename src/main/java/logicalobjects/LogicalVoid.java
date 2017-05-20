package logicalobjects;

public class LogicalVoid extends LogicalObject {

	@Override
	protected void onDelete() {}

	@Override
	Object accept(LogicalObjectVisitor<?> v) {return null;}

	@Override
	public boolean canHaveAsName(String string) {return false;}
	
}
