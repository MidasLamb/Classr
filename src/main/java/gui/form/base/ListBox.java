package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.inputHandlers.clicks.MouseClick;

public abstract class ListBox<T extends Displayable> extends FormObject implements FormObjectWithChildren{
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
		for (ListBoxElement<T> e : this.getListboxElements()) {
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
		for (ListBoxElement<T> e : this.getListboxElements()) {
			e.draw(g);
			sumOfVerticalTranslations += e.getHeight();
			g.translate(0, e.getHeight());
		}
		g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
	}

	public void addElement(T e) {
		ListBoxElement<T> lbe = new ListBoxElement<T>(e);
		getListboxElements().add(lbe);
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
	
	public final ArrayList<T> getElements(){
		ArrayList<T> c = new ArrayList<T>();
		for (ListBoxElement<T> t: this.getListboxElements())
			c.add(t.getObject());
		return c;
	}

	private final void setElements(ArrayList<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	private final ListBoxElement<T> getSelectedElement() {
		for (ListBoxElement<T> l: this.getListboxElements()){
			if (l.isFocused())
				return l;
		}
			
		return null;
	}

	public final T getSelectedObject() {
		ListBoxElement<T> lb = this.getSelectedElement();
		if (lb == null)
			return null;
		return lb.getObject();
	}

	private final void setSelectedElement(ListBoxElement<T> selectedElement) {
		if (this.selectedElement != null)
			this.selectedElement.setFocused(false);
		this.selectedElement = selectedElement;
		if (selectedElement != null)
			selectedElement.setFocused(true);
	}
	
	
	
	@Override
	public FormObject getNextChild(){
		ListBoxElement<T> s = this.getSelectedElement();
		int currentIndex = getListboxElements().indexOf(s);
		this.setSelectedElement(getListboxElements().get(currentIndex + 1));
		return getListboxElements().get(currentIndex + 1);
	}
	
	@Override
	public FormObject getPreviousChild(){
		ListBoxElement<T> s = this.getSelectedElement();
		int currentIndex = getListboxElements().indexOf(s);
		this.setSelectedElement(getListboxElements().get(currentIndex - 1));
		return getListboxElements().get(currentIndex - 1);
	}
	
	@Override
	public boolean hasNextChild(){
		ListBoxElement<T> s = this.getSelectedElement();
		int currentIndex = getListboxElements().indexOf(s);
		return (currentIndex + 1) < getListboxElements().size();
	}
	@Override
	public boolean hasPreviousChild(){
		ListBoxElement<T> s = this.getSelectedElement();
		int currentIndex = getListboxElements().indexOf(s);
		return (currentIndex - 1) >= 0; 
	}

	class ListBoxElement<T extends Displayable> extends FormObject implements FormObjectChild{
		private T obj;
		private int height;

		ListBoxElement(T obj) {
			super(0,0,0,0);
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
			Color c = g.getColor();
			if (this.isFocused())
				g.setColor(Color.BLUE);
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(obj.getDisplayableString(), 0, height - descent);
			g.setColor(c);
		}

		public int getHeight() {
			return this.height;
		}

		public T getObject() {
			return this.obj;
		}

		@Override
		void onClick(MouseClick click) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void onAction() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public FormObject getNextChild() {
			return ListBox.this.getNextChild();
		}

		@Override
		public FormObject getPreviousChild() {
			return ListBox.this.getPreviousChild();
		}

		@Override
		public boolean hasNextChild() {
			return ListBox.this.hasNextChild();
		}

		@Override
		public boolean hasPreviousChild() {
			return ListBox.this.hasPreviousChild();
		}

	}

}
