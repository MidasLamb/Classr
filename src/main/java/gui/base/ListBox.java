package gui.base;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import inputHandlers.clicks.MouseClick;

public abstract class ListBox<T extends Displayable> extends FormObject {
	Collection<ListBoxElement<T>> elements;
	ListBoxElement<T> selectedElement;

	public ListBox(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setElements(new ArrayList<ListBoxElement<T>>());
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	@Override
	void draw(Graphics g) {
		int translatedX = this.getX();
		int translatedY = this.getY();
		int sumOfVerticalTranslations = 0;
		g.translate(translatedX, translatedY);
		for (ListBoxElement<T> e: this.getElements()){
			e.draw(g);
			sumOfVerticalTranslations += e.getHeight();
			g.translate(0, e.getHeight());
		}
		g.translate(-translatedX, -(translatedY + sumOfVerticalTranslations));
	}

	@Override
	protected void onAction() {

	}

	private final Collection<ListBoxElement<T>> getElements() {
		return elements;
	}

	private final void setElements(Collection<ListBoxElement<T>> elements) {
		this.elements = elements;
	}

	private final ListBoxElement<T> getSelectedElement() {
		return selectedElement;
	}

	private final void setSelectedElement(ListBoxElement<T> selectedElement) {
		this.selectedElement = selectedElement;
	}
	
	class ListBoxElement<T2 extends Displayable>{
		private T2 obj;
		private int height;
		ListBoxElement(T2 obj){
			this.obj = obj;
		}

		public String getDisplayableString() {
			return obj.getDisplayableString();
		}
		
		

		void onClick(MouseClick click) {
			// TODO Auto-generated method stub
			
		}

		void draw(Graphics g) {
			height = g.getFontMetrics().getHeight();
			int descent = g.getFontMetrics().getDescent();
			g.drawString(obj.getDisplayableString(), 0, height - descent);
			
		}

		protected void onAction() {
			// TODO Auto-generated method stub
			
		}
		
		public int getHeight(){
			return this.height;
		}
		
	}

}
