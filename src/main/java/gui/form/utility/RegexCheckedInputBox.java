package gui.form.utility;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import gui.form.base.InputBox;

/**
 * InputBox of which the contents can be validated with a regular expression.
 */
public class RegexCheckedInputBox extends InputBox implements Checkable {
	private String regex;
	private Collection<Checker> checkers;

	/**
	 * Construct a new RegexCheckedInputBox given its text, regex, coordinates
	 * and dimensions.
	 * 
	 * @param text
	 *            text that should be displayed in the InputBox
	 * @param regex
	 *            regular expression used to validate the InputBox
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param width
	 *            width
	 * @param height
	 *            height
	 */
	public RegexCheckedInputBox(String text, String regex, int x, int y, int width, int height) {
		super(text, x, y, width, height);
		this.setRegex(regex);
		this.setCheckers(new ArrayList<Checker>());
	}

	@Override
	protected void onAction() {
		this.notifyUpdate();
	}

	
	/**
	 * Notify checkers that the constraints should be checked again.
	 */
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
	public void draw(Graphics g) {
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
