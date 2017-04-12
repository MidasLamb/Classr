package gui.form.utility;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import gui.form.base.InputBox;

public class RegexCheckedInputBox extends InputBox implements Checkable {
	private String regex;
	private Collection<Checker> checkers;

	public RegexCheckedInputBox(String text, String regex, int x, int y, int width, int height) {
		super(text, x, y, width, height);
		this.setRegex(regex);
		this.setCheckers(new ArrayList<Checker>());
	}

	@Override
	protected void onAction() {
		this.notifyUpdate();
	}

	protected final void notifyUpdate() {
		for (Checker c : this.getCheckers()) {
			c.checkConstraints();
		}
	}

	@Override
	public boolean check() {
		return super.getTextObject().getText().matches(this.getRegex());
	}

	@Override
	protected void draw(Graphics g) {
		Color prev = g.getColor();
		if (!this.check())
			g.setColor(Color.RED);
		super.draw(g);
		g.setColor(prev);
	}

	@Override
	public void addChecker(Checker c) {
		this.getCheckers().add(c);
	}

	private final String getRegex() {
		return regex;
	}

	private final void setRegex(String regex) {
		this.regex = regex;
	}

	private final Collection<Checker> getCheckers() {
		return checkers;
	}

	private final void setCheckers(Collection<Checker> checkers) {
		this.checkers = checkers;
	}

}
