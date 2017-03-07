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
		RealClass realclass = new RealClass(vc);
		
		assertTrue(realclass.getVisualClass() == vc);
	}

	@Test
	public void testAddAttributes() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass realclass = new RealClass(vc);
		int initialSize = realclass.getAttributes().size();
		Attribute attr = realclass.addAttribute();
		int finalSize = realclass.getAttributes().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildAttribute() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass realclass = new RealClass(vc);
		Attribute attr = realclass.addAttribute();
		
		realclass.deleteChild(attr);
		assertFalse(realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildMethod() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass realclass = new RealClass(vc);
		Method meth = realclass.addMethod();
		
		realclass.deleteChild(meth);
		assertFalse(realclass.getMethods().contains(meth));
	}

	@Test
	public void testDeleteChildAssociation() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass rc1 = new RealClass(vc);
		RealClass rc2 = new RealClass(vc);
		Association ass = new Association(rc1, rc2);
		
		rc1.deleteChild(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}

	@Test
	public void testAddMethod() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass realclass = new RealClass(vc);
		int initialSize = realclass.getMethods().size();
		Method meth = realclass.addMethod();
		int finalSize = realclass.getMethods().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getMethods().contains(meth));
	}

	@Test
	public void testAddAssociation() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass rc1 = new RealClass(vc);
		RealClass rc2 = new RealClass(vc);
		Association ass = new Association(rc1, rc2);
		assertTrue(rc1.getAssociations().contains(ass) && rc2.getAssociations().contains(ass));
	}

	@Test
	public void testDeleteAssociation() {
		Container container = new Container(0, 0, 1000, 1000);
		VisualClass vc = new VisualClass(0, 0, 0, container);
		RealClass rc1 = new RealClass(vc);
		RealClass rc2 = new RealClass(vc);
		Association ass = new Association(rc1, rc2);
		
		rc1.deleteAssociation(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}

}
