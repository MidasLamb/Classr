package visualobjects;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import mouse.clicks.DoubleClick;
import mouse.clicks.SingleClick;
import visualobjects.visualclass.VisualClass;

public class Container extends VisualObject {

	public Container(int x, int y, int width, int height) {
		super(x, y, Integer.MIN_VALUE, width, height, null);
	}
	
	
	/**
	 * @param	e
	 * 			KeyEvent that needs to be handled
	 * @effect	If there is an element selected, let that element handle KeyEvent e
	 */
	public void sendKeyToSelected(KeyEvent e){
		if (getSelected() != null)
			getSelected().handleKey(e);
	}
	
	/**
	 * @param 	vo
	 * 			VisualObject that will be selected
	 * @effect	Unselects the selected item (if present), and sets VisualObject vo as selected
	 */
	public void switchSelectedTo(VisualObject vo){
		//Unselect previous selected item
		if (getSelected() != null) 
			getSelected().setSelected(false);
		//Select current selected item
		if (vo != null)
			vo.setSelected(true);
		setSelected(vo);
	}
	
	/**
	 * 
	 * @param 	x
	 * 			The x coordinate where the class needs to be showed
	 * @param 	y
	 * 			The y coordinate where the class needs to be showed
	 * @effect	Creates a new visual class in this container
	 * 				adds the class to the children
	 * 				makes the text item of the class the selected item
	 */
	private void createNewClass(int x, int y){
		VisualClass c = new VisualClass(x,y, 1, this);
		this.addChild(c);
		VisualObject a = c.getName().getContent();
		this.switchSelectedTo(a);
	}
	
	@Override
	public void onDoubleClick(DoubleClick dc){
		if (this.select(dc.getX(), dc.getY()).equals(this)){
			//Double click on empty 
			createNewClass(dc.getX(), dc.getY());
		} else {
			super.onDoubleClick(dc);
		}
	}
	
	@Override
	public void onClick(SingleClick sc){
		int x = sc.getX();
		int y = sc.getY();
		boolean b = false;
		for (VisualObject v: this.getChildren()){
			if (v.isIn(x, y))
				b = true;
		}
		if (b)
			super.onClick(sc);
		else
			this.switchSelectedTo(null);
	}
	
	
	/**
	 * @return	VisualObject that is selected
	 */
	private VisualObject getSelected() {
		return selected;
	}


	/**
	 * @param 	selected
	 * 			VisualObject that will be set as selected
	 */
	private void setSelected(VisualObject selected) {
		this.selected = selected;
	}
	
	private VisualObject selected;
}
