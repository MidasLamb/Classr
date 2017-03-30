package gui;

import java.awt.Canvas;
import java.awt.Font;

public final class Constants {
	
	public static final int STANDARD_RADIOBUTTON_WIDTH = 10;
	public static final int STANDARD_RADIOBUTTON_HEIGHT = 10;
	public static final int STANDARD_CHECKBOX_HEIGHT = 10;
	public static final int STANDARD_CHECKBOX_WIDTH = 10;
	public static final int STANDARD_BUTTON_TEXT_PADDING = 5;
	
	// TODO: hoogte label
	public static final Font STANDARD_FONT = new Font(Font.SANS_SERIF,Font.PLAIN, 12);
	public static final int STANDARD_TEXT_HEIGHT = new Canvas().getFontMetrics(STANDARD_FONT).getHeight();
	public static final int STANDARD_TEXT_ASCEND = new Canvas().getFontMetrics(STANDARD_FONT).getAscent();
	
	public static final int STANDARD_LABEL_PADDING = 5;
	
	
}
