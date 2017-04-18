package gui.text.state;

import java.awt.Graphics;

import gui.inputHandlers.Typable;
import gui.text.Text;

public abstract class TextState implements Typable{
	private Text text;
	public TextState(){
		
	}
	
	public void setText(Text text){
		this.text = text;
	}
	
	protected final Text getText(){
		return text;
	}
	public abstract void draw(Graphics g, int x, int y);
}
