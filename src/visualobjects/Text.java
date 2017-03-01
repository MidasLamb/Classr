package visualobjects;
import static main.Constants.*;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Text extends VisualObject {
	private boolean isStandardText;
	private String standardTextString = "okidoki";

	public Text(int x, int y, int width, int height, VisualObject parent) {
		super(x, y, width, height, parent);
		this.setText("New Text");
	}
	
	public Text(int x, int y, VisualObject parent){
		// 50, 16 is the standard size of the font if the text is "New Text"
		this(x,y, 50,16, parent);
	}
	
	public void removeLetter(){
		if (this.getText().length() > 0)
			this.setText(text.substring(0, text.length()-1));
		//TODO string length = 0
	}
	
	public void addLetter(String i){
		this.setText(this.getText() + i);
	}
	@Override 
	public void show(Graphics g){
		//Limit the text size if it is to long
		cutTextMaxWidth(g);
		//Get and set the width/height based on font
		FontMetrics m = g.getFontMetrics();
		this.setWidth(m.stringWidth(this.getText()));
		this.setHeight(m.getHeight());
		
		//Draw the string
		//Add the height with the Y value since drawing strings
		//	begins bottom left
		g.drawString(this.getText(), this.getX(), this.getY() + this.getHeight());
		
		//Draw cursus
		if (this.isSelected()){
			g.drawLine(this.getX() + this.getWidth() + 1,
					this.getY(),
					this.getX() + this.getWidth() + 1,
					this.getY() + this.getHeight());
			g.drawLine(this.getX() + this.getWidth() + 2,
					this.getY(),
					this.getX() + this.getWidth() + 2,
					this.getY() + this.getHeight());
		}
	}
	
	/**
	 * Changes the text due to keypresses
	 */
	public void handleKey(KeyEvent e){
		//Get the key and put it in a string
		String s = Character.toString(e.getKeyChar());
		//Delete text if you press the backspace
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || s.equals("\b")){
			this.removeLetter();
		//Go out of object if you press enter
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.setIsSelected(false);
		// if it isn't an action key you can write it down
		} else if(!e.isActionKey() && e.getKeyCode() != KeyEvent.VK_SHIFT){
			this.addLetter(s);
		}
	}
	
	private void cutTextMaxWidth(Graphics g){
		FontMetrics m = g.getFontMetrics();
		while(m.stringWidth(getText()) > MAX_TEXT_WIDTH){
			removeLetter();	
		}
	}
	
	private String getText() {
		return text;
	}

	private void setText(String text) {
		this.text = text;
	}
	
	private String text;

	@Override
	public void setIsSelected(boolean b){
		boolean prev = this.isSelected();
		super.setIsSelected(b);
		if (this.isSelected() == false && prev){
			if (this.getText().length() == 0){
				this.setText(this.standardTextString);
				this.isStandardText = true;
			}
		}
		
		if (this.isSelected() && prev == false){
			if (this.isStandardText){
				this.setText("");
				this.isStandardText = false;
			}
		}
	}
}
