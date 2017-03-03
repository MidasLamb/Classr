package mouse.clicks;

import mouse.MouseClick;
import mouse.MouseClickSort;

public class DoubleClick extends MouseClick {

	public DoubleClick(int startX, int startY, MouseClickSort mcs) {
		super(startX, startY, mcs);
	}
	
	public int getX(){
		return this.getStartX();
	}
	
	public int getY(){
		return this.getStartY();
	}


}
