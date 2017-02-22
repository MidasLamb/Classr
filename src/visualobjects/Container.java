package visualobjects;


import java.awt.event.KeyEvent;

import visualobjects.visualclass.Class;

public class Container extends VisualObject {
	private Text selected;

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VisualObject select(int x, int y) {
		for (VisualObject v : this.getChildren()){
			if (v.isIn(x, y)){
				return v.select(x, y);
			}
		}
		Text c = new Text(x,y, 10,10);
		this.addChild(c);
		this.selected = c;
		return c;
	}
	
	public void sendKeyToSelected(KeyEvent e){
		this.selected.handleKey(e);
	}
	
	

}
