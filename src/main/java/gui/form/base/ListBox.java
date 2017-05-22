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
	 * Returns whether a click is in a LitBoxElement
	 * 
	 * @param e
	 * @param clickX
	 * @param clickY
	 * @param elementX
	 * @param elementY
	 * @return
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
	 * @return
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
	 */
	public void removeElement(T e) {
		Collection<ListBoxElement<T>> clone = new ArrayList<ListBoxElement<T>>(getListboxElements());
		for (ListBoxElement<T> lbe : clone) {
			if (lbe.getObject().equals(e))
				getListboxElements().remove(lbe);
		}
	}

	/**
	 * Removes the ListBoxElement that is currently selected from this ListBox
	 */
	public void removeSelectedElement() {
		int index = getListboxElements().indexOf(this.getSelectedElement());
		getListboxElements().remove(this.getSelectedElement());
		if (index < getListboxElements().size()) {
			this.setSelectedElement(getListboxElements().get(index));
		} else {
			this.setSelectedElement(null);
		}
	}

	/**
	 * Switches the ListBoxElement that is currently selected with the stated
	 * ListBoxElement
	 * 
	 * @param e
	 */
	public void switchSelectedElementWith(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		int index = getListboxElements().indexOf(this.getSelectedElement());
		getListboxElements().set(index, lbe);
		this.setSelectedElement(lbe);
	}

	/**
	 * Returns the ListBoxElements of this ListBox
	 * 
	 * @return
	 */
	final ArrayList<ListBoxElement<T>> getListboxElements() {
		return elements;
	}

	/**
	 * Returns the objects of the ListBoxElements of this ListBox
	 * 
	 * @return
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
	 */
	protected final void setElements(ArrayList<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	private final ListBoxElement<T> getSelectedElement() {
		return this.selectedElement;
	}

	/**
	 * Return the object of the ListBoxElement that is currently selected
	 * 
	 * @return
	 */
	public final T getSelectedObject() {
		ListBoxElement<T> lb = this.getSelectedElement();
		if (lb == null)
			return null;
		return lb.getObject();
	}

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
		 */
		ListBoxElement(T2 obj) {
			super(0, 0, 0, 0);
			this.obj = obj;
		}

		/**
		 * Returns the displayable String of the object of this ListBoxElement
		 * 
		 * @return
		 */
		public String getDisplayableString() {
			return obj.getDisplayableString();
		}

		/**
		 * Draws this element at the origin.
		 * 
		 * @param g
		 */
		public void draw(Graphics g) {
			Color c = g.getColor();
			if (this.isFocused())
				g.setColor(Color.BLUE);
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(obj.getDisplayableString(), 0, height - descent);
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

	}

}
