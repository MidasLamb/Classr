package gui.form.utility;

import java.util.ArrayList;
import java.util.Collection;

import gui.form.base.Button;

public abstract class OkButton extends Button implements Checker{
	private Collection<Checkable> constraints;

	public OkButton(int x, int y, int width, int height) {
		super("Ok", x, y, width, height);
		this.constraints = new ArrayList<Checkable>();
	}

	@Override
	protected void onAction() {
		this.checkConstraints();
		this.onOk();
	}
	
	protected abstract void onOk();
	
	
	public void addCheckableConstraint(Checkable c){
		this.constraints.add(c);
		this.checkConstraints();
		c.addChecker(this);
	}
	
	public void checkConstraints(){
		for(Checkable c: constraints){
			if (!c.check()){
				this.setEnabled(false);
				return;
			}
		}
		this.setEnabled(true);
	}
}
