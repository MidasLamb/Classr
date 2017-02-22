package visualobjects;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Text extends VisualObject {
	private String text;
	private boolean isStandardText;
	private String standardTextString = "okidoki";

	public Text(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = "New Text";
	}
	
	public void removeLetter(){
		this.text = text.substring(0, text.length()-1);
		//TODO string length = 0
	}
	
	public void addLetter(String i){
		this.text += i;
	}
	@Override 
	public void show(Graphics g){
		FontMetrics m = g.getFontMetrics();
		this.setWidth(m.stringWidth(this.text));
		this.setHeight(m.getHeight());
		
		//g.drawRect(this.getX(), this.getY() - this.getHeight(), this.getWidth(), this.getHeight());
		g.drawString(this.text, this.getX(), this.getY());
		
		if (this.isSelected()){
			g.drawLine(this.getX() + this.getWidth() + 1,
					this.getY() - this.getHeight() + 5,
					this.getX() + this.getWidth() + 1,
					this.getY());
			g.drawLine(this.getX() + this.getWidth() + 2,
					this.getY() - this.getHeight() + 5,
					this.getX() + this.getWidth() + 2,
					this.getY());
		}
	}
	
	public void handleKey(KeyEvent e){
		String s = Character.toString(e.getKeyChar());
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || s.equals("\b")){
			this.removeLetter();
		} else {
			this.addLetter(s);
		}
	}
	
	@Override
	public void setIsSelected(boolean b){
		boolean prev = this.isSelected();
		super.setIsSelected(b);
		if (this.isSelected() == false && prev){
			if (this.text.length() == 0){
				this.text = this.standardTextString;
				this.isStandardText = true;
			}
		}
		
		if (this.isSelected() && prev == false){
			if (this.isStandardText){
				this.text = "";
				this.isStandardText = false;
			}
		}
	}
	
	@Override
	public VisualObject select(int x, int y) {
		return this;
	}
	
	@Override
	public boolean isIn(int x, int y){
		return isBetween(this.getX(), this.getX() + this.getWidth(), x) 
				&& isBetween(this.getY()-this.getHeight(), this.getY(), y);
	}

	
	


}
