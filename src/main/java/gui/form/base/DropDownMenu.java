package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import gui.form.base.ListBox;
import gui.form.base.ListBox.ListBoxElement;
import gui.inputHandlers.clicks.MouseClick;

public class DropDownMenu<T extends Displayable> extends ListBox<MenuItem> {
	private boolean enabled;

	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void toggle() {
		this.setEnabled(!this.isEnabled());
	}

	@Override
	void draw(Graphics g) {
		if (this.isEnabled()) {
			int translatedX = this.getX();
			int translatedY = this.getY();
			int sumOfVerticalTranslations = 0;
			g.translate(translatedX, translatedY);
			for (MenuItem e : this.getElements()) {
				Color c = g.getColor();
				e.draw(g);
				g.setColor(c);
				sumOfVerticalTranslations += e.getHeight();
				g.translate(0, e.getHeight());
			}
			g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
		}
	}

	@Override
	protected void onAction() {
		
	}

}

