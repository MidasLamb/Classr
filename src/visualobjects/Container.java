package visualobjects;


import java.awt.event.KeyEvent;

import visualobjects.visualclass.Class;

public class Container extends VisualObject {
	private VisualObject selected;

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VisualObject select(int x, int y) {
		for (VisualObject v : this.getChildren()){
			if (v.isIn(x, y)){
				this.selected.setIsSelected(false);
				VisualObject t = v.select(x, y);
				this.selected = t;
				t.setIsSelected(true);
				return t;
			}
		}
		Class c = new Class(x,y);
		this.addChild(c);
		
		Text a = c.getText();
		
		if (this.selected != null) 
				this.selected.setIsSelected(false);
		this.selected = a;
		a.setIsSelected(true);
		
		return a;
	}
	
	public void sendKeyToSelected(KeyEvent e){
		this.selected.handleKey(e);
	}
	
	

}
