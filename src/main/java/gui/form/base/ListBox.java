package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.inputHandlers.clicks.MouseClick;

public abstract class ListBox<T extends Displayable> extends FormObject implements FormObjectWithChildren {
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
	public void unfocusChildren(){
		this.getListboxElements().stream().forEach(x -> x.setFocused(false));
	}

	@Override
	void onClick(MouseClick click) {
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
	public
	void draw(Graphics g) {
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
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		getListboxElements().remove(lbe);
	}

	public void removeSelectedElement() {
		int index = getListboxElements().indexOf(this.getSelectedElement());
		getListboxElements().remove(this.getSelectedElement());
		if (index < getListboxElements().size()) {
			this.setSelectedElement(getListboxElements().get(index));
		} else {
			this.setSelectedElement(null);
		}
	}

	public void switchSelectedElementWith(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		int index = getListboxElements().indexOf(this.getSelectedElement());
		getListboxElements().set(index, lbe);
		this.setSelectedElement(lbe);
	}

	private final ArrayList<ListBoxElement<T>> getListboxElements() {
		return elements;
	}

	public final ArrayList<T> getElements() {
		ArrayList<T> c = new ArrayList<T>();
		for (ListBoxElement<T> t : this.getListboxElements())
			c.add(t.getObject());
		return c;
	}

	private final void setElements(ArrayList<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	private final ListBoxElement<T> getSelectedElement() {
		return this.selectedElement;
	}

	public final T getSelectedObject() {
		ListBoxElement<T> lb = this.getSelectedElement();
		if (lb == null)
			return null;
		return lb.getObject();
	}

	final void setSelectedElement(ListBoxElement<T> selectedElement) {
		this.selectedElement = selectedElement;
	}
	
	public FormObject getClickedChild(){
		return this.getSelectedElement();
	}

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

	class ListBoxElement<T2 extends Displayable> extends FormObject implements FormObjectChild {
		protected T2 obj;
		private int height;

		ListBoxElement(T2 obj) {
			super(0, 0, 0, 0);
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

		public int getHeight() {
			return this.height;
		}

		public T2 getObject() {
			return this.obj;
		}

		@Override
		void onClick(MouseClick click) {

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
