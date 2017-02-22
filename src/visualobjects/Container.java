package visualobjects;


import visualobjects.visualclass.Class;

public class Container extends VisualObject {

	public Container(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VisualObject select(int x, int y) {
		for (VisualObject v : this.getChildren()){
			if (v.isIn(x, y)){
				return v.select(x, y);
			}
		}
		Class c = new Class(x,y, 10,10);
		this.addChild(c);
		return c;
	}

}
