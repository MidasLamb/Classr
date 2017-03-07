package objects;

import static org.junit.Assert.*;

import org.junit.Test;

import mouse.clicks.DoubleClick;
import mouse.clicks.Drag;
import mouse.clicks.SingleClick;
import objects.RealClass;
import visualobjects.Container;
import visualobjects.visualclass.VisualClass;

public class RealClassTest {

	@Test
	public void testRealClass() {
		Container container = new Container(0, 0, 10, 10);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass realclass = new RealClass();
		realclass.setVisualObject(vc);
		
		assertTrue(realclass.getVisualObject() == vc);
	}

	@Test
	public void testAddAttributes() {
		RealClass realclass = new RealClass();
		int initialSize = realclass.getAttributes().size();
		Attribute attr = realclass.addAttribute();
		int finalSize = realclass.getAttributes().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildAttribute() {
		RealClass realclass = new RealClass();
		Attribute attr = realclass.addAttribute();
		
		realclass.deleteChild(attr);
		assertFalse(realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildMethod() {
		RealClass realclass = new RealClass();
		Method meth = realclass.addMethod();
		
		realclass.deleteChild(meth);
		assertFalse(realclass.getMethods().contains(meth));
	}

	@Test
	public void testDeleteChildAssociation() {
		RealClass rc1 = new RealClass();
		RealClass rc2 = new RealClass();
		Association ass = new Association(rc1, rc2);
		
		rc1.deleteChild(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}

	@Test
	public void testAddMethod() {
		RealClass realclass = new RealClass();
		int initialSize = realclass.getMethods().size();
		Method meth = realclass.addMethod();
		int finalSize = realclass.getMethods().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getMethods().contains(meth));
	}

	@Test
	public void testAddAssociation() {
		RealClass rc1 = new RealClass();
		RealClass rc2 = new RealClass();
		Association ass = new Association(rc1, rc2);
		assertTrue(rc1.getAssociations().contains(ass) && rc2.getAssociations().contains(ass));
	}

	@Test
	public void testDeleteAssociation() {
		RealClass rc1 = new RealClass();
		RealClass rc2 = new RealClass();
		Association ass = new Association(rc1, rc2);
		
		rc1.deleteAssociation(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}

}
