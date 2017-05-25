package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import gui.inputHandlers.clicks.MouseClick;

/**
 * A class that represents a ListBox in which ListBoxItems exist
 *
 * @param <T>
 */
public abstract class ListBox<T extends Displayable> extends FormObject implements FormObjectWithChildren {
	ArrayList<ListBoxElement<T>> elements;
	ListBoxElement<T> selectedElement;

	/**
	 * Constructs a ListBox at the stated coordinates, width and height
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public ListBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setElements(new ArrayList<ListBoxElement<T>>());
	}

	/**
	 * Returns whether a click is in a ListBoxElement
	 * 
	 * @param e
	 *            ListBoxElement to be checked
	 * @param clickX
	 *            x-coordinate of the click
	 * @param clickY
	 *            y-coordinate of the click
	 * @param elementX
	 *            x-coordinate of the element
	 * @param elementY
	 *            y-coordinate of the element
	 * @return true when the click is in the ListBoxElement, false otherwise
	 */
	boolean isInElement(ListBoxElement<T> e, int clickX, int clickY, int elementX, int elementY) {
		boolean isInHorizontal = clickX >= elementX && clickX < elementX + this.getWidth();
		boolean isInVertical = clickY >= elementY && clickY < elementY + e.getHeight();
		return isInHorizontal && isInVertical;
	}

	/**
	 * Returns whether this ListBox contains the stated ListBoxElement
	 * 
	 * @param el
	 *            the ListBoxElement to be checked
	 * @return true when the ListBox contains the given ListBoxElement, false
	 *         otherwise
	 */
	public boolean contains(T el) {
		return getElements().contains(el);
	}

	@Override
	public void unfocusChildren() {
		this.getListboxElements().stream().forEach(x -> x.setFocused(false));
	}

	@Override
	public void onClick(MouseClick click) {
		// Check on which element there has been clicked.
		int x = this.getX();
		int y = this.getY();
		int sumOfVerticalMovement = 0;
		for (ListBoxElement<T> e : this.getListboxElements()) {
			boolean isin = isInElement(e, click.getX(), click.getY(), x, y + sumOfVerticalMovement);
			if (isin) {
				this.setSelectedElement(e);
				e.setFocused(true);
				this.onAction();
				return;
			}
			sumOfVerticalMovement += e.getHeight();
		}
		this.setSelectedElement(null);
		this.onAction();
	}

	@Override
	public void draw(Graphics g) {
		// We use translating, so it's easier to remove an element from the
		// middle of the list.
		int translatedX = this.getX();
		int translatedY = this.getY();
		int sumOfVerticalTranslations = 0;
		g.translate(translatedX, translatedY);
		for (ListBoxElement<T> e : this.getListboxElements()) {
			Color c = g.getColor();
			if (e.equals(this.getSelectedElement()))
				g.setColor(Color.RED);
			e.draw(g);
			g.setColor(c);
			sumOfVerticalTranslations += e.getHeight();
			g.translate(0, e.getHeight());
		}
		g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
	}

	@Override
	void setFocused(boolean b) {
		super.setFocused(b);
		System.out.println("Ok");
	}

	/**
	 * Adds a ListBoxElement to this ListBox
	 * 
	 * @param e
	 *            ListBoxElement to be added
	 */
	public void addElement(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		getListboxElements().add(lbe);
		if (!getListboxElements().stream().anyMatch(x -> x.isFocused()))
			setSelectedElement(lbe);
	}

	/**
	 * Removes the first occurrence of the element in the list.
	 * 
	 * @param e
	 *            ListBoxElement to be removed
	 */
	public void removeElement(T e) {
		Collection<ListBoxElement<T>> clone = new ArrayList<ListBoxElement<T>>(getListboxElements());
		for (ListBoxElement<T> lbe : clone) {
			if (lbe.getObject().equals(e))
				getListboxElements().remove(lbe);
		}
	}

	/**
	 * Switches the ListBoxElement that is currently selected with the stated
	 * ListBoxElement
	 * 
	 * @param e
	 *            ListBoxElement to switch
	 */
	void switchSelectedElementWith(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		int index = getListboxElements().indexOf(this.getSelectedElement());
		getListboxElements().set(index, lbe);
		this.setSelectedElement(lbe);
	}

	/**
	 * Unselects all objects.
	 */
	public void unselect() {
		this.setSelectedElement(null);
	}

	/**
	 * Returns the ListBoxElements of this ListBox
	 * 
	 * @return list of all the ListBoxElements of this ListBox
	 */
	final ArrayList<ListBoxElement<T>> getListboxElements() {
		return elements;
	}

	/**
	 * Returns the objects of the ListBoxElements of this ListBox
	 * 
	 * @return ArrayList with the objects of the ListBoxElements of this ListBox
	 */
	public final ArrayList<T> getElements() {
		ArrayList<T> c = new ArrayList<T>();
		for (ListBoxElement<T> t : this.getListboxElements())
			c.add(t.getObject());
		return c;
	}

	/**
	 * Set the ListBoxElements of this ListBox to the stated list of
	 * ListBoxElements
	 * 
	 * @param elements
	 *            list of ListBoxElements to be set
	 */
	protected final void setElements(ArrayList<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	/**
	 * Get the selected element of this ListBox
	 * 
	 * @return the selected element
	 */
	private final ListBoxElement<T> getSelectedElement() {
		return this.selectedElement;
	}

	/**
	 * Return the object of the ListBoxElement that is currently selected
	 * 
	 * @return the object of the selected ListBoxElement
	 */
	public final T getSelectedObject() {
		ListBoxElement<T> lb = this.getSelectedElement();
		if (lb == null)
			return null;
		return lb.getObject();
	}

	/**
	 * Set the selected element of this ListBox
	 * 
	 * @param selectedElement
	 *            ListBoxElement to set as selected element of this ListBox
	 */
	final void setSelectedElement(ListBoxElement<T> selectedElement) {
		this.selectedElement = selectedElement;
	}

	/**
	 * Returns the clicked ListBoxElement
	 */
	public FormObject getClickedChild() {
		return this.getSelectedElement();
	}

	/**
	 * Returns the ListBoxElement that is currently selected
	 */
	public FormObject getFocusedChild() {
		for (ListBoxElement<T> l : this.getListboxElements()) {
			if (l.isFocused())
				return l;
		}
		return null;
	}

	@Override
	public boolean hasChildren() {
		return getListboxElements().size() > 0;
	}

	@Override
	public FormObject getFirstChild() {
		return getListboxElements().get(0);
	}

	@Override
	public FormObject getLastChild() {
		return getListboxElements().get(getListboxElements().size() - 1);
	}

	/**
	 * A class of ListBoxElements representing the elements of a ListBox
	 * 
	 * @param <T2>
	 */
	class ListBoxElement<T2 extends Displayable> extends FormObject implements FormObjectChild {
		protected T2 obj;

		/**
		 * Constructs a new ListBoxElement with the stated object
		 * 
		 * @param obj
		 * 		the object to use.
		 */
		ListBoxElement(T2 obj) {
			super(0, 0, 0, obj.getHeight());
			this.obj = obj;
		}

		/**
		 * Returns the displayable String of the object of this ListBoxElement
		 * 
		 * @return the displayable string.
		 */
		public String getDisplayableString() {
			return obj.getDisplayableString();
		}

		/**
		 * Draws this element at the origin.
		 * 
		 * @param g
		 * 		the graphics object
		 */
		public void draw(Graphics g) {
			Color c = g.getColor();
			if (this.isFocused())
				g.setColor(Color.BLUE);

			int descent = g.getFontMetrics().getDescent();
			g.drawString(obj.getDisplayableString(), 0, getHeight() - descent);
			g.setColor(c);
		}

		@SuppressWarnings("unchecked")
		@Override
		void setFocused(boolean b) {
			super.setFocused(b);

			if (b) {
				setSelectedElement((ListBox<T>.ListBoxElement<T>) this);
			}
			onAction();
		}

		public T2 getObject() {
			return this.obj;
		}

		@Override
		public void onClick(MouseClick click) {

		}

		@Override
		protected void onAction() {
			ListBox.this.onAction();

		}

		@Override
		public FormObject getNextSibling() {
			FormObject s = getFocusedChild();
			int currentIndex = getListboxElements().indexOf(s);
			setSelectedElement(getListboxElements().get(currentIndex + 1));
			return getListboxElements().get(currentIndex + 1);
		}

		@Override
		public FormObject getPreviousSibling() {
			if (getFocusedChild() != null) {
				FormObject s = getFocusedChild();
				int currentIndex = getListboxElements().indexOf(s);
				setSelectedElement(getListboxElements().get(currentIndex - 1));
				return getListboxElements().get(currentIndex - 1);
			} else {
				return getListboxElements().get(getListboxElements().size() - 1);
			}
		}

		@Override
		public boolean hasNextSibling() {
			FormObject s = getFocusedChild();
			int currentIndex = getListboxElements().indexOf(s);
			return (currentIndex + 1) < getListboxElements().size();
		}

		@Override
		public boolean hasPreviousSibling() {
			FormObject s = getFocusedChild();
			int currentIndex = getListboxElements().indexOf(s);
			return (currentIndex - 1) >= 0 || (currentIndex == -1 && getListboxElements().size() > 0);
		}

		@Override
		public FormObject getParent() {
			return ListBox.this;
		}

		public int getHeight() {
			return getObject().getHeight();
		}

		public int getWidth() {
			return getObject().getWidth();
		}

	}

	@Override
	protected int getWidth() {
		int max = super.getWidth();
		for (ListBoxElement<T> lbe : getListboxElements()) {
			if (lbe.getWidth() > max)
				max = lbe.getWidth();
		}
		return max;
	}

	@Override
	protected int getHeight() {
		int height = 0;
		for (ListBoxElement<T> lbe : getListboxElements()) {
			height += lbe.getHeight();
		}
		return Math.max(height, super.getHeight());
	}

}
