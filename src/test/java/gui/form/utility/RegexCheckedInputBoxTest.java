package gui.form.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexCheckedInputBoxTest {

	@Test
	public void checkRegexTest1() {
		RegexCheckedInputBox box = getInputBox("aap", ".*");
		assertTrue(box.check());
	}
	
	@Test
	public void checkRegexTest2() {
		RegexCheckedInputBox box = getInputBox("aap", "10");
		assertFalse(box.check());
	}
	
	@Test
	public void checkRegexTest3() {
		RegexCheckedInputBox box = getInputBox("aap", ".*");
		assertTrue(box.isValidString("kjchqisjc"));
	}
	
	@Test
	public void checkRegexTest4() {
		RegexCheckedInputBox box = getInputBox("aap", "10");
		assertFalse(box.isValidString("hbsddkj"));
	}
	
	@Test
	public void checkRegexTest5() {
		RegexCheckedInputBox box = getInputBox("aap", "10");
		assertTrue(box.isValidString("10"));
	}
	
	private RegexCheckedInputBox getInputBox(String text, String regex){
		return new RegexCheckedInputBox(text, regex, 0, 0, 10, 10);
	}

}
