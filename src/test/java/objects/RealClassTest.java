package objects;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Test;

import visualobjects.Container;
import visualobjects.VisualClass;

public class RealClassTest {

	@Test
	public void testAddAttributes() {
		RealClass realclass = new RealClass();
		int initialSize = realclass.getAttributes().size();
		Attribute attr = realclass.addAttribute();
		int finalSize = realclass.getAttributes().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildAttribute1() {
		RealClass realclass = new RealClass();
		Attribute attr = realclass.addAttribute();		
		realclass.deleteChild(attr);
		assertFalse(realclass.getAttributes().contains(attr));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testDeleteChildAttribute2() {
		RealClass realclass = new RealClass();
		Attribute attr = realclass.addAttribute();		
		realclass.deleteChild(attr);
		realclass.deleteChild(attr);
	}

	@Test
	public void testDeleteChildMethod1() {
		RealClass realclass = new RealClass();
		Method meth = realclass.addMethod();		
		realclass.deleteChild(meth);
		assertFalse(realclass.getMethods().contains(meth));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testDeleteChildMethod2() {
		RealClass realclass = new RealClass();
		Method meth = realclass.addMethod();		
		realclass.deleteChild(meth);
		realclass.deleteChild(meth);
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
	
	@Test
	public void getAttributesTest1() {
		RealClass realclass = new RealClass();
		realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		assertEquals(3, realclass.getAttributes().size());
	}
	
	@Test
	public void getAttributesTest2() {
		RealClass realclass = new RealClass();
		Attribute attr = realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		Collection<Attribute> attrs = realclass.getAttributes();
		attrs.remove(attr);
		assertEquals(3, realclass.getAttributes().size());
	}
	
	@Test
	public void getAttributesTest3() {
		RealClass realclass = new RealClass();
		Attribute attr = realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		Collection<Attribute> attrs = realclass.getAttributes();
		attrs.remove(attr);
		assertTrue(realclass.getAttributes().contains(attr));
	}
	
	@Test
	public void getMethodesTest1() {
		RealClass realclass = new RealClass();
		realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		assertEquals(3, realclass.getMethods().size());
	}
	
	@Test
	public void getMethodesTest2() {
		RealClass realclass = new RealClass();
		Method meth = realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		Collection<Method> meths = realclass.getMethods();
		meths.remove(meth);
		assertEquals(3, realclass.getMethods().size());
	}	
	
	@Test
	public void getMethodesTest3() {
		RealClass realclass = new RealClass();
		Method meth = realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		Collection<Method> meths = realclass.getMethods();
		meths.remove(meth);
		assertTrue(realclass.getMethods().contains(meth));
	}	

}
