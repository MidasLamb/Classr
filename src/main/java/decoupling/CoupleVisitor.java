package decoupling;

import visualobjects.VisualAssociation;
import visualobjects.VisualClass;

public class CoupleVisitor {
	
	public Decoupler visit(VisualClass visualClass){
		return new ClassDecoupler(visualClass);
	}

	public Decoupler visit(VisualAssociation visualAssociation) {
		return new AssociationDecoupler(visualAssociation);
	}

}
