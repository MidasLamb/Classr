package objects;

import static org.junit.Assert.*;

import org.junit.Test;
import objects.RealClass;
import visualobjects.visualclass.VisualClass;

public class RealClassTest {

	@Test
	public void testRealClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAttributes() {
		VisualClass vc = new VisualClass(0, 0, null);
		RealClass realclass = new RealClass(vc);
		int initialSize = realclass.getAttributes().size();
		Attribute attr = realclass.addAttribute();
		int finalSize = realclass.getAttributes().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildAttribute() {
		VisualClass vc = new VisualClass(0, 0, null);
		RealClass realclass = new RealClass(vc);
		Attribute attr = realclass.addAttribute();
		
		realclass.deleteChild(attr);
		assertFalse(realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildMethod() {
		fail();
	}

	@Test
	public void testDeleteChildAssociation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteChildLogical_objects() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddMethod() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAssociation() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAssociation() {
		fail("Not yet implemented");
	}

}
