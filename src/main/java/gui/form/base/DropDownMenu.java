package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.form.base.ListBox;
import gui.form.base.ListBox.ListBoxElement;
import gui.inputHandlers.clicks.MouseClick;

public class DropDownMenu<T extends Displayable> extends ListBox<MenuItem> {
	private boolean enabled;
	private DropDownMenuState state;
	//private ArrayList<MenuItem> menuItems;

	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
		//this.menuItems = new ArrayList<MenuItem>();
		this.setState(false);
		this.setElements(new ArrayList<ListBoxElement<MenuItem>>());
	}

	public DropDownMenuState getState() {
		return state;
	}

	public void setState(boolean bool){
		if (bool){
			setState(new Enabled());
		}
		else{
			setState(new Disabled());
		}
	}
	
	public void setState(DropDownMenuState state) {
		this.state = state;
	}

	/*public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}*/

	// WRONG!
	public void addMenuItem(MenuItem item) {
		this.addElement(item);
		item.setDropDownMenu((DropDownMenu<MenuItem>) this);
	}

	// WRONG!
	public void deleteMenuItem(MenuItem item) {
		item.setDropDownMenu(null);
		this.removeElement(item);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void toggle() {
		this.setEnabled(!this.isEnabled());
		this.setState(this.isEnabled());
	}

	// TODO WRONG!
	@Override
	public void onClick(MouseClick click){
		getState().onClick(click);
	}
	
	@Override
	public void draw(Graphics g) {
		getState().draw(g);
	}

	// TODO WRONG!
	@Override
	protected void onAction() {

	}

	private class Enabled extends DropDownMenuState {
		
		
		@Override
		void onClick(MouseClick click) {
			int x = getX();
			int y = getY();
			int sumOfVerticalMovement = 0;
			for (ListBoxElement<MenuItem> e : getListboxElements()) {
				boolean isin = isInElement(e, click.getX(), click.getY(), x, y + sumOfVerticalMovement);
				if (isin) {
					e.obj.onClick(click);
					return;
				}
				sumOfVerticalMovement += e.getHeight();
			}
		}
	
		@Override
		void draw(Graphics g) {
			int translatedX = getX();
			int translatedY = getY();
			int sumOfVerticalTranslations = 0;
			g.translate(translatedX, translatedY);
			for (ListBox<MenuItem>.ListBoxElement<MenuItem> e : getListboxElements()) {
				Color c = g.getColor();
				e.draw(g);
				g.setColor(c);
				sumOfVerticalTranslations += e.getHeight();
				g.translate(0, e.getHeight());
			}
			g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
		}

		

	}

	private class Disabled extends DropDownMenuState {

		@Override
		void onClick(MouseClick click) {
		}

		@Override
		void draw(Graphics g) {
		}

	}
	
	
	

}
