package logicalobjects;

import static visibilities.Visibility.*;

import interfaces.LogicalObjectStringVisitor;

public class StringVisitor implements LogicalObjectStringVisitor {

	@Override
	public String visit(LogicalClass c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Method c) {
		/*Each method is
			represented using the standard UML textual representation for a method,
			including its name, return type, parameters, and visibility, and whether it
			is static or abstract*/
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Attribute c) {
		StringBuilder builder = new StringBuilder();
		//Add visibility
		builder.append(c.getVisibility().getUMLRepresentation());
		builder.append(" ");
		builder.append(c.getName());
		return builder.toString();
		/*Each attribute is represented using the standard UML textual representation
		for an attribute, including its name, type, and visibility (private, packageaccessible,
				protected, or public), and whether it is static*/
		// TODO Auto-generated method stub
	}

	@Override
	public String visit(Association c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Parameter parameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
