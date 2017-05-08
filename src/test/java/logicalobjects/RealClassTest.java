package logicalobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Test;

public class RealClassTest {

	@Test
	public void testAddAttributes() {
		LogicalClass realclass = new LogicalClass();
		int initialSize = realclass.getAttributes().size();
		Attribute attr = realclass.addAttribute();
		int finalSize = realclass.getAttributes().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getAttributes().contains(attr));
	}

	@Test
	public void testDeleteChildAttribute1() {
		LogicalClass realclass = new LogicalClass();
		Attribute attr = realclass.addAttribute();		
		realclass.deleteChild(attr);
		assertFalse(realclass.getAttributes().contains(attr));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testDeleteChildAttribute2() {
		LogicalClass realclass = new LogicalClass();
		Attribute attr = realclass.addAttribute();		
		realclass.deleteChild(attr);
		realclass.deleteChild(attr);
	}

	@Test
	public void testDeleteChildMethod1() {
		LogicalClass realclass = new LogicalClass();
		Method meth = realclass.addMethod();		
		realclass.deleteChild(meth);
		assertFalse(realclass.getMethods().contains(meth));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testDeleteChildMethod2() {
		LogicalClass realclass = new LogicalClass();
		Method meth = realclass.addMethod();		
		realclass.deleteChild(meth);
		realclass.deleteChild(meth);
	}

	@Test
	public void testDeleteChildAssociation() {
		LogicalClass rc1 = new LogicalClass();
		LogicalClass rc2 = new LogicalClass();
		Association ass = new Association(rc1, rc2);		
		rc1.deleteChild(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}

	@Test
	public void testAddMethod() {
		LogicalClass realclass = new LogicalClass();
		int initialSize = realclass.getMethods().size();
		Method meth = realclass.addMethod();
		int finalSize = realclass.getMethods().size();
		assertTrue(finalSize == initialSize + 1 && realclass.getMethods().contains(meth));
	}

	@Test
	public void testAddAssociation() {
		LogicalClass rc1 = new LogicalClass();
		LogicalClass rc2 = new LogicalClass();
		Association ass = new Association(rc1, rc2);
		assertTrue(rc1.getAssociations().contains(ass) && rc2.getAssociations().contains(ass));
	}

	@Test
	public void testDeleteAssociation() {
		LogicalClass rc1 = new LogicalClass();
		LogicalClass rc2 = new LogicalClass();
		Association ass = new Association(rc1, rc2);
		
		rc1.deleteAssociation(ass);
		assertFalse(rc1.getAssociations().contains(ass));
	}
	
	@Test
	public void getAttributesTest1() {
		LogicalClass realclass = new LogicalClass();
		realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		assertEquals(3, realclass.getAttributes().size());
	}
	
	@Test
	public void getAttributesTest2() {
		LogicalClass realclass = new LogicalClass();
		Attribute attr = realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		Collection<Attribute> attrs = realclass.getAttributes();
		attrs.remove(attr);
		assertEquals(3, realclass.getAttributes().size());
	}
	
	@Test
	public void getAttributesTest3() {
		LogicalClass realclass = new LogicalClass();
		Attribute attr = realclass.addAttribute();
		realclass.addAttribute();
		realclass.addAttribute();
		Collection<Attribute> attrs = realclass.getAttributes();
		attrs.remove(attr);
		assertTrue(realclass.getAttributes().contains(attr));
	}
	
	@Test
	public void getMethodesTest1() {
		LogicalClass realclass = new LogicalClass();
		realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		assertEquals(3, realclass.getMethods().size());
	}
	
	@Test
	public void getMethodesTest2() {
		LogicalClass realclass = new LogicalClass();
		Method meth = realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		Collection<Method> meths = realclass.getMethods();
		meths.remove(meth);
		assertEquals(3, realclass.getMethods().size());
	}	
	
	@Test
	public void getMethodesTest3() {
		LogicalClass realclass = new LogicalClass();
		Method meth = realclass.addMethod();
		realclass.addMethod();
		realclass.addMethod();
		Collection<Method> meths = realclass.getMethods();
		meths.remove(meth);
		assertTrue(realclass.getMethods().contains(meth));
	}	

	@Test
	public void setClassName() {
		LogicalClass logicalClass = new LogicalClass();
		logicalClass.setName("MyClass");
		assertEquals("MyClass", logicalClass.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setInvalidClassName1() {
		LogicalClass logicalClass = new LogicalClass();
		logicalClass.setName("myClass");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setInvalidClassName2() {
		LogicalClass logicalClass = new LogicalClass();
		logicalClass.setName("My Class");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setInvalidClassName3() {
		LogicalClass logicalClass = new LogicalClass();
		logicalClass.setName("1NewClass");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setDuplicateMethodName() {
		LogicalClass logicalClass = new LogicalClass();
		Method method1 = logicalClass.addMethod();
		method1.setName("myMethod");
		Method method2 = logicalClass.addMethod();
		method2.setName("myMethod");
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void setDuplicateAttributeName() {
		LogicalClass logicalClass = new LogicalClass();
		Attribute attribute1 = logicalClass.addAttribute();
		attribute1.setName("myAttribute");
		Attribute attribute2 = logicalClass.addAttribute();
		attribute2.setName("myAttribute");
	}
}
