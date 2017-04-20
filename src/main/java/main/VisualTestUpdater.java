package main;

import java.lang.reflect.Method;

import canvaswindow.VisualTests;

/**
 * A Class to update all visual tests. Mostly used to automatically recreate all
 * tests when we know everything is ok and only a visual update happened.
 * 
 * @author Groep11
 *
 */
public class VisualTestUpdater {
	public static void main(String[] args) {
		try {
			VisualTests t = new VisualTests();
			t.update = true;
			Class<?> test = Class.forName("canvaswindow.VisualTests");
			Method[] methods = test.getMethods();
			for (Method m : methods)
				m.invoke(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
