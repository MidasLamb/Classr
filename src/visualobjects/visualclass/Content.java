package visualobjects.visualclass;

import java.awt.Graphics;

import visualobjects.Text;
import visualobjects.VisualObject;

public class Content extends VisualObject {
	private Text text;

	public Content(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = new Text(this.getX(), this.getY() + 16, this.getWidth(), this.getHeight());
		this.addChild(text);
	}

	
	@Override
	public void show(Graphics g){
		super.show(g);
		if (this.isSelected()){
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public Text getText(){
		return this.text;
	}
	
	@Override
	public VisualObject select(int x, int y){
		return this.text;
	}
	
	@Override
	public void setY(int y){
		super.setY(y);
		this.text.setY(this.getY() + 16);
	}

}
