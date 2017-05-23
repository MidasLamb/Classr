package decoupling;

import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObjectVisitor;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visualobjects.PaddingBox;
import visualobjects.TextWrapper;
import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

public class CoupleVisitor implements LogicalObjectVisitor<Decoupler>{
	
	/**
	 * A visitor to get the decoupler for the given visualClass
	 * @param 	visualClass
	 * 			the visualClass you want to visit
	 * @return returns the decoupler for the given visualClass
	 */
	public Decoupler visit(VisualClass visualClass){
		return new ClassDecoupler(visualClass);
	}

	/**
	 * A visitor to get the decoupler for the given visualAssociation
	 * @param 	visualAssociation
	 * 			the visualAssociation you want to visit
	 * @return returns the decoupler for the given visualAssociation
	 */
	public Decoupler visit(VisualAssociation visualAssociation) {
		return new AssociationDecoupler(visualAssociation);
	}
	
	/**
	 * A visitor to get the decoupler for the given paddingBox
	 * @param 	paddingBox
	 * 			the padding box you want to visit
	 * @return returns the decoupler for the given paddingbox
	 */
	public Decoupler visit(PaddingBox<?,?> paddingBox) {
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
		return null;
	}

	@Override
	public Decoupler visit(Parameter c) {
		return null;
	}

	public Decoupler visit(TextWrapper<?> textWrapper) {
		return null;
	}
}
