package visualobjects;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
import visualobjects.visualclass.AssociationHandle;
import visualobjects.visualclass.Class;
import visualobjects.visualclass.VisualClass;

public class Container extends VisualObject {
	private VisualObject selected;
	private AssociationHandle handleStart;
	private Collection<Association> associations;

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height, null);
		// TODO Auto-generated constructor stub
	}

	//TODO extraheer het geselecteerd maken naar een aparte functie??
	@Override
	public VisualObject select(int x, int y, MouseClick mc) {
		//System.out.println(mc.toString());
		for (VisualObject v : this.getChildren()){
			if (v.isIn(x, y)){
				VisualObject t = v.select(x, y, mc);
				if (this.selected != null)
					this.selected.setIsSelected(false);
				
				this.selected = t;
				//TODO wanneer kan t == null??? Gebeurt bij associaties precies
				if (t != null)
					t.setIsSelected(true);
				if (mc.equals(mc.CLICK) && t instanceof AssociationHandle)
					this.handleStart = (AssociationHandle) t;
				return t;
			}
		}
		if (mc.equals(MouseClick.DOUBLE_CLICK)){
			VisualClass c = new VisualClass(x,y, this);
			this.addChild(c);
			
			Text a = c.getName();
			
			if (this.selected != null) 
					this.selected.setIsSelected(false);
			this.selected = a;
			a.setIsSelected(true);
			
			return null;
		}
		this.handleStart = null;
		if (this.selected != null) 
			this.selected.setIsSelected(false);
		this.selected = null;
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
	

}
