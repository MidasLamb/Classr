package logicalobjects;

/**
 * A void object that can be used as a dummy
 */
public class LogicalVoid extends LogicalObject {

	@Override
	protected void onDelete() {
	}

	@Override
	Object accept(LogicalObjectVisitor<?> v) {
		return null;
	}

	@Override
	public boolean canHaveAsName(String string) {
		return false;
	}

}
