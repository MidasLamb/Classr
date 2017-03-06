package objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class LogicalObjectTest {

	@Test
	public void testName() {
		Attribute attr = new Attribute();
		attr.setName("Test");
		assertEquals("Test", attr.getName());
	}

}
