package visualobjects.visualclass;

import java.awt.Color;
import java.awt.Graphics;

import mouse.MouseClick;
import visualobjects.Text;
import visualobjects.VisualObject;

public class Content extends VisualObject {
	private Text text;

	public Content(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.text = new Text(this.getX(), this.getY() + 16, this.getWidth(), this.getHeight(), this);
		this.addChild(text);
	}

	
	@Override
	public void show(Graphics g){
		if (this.isSelected())
			g.setColor(Color.red);
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.show(g);
	}
	
	public Text getText(){
		return this.text;
	}
	
	@Override
	public VisualObject select(int x, int y, MouseClick mc){
		if (mc.equals(MouseClick.DOUBLE_CLICK) || this.isSelected() || this.text.isSelected())
			return this.text;
		else 
			return this;
	}
	
	@Override
	public void setY(int y){
		super.setY(y);
		this.text.setY(this.getY() + 16);
	}
	
	@Override
	public void delete(){
		super.delete();
		((ClassBody) this.getParent()).updateHeight();
	}

}
