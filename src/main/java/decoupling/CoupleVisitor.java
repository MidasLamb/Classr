package decoupling;

import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObjectVisitor;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visualobjects.PaddingBox;
import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

public class CoupleVisitor implements LogicalObjectVisitor<Decoupler>{
	
	public Decoupler visit(VisualClass visualClass){
		return new ClassDecoupler(visualClass);
	}

	public Decoupler visit(VisualAssociation visualAssociation) {
		return new AssociationDecoupler(visualAssociation);
	}
	
	public Decoupler visit(PaddingBox<?> paddingBox) {
		return new PaddingBoxDecoupler(paddingBox);
	}

	@Override
	public Decoupler visit(LogicalClass c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decoupler visit(Method c) {
		return new MethodDecoupler(c);
	}

	@Override
	public Decoupler visit(Attribute c) {
		return new AttributeDecoupler(c);
	}

	@Override
	public Decoupler visit(Association c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Decoupler visit(Parameter c) {
		// TODO Auto-generated method stub
		return null;
	}
}
