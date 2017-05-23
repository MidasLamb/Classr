package decoupling;

import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObjectVisitor;
import logicalobjects.Method;
import logicalobjects.Parameter;
import visualobjects.AssociationHandle;
import visualobjects.Container;
import visualobjects.EditableTextWrapper;
import visualobjects.FormObjectWrapper;
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
	
	/**
	 * A visitor to get the decoupler for the given EditableTextWrapper
	 * @param 	textWrapper
	 * 			the EditableTextWrapper you want to visit
	 * @return returns the decoupler for the given EditableTextWrapper
	 */
	public Decoupler visit(EditableTextWrapper<?> textWrapper) {
		return new EditableTextWrapperDecoupler(textWrapper);
	}
	
	public Decoupler visit(TextWrapper<?> textWrapper) {
		return null;
	}

	public Decoupler visit(AssociationHandle associationHandle) {
		return null;
	}

	public Decoupler visit(Container container) {
		return null;
	}

	public Decoupler visit(FormObjectWrapper<?> formObjectWrapper) {
		return null;
	}

	@Override
	public Decoupler visit(LogicalClass c) {
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
}
