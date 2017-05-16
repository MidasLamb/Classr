package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;

import gui.inputHandlers.Clickable;
import gui.inputHandlers.clicks.DoubleClick;
import gui.inputHandlers.clicks.Drag;
import gui.inputHandlers.clicks.MouseClick;
import gui.inputHandlers.clicks.SingleClick;

/**
 * A class representing a MenuItem in a drop down menu of a MenuHeader
 */
//TODO WRONG!
public abstract class MenuItem extends FormObject implements Displayable, Clickable {
	//TODO WRONG!
	private DropDownMenu<MenuItem> dropDownMenu;
	private String name;
	private boolean enabled;
	//TODO WRONG!
	private int height;
	

	public MenuItem(String name, int width, int height) {
		super(0, 0, width, height);
		this.setName(name);
	}
	
	//TODO WRONG!
	public DropDownMenu<MenuItem> getDropDownMenu() {
		return dropDownMenu;
	}
	// TODO WRONG!
	public void setDropDownMenu(DropDownMenu<MenuItem> dropDownMenu) {
		this.dropDownMenu = dropDownMenu;
	}

	// TODO WRONG!
	public String getName() {
		return name;
	}

	// TODO WRONG!
	public void setName(String name) {
		this.name = name;
	}

	// TODO WRONG!
	public boolean isEnabled() {
		return enabled;
	}

	// TODO WRONG!
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public
	void onClick(MouseClick click) {
		this.getDropDownMenu().toggle();
		this.onAction();
	}

	@Override
	public void draw(Graphics g) {
		
		Color c = g.getColor();
		if (this.isFocused())
			g.setColor(Color.BLUE);
		height = g.getFontMetrics().getHeight();
		int descent = g.getFontMetrics().getDescent();
		g.drawString(getDisplayableString(), 0, height - descent);
		g.setColor(c);
		
		//height = g.getFontMetrics().getHeight();
		//g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	@Override
	public String getDisplayableString() {
		return this.getName();
	}

	// TODO WRONG!
	@Override
	public void onClick(SingleClick click) {
		this.onAction();
	}

	@Override
	public void onDoubleClick(DoubleClick click) {
	}

	@Override
	public void onDragEnd(Drag drag) {
	}

	@Override
	public void onDragUpdate(Drag drag) {
	}

	boolean isIn(int x, int y) {
		return isBetween(this.getX(), this.getX() + this.getWidth(), x)
				&& isBetween(this.getY(), this.getY() + this.getHeight(), y);
	}
	
	/**
	 * Checks if c is between a and b
	 */
	final static boolean isBetween(int a, int b, int c) {
		return a <= c && b >= c;
	}

	
	

}
