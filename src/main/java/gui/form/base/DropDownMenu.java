package gui.form.base;


import gui.form.base.ListBox.ListBoxElement;
import gui.inputHandlers.clicks.MouseClick;

//Ik ben hier aan het prutsen met de generics, als ge een quickfix ziet, voel u vrij
public class DropDownMenu<T extends Displayable> extends ListBox<Displayable>{

	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
	}


	
	
	@Override
	protected void onAction() {
		// TODO Auto-generated method stub
		
	}
	
	class DropDownMenuElement extends ListBoxElement{

		DropDownMenuElement(Displayable obj) {
			super(obj);
		}
		
		@Override
		void onClick(MouseClick click){
			//TODO
		}
	}
	
}

/*public class DropDownMenu<T extends Clickable> extends FormObject implements FormObjectWithChildren {
	ArrayList<DropDownMenuElement<T>> elements;
	DropDownMenuElement<T> selectedElement;

	public DropDownMenu(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setElements(new ArrayList<DropDownMenuElement<T>>());
	}

	private boolean isInElement(DropDownMenuElement<T> e, int clickX, int clickY, int elementX, int elementY) {
		boolean isInHorizontal = clickX >= elementX && clickX < elementX + this.getWidth();
		boolean isInVertical = clickY >= elementY && clickY < elementY + e.getHeight();
		return isInHorizontal && isInVertical;
	}

	@Override
	public void unfocusChildren() {
		System.out.println("Unfocusing Children");
		this.getElements().stream().forEach(x -> x.setFocused(false));
	}

	@Override
	void onClick(MouseClick click) {
		// TODO Auto-generated method stub

	}

	@Override
	void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onAction() {
		// TODO Auto-generated method stub

	}

	public ArrayList<DropDownMenuElement<T>> getElements() {
		return elements;
	}

	public void setElements(ArrayList<DropDownMenuElement<T>> elements) {
		this.elements = elements;
	}

	public DropDownMenuElement<T> getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(DropDownMenuElement<T> selectedElement) {
		this.selectedElement = selectedElement;
	}

	@Override
	public FormObject getFirstChild() {
		return this.getElements().get(0);
	}

	@Override
	public FormObject getLastChild() {
		return this.getElements().get(this.getElements().size()-1);
	}

	@Override
	public boolean hasChildren() {
		return this.getElements().size() > 0;
	}

	@Override
	public FormObject getClickedChild() {
		return this.getSelectedElement();
	}

	@Override
	public FormObject getFocusedChild() {
		for (DropDownMenuElement<T> e : this.getElements()) {
			if (e.isFocused())
				return e;
		}
		return null;
	}

	class DropDownMenuElement<T2 extends Clickable> extends FormObject implements FormObjectChild {
		private T2 obj;
		private int height;

		DropDownMenuElement(int x, int y, int width, int height) {
			super(x, y, width, height);
			// TODO Auto-generated constructor stub
		}

		@Override
		public FormObject getNextSibling() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public FormObject getPreviousSibling() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasNextSibling() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPreviousSibling() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public FormObject getParent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		void onClick(MouseClick click) {
			// TODO Auto-generated method stub

		}

		@Override
		void draw(Graphics g) {
			// TODO Auto-generated method stub

		}

		@Override
		protected void onAction() {
			// TODO Auto-generated method stub

		}

	}

}*/
