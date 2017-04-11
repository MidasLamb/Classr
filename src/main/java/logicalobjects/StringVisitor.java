package logicalobjects;

import static java.awt.Font.ITALIC;
import static java.awt.font.TextAttribute.UNDERLINE;
import static java.awt.font.TextAttribute.UNDERLINE_ON;
import static main.Constants.STANDARD_FONT_NAME;
import static main.Constants.STANDARD_FONT_SIZE;

import java.awt.Font;
import java.awt.font.TextAttribute;
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
			string.addAttribute(UNDERLINE, UNDERLINE_ON, 1, text.length());
		if(c.isAbstract())
			string.addAttribute(TextAttribute.FONT, new Font(STANDARD_FONT_NAME, ITALIC, STANDARD_FONT_SIZE));
		return string;
	}

	@Override
	public AttributedString visit(Attribute c) {
		String text = c.getVisibility().getUMLRepresentation() + c.getName();
		AttributedString string = new AttributedString(text);
		if(c.isStatic())
			string.addAttribute(UNDERLINE, UNDERLINE_ON, 1, text.length());
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
