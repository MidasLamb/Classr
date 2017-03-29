package objects;

public class DeleteChildVisitor extends RealClassVisitor {

	@Override
	public void visitAssociation(Association association) {
		association.remove();
		
	}

	@Override
	public void visitAttribute(Attribute attribute) {
		attribute.getRealClass().deleteAttribute(attribute);
	}

	@Override
	public void visitMethod(Method method) {
		method.getRealClass().deleteMethod(method);
	}

}
