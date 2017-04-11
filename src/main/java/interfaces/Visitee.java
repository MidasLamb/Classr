package interfaces;

public interface Visitee {
	public Object accept(LogicalObjectVisitor v);
}
