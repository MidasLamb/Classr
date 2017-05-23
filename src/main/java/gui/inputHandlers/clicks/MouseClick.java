package gui.inputHandlers.clicks;

/**
 * Class that represents a mouse click
 */
public class MouseClick {
	private int x;
	private int y;

	/**
	 * Create a new MouseClick given its coordinates.
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public MouseClick(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Get the x-coordinate of this MouseClick.
	 * 
	 * @return x-coordinate
	 */
	public final int getX() {
		return x;
	}

	/**
	 * Set the x-coordinate of this MouseClick.
	 * 
	 * @param x
	 *            x-coordinate
	 */
	private final void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the y-coordinate of this MouseClick.
	 * 
	 * @return y-coordinate
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Set the y-coordinate of this MouseClick.
	 * 
	 * @param y
	 *            y-coordinate
	 */
	private final void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Translates a mouseclick so that the given x and y is the new origin.
	 * @param x 
	 * @param y 
	 */
	public void translate(int x, int y){
		this.setX(this.getX() - x);
		this.setY(this.getY() - y);
	}

}
