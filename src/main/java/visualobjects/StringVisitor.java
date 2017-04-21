package visualobjects;

import static java.awt.Font.ITALIC;
import static java.awt.font.TextAttribute.FONT;
import static java.awt.font.TextAttribute.UNDERLINE;
import static java.awt.font.TextAttribute.UNDERLINE_ON;
import static main.Constants.MAX_TEXT_WIDTH;
import static main.Constants.STANDARD_FONTMETRICS;
import static main.Constants.STANDARD_FONT_NAME;
import static main.Constants.STANDARD_FONT_SIZE;

import java.awt.Font;
import java.text.AttributedString;
import java.util.List;
import java.util.stream.Collectors;

import gui.text.Text;
import logicalobjects.Association;
import logicalobjects.Attribute;
import logicalobjects.LogicalClass;
import logicalobjects.LogicalObjectVisitor;
import logicalobjects.Method;
import logicalobjects.Parameter;

/**
 * A visitor to visit logical objects and creates an attributed string
 * 		containing the data in UML representation of that logicalObject
 * 
 * @author group11
 *
 */
public class StringVisitor implements LogicalObjectVisitor<AttributedString> {

	@Override
	public AttributedString visit(LogicalClass c) {
		String text = c.getName();
		text = trimString(text);
		AttributedString string = new AttributedString(text);
		return string;
	}

	@Override
	public AttributedString visit(Method c) {
		List<String> parameters = c.getParameters().stream()
				.map(x -> Text.getStringFromAttributedString((AttributedString) x.accept(this)))
				.collect(Collectors.toList());
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(c.getVisibility().getUMLRepresentation());
		strBuilder.append(c.getName());
		strBuilder.append("(");
		for (String parameter : parameters) {
			if(!parameter.equals(parameters.get(0)))
				strBuilder.append(", ");
			strBuilder.append(parameter);
		}
		strBuilder.append(") : ");
		strBuilder.append(c.getType());
		String text = strBuilder.toString();
		text = trimString(text);
		AttributedString string = new AttributedString(text);
		if(c.isStatic())
			string.addAttribute(UNDERLINE, UNDERLINE_ON, 1, text.length());
		if(c.isAbstract())
			string.addAttribute(FONT, new Font(STANDARD_FONT_NAME, ITALIC, STANDARD_FONT_SIZE), 1, text.length());
		return string;
	}
	
	/**
	 * Trims the string to the max text width
	 * @param 	string
	 * 			the string that needs to be trimmed
	 * @return	the trimmed string
	 */
	private String trimString(String string){
		int i = string.length();
		while(STANDARD_FONTMETRICS.stringWidth(string.substring(0, i)) > MAX_TEXT_WIDTH){
			i--;
		}
		return string.substring(0,i);
	}

	@Override
	public AttributedString visit(Attribute c) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(c.getVisibility().getUMLRepresentation());
		strBuilder.append(c.getName());
		strBuilder.append(" : ");
		strBuilder.append(c.getType());
		String text = strBuilder.toString();
		text = trimString(text);
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
		String text = c.getName() + " : " + c.getType();
		AttributedString string = new AttributedString(text);
		return string;
	}

}
