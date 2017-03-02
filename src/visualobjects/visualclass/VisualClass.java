package visualobjects.visualclass;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import mouse.MouseClick;
import objects.Attribute;
import objects.Methode;
import objects.RealClass;
import visualobjects.Text;
import visualobjects.VisualObject;

public class VisualClass extends VisualObject {
	private final RealClass realClass;
	
	private Text name;
	private Collection<Text> attributes;
	private Collection<Text> methods;

	public VisualClass(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.realClass = new RealClass();
		this.setAttributes(new ArrayList<Text>());
		this.setMethods(new ArrayList<Text>());
		this.setName(new Text(this.getX(), this.getY(), parent));
		this.addChild(this.getName());
	}

	public VisualClass(int x, int y, VisualObject parent){
		this(x,y, 100,200, parent);
	}
	
	
	
	public void show(Graphics g){
		super.show(g);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		
		for (Methode m : this.getRealClass().getMethodes()){
			
		}
		
		for (Attribute a: this.getRealClass().getAttributes()){
			
		}
	}
	
	
	public VisualObject select(int x, int y, MouseClick mc){
		VisualObject vo = super.select(x, y, mc);
		if (vo == this){
			
		} else {
			if (mc.equals(MouseClick.DOUBLE_CLICK)){
				Text t = this.createAttribute();
				return t;
			}
		}
		
		return null;
	}

	public Collection<Text> getAttributes() {
		return attributes;
	}

	public void setAttributes(Collection<Text> attributes) {
		this.attributes = attributes;
	}

	public Collection<Text> getMethods() {
		return methods;
	}

	public void setMethods(Collection<Text> methods) {
		this.methods = methods;
	}

	public RealClass getRealClass() {
		return realClass;
	}
	
	private void addAttribute(Text a){
		this.getAttributes().add(a);
		this.addChild(a);
	}
	
	private void addMethod(Text m){
		this.getMethods().add(m);
		this.addChild(m);
	}
	
	public Text createAttribute(){
		Text t = new Text(this.getX(),
				this.getY(), this.getWidth(), 50, this);
		this.addAttribute(t);
		return t;
	}
	
	public Text createMethod(){
		Text t = new Text(this.getX(),
				this.getY(), this.getWidth(), 50, this);
		this.addMethod(t);
		return t;
	}
	
	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}
	
	private boolean isInEmptyAttribute(int x, int y){
		return false;
	}
	
	private boolean isInEmptyMethod(int x, int y){
		return false;
	}

}
