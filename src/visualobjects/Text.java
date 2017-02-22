package visualobjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Text extends VisualObject {
	private String text;

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
		super.show(g);
		g.drawString(this.text, this.getX(), this.getY());
	}
	
	public void handleKey(KeyEvent e){
		String s = Character.toString(e.getKeyChar());
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || s.equals("\b")){
			this.removeLetter();
		} else {
			this.addLetter(s);
		}
	}

	
	


}
