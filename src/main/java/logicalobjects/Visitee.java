package logicalobjects;

interface Visitee {
	Object accept(LogicalObjectVisitor v);
}
