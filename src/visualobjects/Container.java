package visualobjects;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
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

	
	@Override
	public VisualObject select(int x, int y, MouseClick mc) {
		for (VisualObject v : this.getChildren()){
			if (v.isIn(x, y)){
				VisualObject t = v.select(x, y, mc);
				this.switchSelectedTo(t);
				if (mc.equals(MouseClick.CLICK) && t instanceof AssociationHandle)
					this.handleStart = (AssociationHandle) t;
				return t;
			}
		}
		if (mc.equals(MouseClick.DOUBLE_CLICK)){
			return this.createNewClass(x, y);	
		}
		this.handleStart = null;
		this.switchSelectedTo(null);
		return null;
	}
	
	public void sendKeyToSelected(KeyEvent e){
		if (selected != null){
			this.selected.handleKey(e);
			return;
		}
		this.handleKey(e);
	}
	
	public boolean hasHandleStart(){
		return (this.handleStart != null);
	}
	
	public AssociationHandle getHandleStart(){
		return this.handleStart;
	}
	
	@Override
	public void handleKey(KeyEvent e){
		
	}
	
	private void switchSelectedTo(VisualObject vo){
		if (this.selected != null) 
			this.selected.setIsSelected(false);
		this.selected = vo;
		if (vo != null)
			vo.setIsSelected(true);
	}
	
	private VisualObject createNewClass(int x, int y){
		VisualClass c = new VisualClass(x,y, this);
		this.addChild(c);
		
		Text a = c.getName();
		this.switchSelectedTo(a);
		return a;
	}
	

}
