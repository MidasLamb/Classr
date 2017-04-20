package gui.form.base;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Useful constants
 */
public final class Constants {
	
	public static final int STANDARD_RADIOBUTTON_WIDTH = 10;
	public static final int STANDARD_RADIOBUTTON_HEIGHT = 10;
	public static final int STANDARD_CHECKBOX_HEIGHT = 10;
	public static final int STANDARD_CHECKBOX_WIDTH = 10;
	public static final int STANDARD_BUTTON_TEXT_PADDING = 5;
	
	public static final Font STANDARD_FONT = new Font(Font.SANS_SERIF,Font.PLAIN, 12);
	public static final FontMetrics STANDARD_FONTMETRICS = new Canvas().getFontMetrics(STANDARD_FONT);
	public static final int STANDARD_TEXT_HEIGHT = STANDARD_FONTMETRICS.getHeight();
	public static final int STANDARD_TEXT_ASCEND = STANDARD_FONTMETRICS.getAscent();
	
	public static final int STANDARD_LABEL_PADDING = 5;
	
	
}
