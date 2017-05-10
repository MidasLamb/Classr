package gui.form.base;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.inputHandlers.clicks.MouseClick;

public class DropDownMenu<T extends Displayable> extends ListBox<Displayable> {
	private boolean enabled;
	private ArrayList<DropDownMenuElement> elements;

	

	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public ArrayList<DropDownMenuElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<DropDownMenuElement> elements) {
		this.elements = elements;
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
			for (DropDownMenuElement e : this.getElements()) {
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
	protected void onAction() {}

	class DropDownMenuElement extends ListBoxElement<MenuItem> implements Displayable{
		private MenuItem menuItem;

		DropDownMenuElement(MenuItem obj) {
			super(obj);
		}

		public MenuItem getMenuItem() {
			return menuItem;
		}

		public void setMenuItem(MenuItem menuItem) {
			this.menuItem = menuItem;
		}

		@Override
		void onClick(MouseClick click) {
			// TODO
		}
	}

}

/*
 * public class DropDownMenu<T extends Clickable> extends FormObject implements
 * FormObjectWithChildren { ArrayList<DropDownMenuElement<T>> elements;
 * DropDownMenuElement<T> selectedElement;
 * 
 * public DropDownMenu(int x, int y, int width, int height) { super(x, y, width,
 * height); this.setElements(new ArrayList<DropDownMenuElement<T>>()); }
 * 
 * private boolean isInElement(DropDownMenuElement<T> e, int clickX, int clickY,
 * int elementX, int elementY) { boolean isInHorizontal = clickX >= elementX &&
 * clickX < elementX + this.getWidth(); boolean isInVertical = clickY >=
 * elementY && clickY < elementY + e.getHeight(); return isInHorizontal &&
 * isInVertical; }
 * 
 * @Override public void unfocusChildren() {
 * System.out.println("Unfocusing Children");
 * this.getElements().stream().forEach(x -> x.setFocused(false)); }
 * 
 * @Override void onClick(MouseClick click) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override void draw(Graphics g) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override protected void onAction() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * public ArrayList<DropDownMenuElement<T>> getElements() { return elements; }
 * 
 * public void setElements(ArrayList<DropDownMenuElement<T>> elements) {
 * this.elements = elements; }
 * 
 * public DropDownMenuElement<T> getSelectedElement() { return selectedElement;
 * }
 * 
 * public void setSelectedElement(DropDownMenuElement<T> selectedElement) {
 * this.selectedElement = selectedElement; }
 * 
 * @Override public FormObject getFirstChild() { return
 * this.getElements().get(0); }
 * 
 * @Override public FormObject getLastChild() { return
 * this.getElements().get(this.getElements().size()-1); }
 * 
 * @Override public boolean hasChildren() { return this.getElements().size() >
 * 0; }
 * 
 * @Override public FormObject getClickedChild() { return
 * this.getSelectedElement(); }
 * 
 * @Override public FormObject getFocusedChild() { for (DropDownMenuElement<T> e
 * : this.getElements()) { if (e.isFocused()) return e; } return null; }
 * 
 * class DropDownMenuElement<T2 extends Clickable> extends FormObject implements
 * FormObjectChild { private T2 obj; private int height;
 * 
 * DropDownMenuElement(int x, int y, int width, int height) { super(x, y, width,
 * height); // TODO Auto-generated constructor stub }
 * 
 * @Override public FormObject getNextSibling() { // TODO Auto-generated method
 * stub return null; }
 * 
 * @Override public FormObject getPreviousSibling() { // TODO Auto-generated
 * method stub return null; }
 * 
 * @Override public boolean hasNextSibling() { // TODO Auto-generated method
 * stub return false; }
 * 
 * @Override public boolean hasPreviousSibling() { // TODO Auto-generated method
 * stub return false; }
 * 
 * @Override public FormObject getParent() { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override void onClick(MouseClick click) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override void draw(Graphics g) { // TODO Auto-generated method stub
 * 
 * }
 * 
 * @Override protected void onAction() { // TODO Auto-generated method stub
 * 
 * }
 * 
 * }
 * 
 * }
 */
