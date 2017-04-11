package logicalobjects;

import static java.awt.Font.ITALIC;
import static java.awt.font.TextAttribute.FONT;
import static java.awt.font.TextAttribute.UNDERLINE;
import static java.awt.font.TextAttribute.UNDERLINE_ON;

import java.text.AttributedString;

import interfaces.LogicalObjectVisitor;

public class StringVisitor implements LogicalObjectVisitor<AttributedString> {

	@Override
	public AttributedString visit(LogicalClass c) {
		String text = c.getName();
		AttributedString string = new AttributedString(text);
		return string;
	}

	@Override
	public AttributedString visit(Method c) {
		String text = c.getVisibility().getUMLRepresentation() + c.getName();
		AttributedString string = new AttributedString(text);
		if(c.isStatic())
			string.addAttribute(UNDERLINE, UNDERLINE_ON);
		if(c.isAbstract())
			string.addAttribute(FONT, ITALIC);
		return string;
	}

	@Override
	public AttributedString visit(Attribute c) {
		String text = c.getVisibility().getUMLRepresentation() + c.getName();
		AttributedString string = new AttributedString(text);
		if(c.isStatic())
			string.addAttribute(UNDERLINE, UNDERLINE_ON);
		return string;
	}

	@Override
	public AttributedString visit(Association c) {
		String text = c.getName();
		AttributedString string = new AttributedString(text);
		return string;
	}

	@Override
	public AttributedString visit(Parameter c) {
		String text = c.getName();
		AttributedString string = new AttributedString(text);
		return string;
	}

}
