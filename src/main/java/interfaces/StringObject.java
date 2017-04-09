package interfaces;

import java.text.AttributedString;

public interface StringObject {

	public AttributedString accept(LogicalObjectStringVisitor v);
}
