package mouse.clicks;

import mouse.MouseClick;
import mouse.MouseClickSort;

public class SingleClick extends MouseClick {

	public SingleClick(int startX, int startY, MouseClickSort mcs) {
		super(startX, startY, mcs);
		// TODO Auto-generated constructor stub
	}
	
	public int getX(){
		return this.getStartX();
	}
	
	public int getY(){
		return this.getStartY();
	}

}
