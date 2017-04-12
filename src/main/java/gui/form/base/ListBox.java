package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import inputHandlers.clicks.MouseClick;

public abstract class ListBox<T extends Displayable> extends FormObject {
	ArrayList<ListBoxElement<T>> elements;
	ListBoxElement<T> selectedElement;

	public ListBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setElements(new ArrayList<ListBoxElement<T>>());
	}

	private boolean isInElement(ListBoxElement<T> e, int clickX, int clickY, int elementX, int elementY) {
		boolean isInHorizontal = clickX >= elementX && clickX < elementX + this.getWidth();
		boolean isInVertical = clickY >= elementY && clickY < elementY + e.getHeight();
		return isInHorizontal && isInVertical;
	}

	@Override
	void onClick(MouseClick click) {
		// Check on which element there has been clicked.
		int x = this.getX();
		int y = this.getY();
		int sumOfVerticalMovement = 0;
		for (ListBoxElement<T> e : this.getElements()) {
			boolean isin = isInElement(e, click.getX(), click.getY(), x, y + sumOfVerticalMovement);
			if (isin) {
				this.setSelectedElement(e);
				this.onAction();
				return;
			}
			sumOfVerticalMovement += e.getHeight();
		}
		this.setSelectedElement(null);
		this.onAction();
	}

	@Override
	void draw(Graphics g) {
		// We use translating, so it's easier to remove an element from the
		// middle of the list.
		int translatedX = this.getX();
		int translatedY = this.getY();
		int sumOfVerticalTranslations = 0;
		g.translate(translatedX, translatedY);
		for (ListBoxElement<T> e : this.getElements()) {
			Color c = g.getColor();
			if (e.equals(this.getSelectedElement()))
				g.setColor(Color.RED);
			e.draw(g);
			sumOfVerticalTranslations += e.getHeight();
			g.translate(0, e.getHeight());
			g.setColor(c);
		}
		g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
	}

	public void addElement(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		getElements().add(lbe);
		this.setSelectedElement(lbe);
	}

	/**
	 * Removes the first occurrence of the element in the list.
	 * 
	 * @param e
	 */
	public void removeElement(T e) {
		// TODO update because remove won't work
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		getElements().remove(lbe);
	}

	public void removeSelectedElement() {
		int index = getElements().indexOf(this.getSelectedElement());
		getElements().remove(this.getSelectedElement());
		if (index < getElements().size()) {
			this.setSelectedElement(getElements().get(index));
		} else {
			this.setSelectedElement(null);
		}
	}

	public void switchSelectedElementWith(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		int index = getElements().indexOf(this.getSelectedElement());
		getElements().set(index, lbe);
		this.setSelectedElement(lbe);
	}

	private final ArrayList<ListBoxElement<T>> getElements() {
		return elements;
	}

	private final void setElements(ArrayList<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	private final ListBoxElement<T> getSelectedElement() {
		return selectedElement;
	}

	public final T getSelectedObject() {
		ListBoxElement<T> lb = this.getSelectedElement();
		if (lb == null)
			return null;
		return lb.getObject();
	}

	private final void setSelectedElement(ListBoxElement<T> selectedElement) {
		this.selectedElement = selectedElement;
	}

	class ListBoxElement<T extends Displayable> {
		private T obj;
		private int height;

		ListBoxElement(T obj) {
			this.obj = obj;
		}

		public String getDisplayableString() {
			return obj.getDisplayableString();
		}

		/**
		 * Draws this element at the origin.
		 * 
		 * @param g
		 */
		void draw(Graphics g) {
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(obj.getDisplayableString(), 0, height - descent);

		}

		public int getHeight() {
			return this.height;
		}

		public T getObject() {
			return this.obj;
		}

	}

}
