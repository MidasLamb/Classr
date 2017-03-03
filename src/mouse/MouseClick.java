package mouse;

public class MouseClick {
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private MouseClickSort mcs;
	
	public MouseClick(int startX, int startY, int endX, int endY, MouseClickSort mcs){
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
		this.setMouseClickSort(mcs);
	}
	
	public MouseClick(int startX, int startY, MouseClickSort mcs){
		this(startX,startY,startX,startY, mcs);
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

	public MouseClickSort getMouseClickSort() {
		return mcs;
	}

	private void setMouseClickSort(MouseClickSort mcs) {
		this.mcs = mcs;
	}
}
