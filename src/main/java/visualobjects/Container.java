package visualobjects;

import java.awt.event.KeyEvent;

import inputHandlers.clicks.DoubleClick;
import inputHandlers.clicks.SingleClick;
import static main.Constants.*;

public class Container extends VisualObject {
	private VisualObject selected;

	public Container(int x, int y, int width, int height) {
		super(x, y, Integer.MIN_VALUE, width, height, null);
	}

	/**
	 * If there is an element selected, let that element handle KeyEvent e
	 * 
	 * @param e
	 *            KeyEvent that needs to be handled
	 */
	@Override
	public void handleKey(KeyEvent e) {
		if (getSelected() != null)
			getSelected().handleKey(e);
	}

	/**
	 * Unselects the selected item (if present), and sets VisualObject vo as
	 * selected
	 * 
	 * @param vo
	 *            VisualObject that will be selected
	 */
	public void switchSelectedTo(VisualObject vo) {
		// Unselect previous selected item
		if (getSelected() != null)
			getSelected().setSelected(false);
		// Select current selected item
		if (vo != null)
			vo.setSelected(true);
		setSelected(vo);
	}

	/**
	 * Creates a new visual class in this container adds the class to the
	 * children makes the text item of the class the selected item
	 * 
	 * @param x
	 *            The x coordinate where the class needs to be showed
	 * @param y
	 *            The y coordinate where the class needs to be showed
	 */
	private void createNewClass(int x, int y) {
		VisualClass c = new VisualClass(x, y, Z_CLASS, this);
		VisualObject a = c.getName().getContent();
		this.switchSelectedTo(a);
	}

	@Override
	public void onDoubleClick(DoubleClick dc) {
		if (this.select(dc.getX(), dc.getY()).equals(this)) {
			// Double click on empty
			createNewClass(dc.getX(), dc.getY());
		} else {
			super.onDoubleClick(dc);
		}
	}

	@Override
	public void onClick(SingleClick sc) {
		int x = sc.getX();
		int y = sc.getY();
		boolean b = false;
		for (VisualObject v : this.getChildren()) {
			if (v.isIn(x, y))
				b = true;
		}
		if (b)
			super.onClick(sc);
		else
			this.switchSelectedTo(null);
	}

	/**
	 * @return VisualObject that is selected
	 */
	VisualObject getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            VisualObject that will be set as selected
	 */
	private void setSelected(VisualObject selected) {
		this.selected = selected;
	}
}
