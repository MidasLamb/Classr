package gui.inputHandlers.clicks;

/**
 * Class that represents a MouseClick that is a mouse drag.
 */
public class Drag extends MouseClick {
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	/**
	 * Create a new Drag given its start and end coordinates.
	 * 
	 * @param startX
	 *            Start x-coordinate
	 * @param startY
	 *            Start y-coordinate
	 * @param endX
	 *            End x-coordinate
	 * @param endY
	 *            End y-coordinate
	 */
	public Drag(int startX, int startY, int endX, int endY) {
		super(endX, endY);
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);

	}

	/**
	 * Get the x-coordinate of the start location
	 * 
	 * @return start x-coordinate
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Set the x-coordinate of the start location
	 * 
	 * @param startX
	 *            start x-coordinate
	 */
	private void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * Get the y-coordinate of the start location
	 * 
	 * @return start y-coordinate
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Set the y-coordinate of the start location
	 * 
	 * @param startY
	 *            start y-coordinate
	 */
	private void setStartY(int startY) {
		this.startY = startY;
	}

	/**
	 * Get the x-coordinate of the end location
	 * 
	 * @return end x-coordinate
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Set the x-coordinate of the end location
	 * 
	 * @param endX
	 *            end x-coordinate
	 */
	private void setEndX(int endX) {
		this.endX = endX;
	}

	/**
	 * Get the y-coordinate of the end location
	 * 
	 * @return end y-coordinate
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * Set the y-coordinate of the end location
	 * 
	 * @param endY
	 *            end y-coordinate
	 */
	private void setEndY(int endY) {
		this.endY = endY;
	}

}
