package main;

public final class Constants {
	
	public static final String CANVAS_TITLE = "Classr"; 
	
	
	public static final int CONTAINER_WIDTH = 600;
	public static final int CONTAINER_HEIGHT = 800;
	
	
	public static final int DOUBLECLICK_TRESHOLD = 200;
	public static final int DOUBLECLICK_RANGE = 20;
	
	
	public static final int ASSOCIATIONHANDLE_SIZE = 10;
	
	
	public static final int CLASS_WIDTH = 100;
	
	public static final int CLASS_BODY_INITIAL_HEIGHT = 40;
	
	public static final int TEXT_MARGIN = 10;
	public static final int TEXT_HEIGHT = 20;
	
	public static final int MAX_TEXT_WIDTH = CLASS_WIDTH - 20;

	
	private Constants() {
		throw new AssertionError();
	}
}
