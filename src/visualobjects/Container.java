package visualobjects;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
import mouse.clicks.DoubleClick;
import mouse.clicks.SingleClick;
import visualobjects.visualclass.Association;
import visualobjects.visualclass.AssociationHandle;
import visualobjects.visualclass.VisualClass;

public class Container extends VisualObject {
	private VisualObject selected;
	private AssociationHandle handleStart;
	private Collection<Association> associations;

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height, null);
	}

		
	public void sendKeyToSelected(KeyEvent e){
		if (selected != null){
			this.selected.handleKey(e);
			return;
		}
	}
	
	public boolean hasHandleStart(){
		return (this.handleStart != null);
	}
	
	public AssociationHandle getHandleStart(){
		return this.handleStart;
	}
	
	public void switchSelectedTo(VisualObject vo){
		if (this.selected != null) 
			this.selected.setIsSelected(false);
		this.selected = vo;
		if (vo != null)
			vo.setIsSelected(true);
	}
	
	private VisualObject createNewClass(int x, int y){
		VisualClass c = new VisualClass(x,y, this);
		this.addChild(c);
		
		Text a = c.getName().getText();
		this.switchSelectedTo(a);
		return a;
	}
	
	@Override
	public void onDoubleClick(DoubleClick dc){
		if (this.select(dc.getX(), dc.getY()).equals(this)){
			//Double click on empty 
			VisualObject v = this.createNewClass(dc.getX(), dc.getY());
			return;
		}
		super.onDoubleClick(dc);
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
	

}
