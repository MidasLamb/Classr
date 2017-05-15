package main;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;

public final class Constants {

	public static final String CANVAS_TITLE = "Classr"; 
	
	public static final String DEFAULT_CLASS_NAME = "NewClass";
	public static final String DEFAULT_ASS_NAME = "newAssociation";
	public static final String DEFAULT_METHOD_NAME = "newMethod";
	public static final String DEFAULT_ATTRIBUTE_NAME = "newAttribute";


	public static final int CONTAINER_WIDTH = 900;
	public static final int CONTAINER_HEIGHT = 800;


	public static final int DOUBLECLICK_TRESHOLD = 200;
	public static final int DOUBLECLICK_RANGE = 20;


	public static final int ASSOCIATIONHANDLE_SIZE = 10;


	public static final int CLASS_WIDTH = 200;
	public static final int CLASS_WHITE_SPACE = 16;
	public static final int CLASS_BODY_INITIAL_HEIGHT = 40;

	public static final int TEXT_MARGIN = 10;
	public static final int TEXT_HEIGHT = 20;

	public static final int MAX_TEXT_WIDTH = CLASS_WIDTH - 20;

	public static final int STANDARD_PADDING = 5;

	public static final int Z_CLASS = 0;
	public static final int Z_PADDING_BOX = -10;
	public static final int Z_ASSOCIATION = -5;
	
	
	public static final String STANDARD_FONT_NAME = Font.SANS_SERIF;
	public static final int STANDARD_FONT_STYLE = Font.PLAIN;
	public static final int STANDARD_FONT_SIZE = 12;
	public static final Font STANDARD_FONT = new Font(Font.SANS_SERIF,STANDARD_FONT_STYLE, STANDARD_FONT_SIZE);
	public static final FontMetrics STANDARD_FONTMETRICS = new Canvas().getFontMetrics(STANDARD_FONT);
	public static final int STANDARD_TEXT_HEIGHT = STANDARD_FONTMETRICS.getHeight();
	public static final int STANDARD_TEXT_ASCEND = STANDARD_FONTMETRICS.getAscent();
	
	public static final String REGEX_START_CAPITAL = "^[A-Z][a-zA-Z0-9_]*";
	public static final String REGEX_START_NO_CAPITAL = "^[a-z][a-zA-Z0-9_]*";

	private Constants() {
		throw new AssertionError();
	}
}
