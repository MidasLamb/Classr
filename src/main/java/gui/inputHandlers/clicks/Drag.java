package gui.inputHandlers.clicks;

public class Drag extends MouseClick {
	private int startX;
	private int startY;
	private int endX;
	private int endY;

	public Drag(int startX, int startY, int endX, int endY) {
		super(endX, endY);
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);

	}

	public int getStartX() {
		return startX;
	}

	private void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	private void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndX() {
		return endX;
	}

	private void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	private void setEndY(int endY) {
		this.endY = endY;
	}

}
