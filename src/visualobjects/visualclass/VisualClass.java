package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.Collection;

import objects.Attribute;
import objects.Methode;
import objects.RealClass;
import visualobjects.Text;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {
	private RealClass realClass;
	
	private Collection<Text> attributes;
	private Collection<Text> methods;

	public VisualClass(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.setRealClass(new RealClass());
	}
	
	public VisualClass(int x, int y, VisualObject parent){
		this(x,y, 100,200, parent);
		
	}
	
	
	
	public void show(Graphics g){
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		
		for (Methode m : this.getRealClass().getMethodes()){
			g.drawRect(x, y, width, 10);
		}
		
		for (Attribute a: this.getRealClass().getAttributes()){
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public RealClass getRealClass() {
		return realClass;
	}

	public void setRealClass(RealClass realClass) {
		this.realClass = realClass;
	}
	
	
	
	

}
