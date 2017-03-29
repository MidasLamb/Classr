package objects;

public abstract class RealClassVisitor {

	public abstract void visitAssociation(Association association);
	
	public abstract void visitAttribute(Attribute attribute);
	
	public abstract void visitMethod(Method method);
}
