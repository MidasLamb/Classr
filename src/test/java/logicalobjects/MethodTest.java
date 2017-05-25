package logicalobjects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MethodTest {

	@Test
	public void getRealClass() {
		LogicalClass realclass = new LogicalClass();
		Method methode = realclass.addMethod();
		assertEquals(realclass, methode.getRealClass());
	}

	@Test
	public void deleteClass() {
		LogicalClass realclass = new LogicalClass();
		Method methode = realclass.addMethod();
		realclass.deleteChild(methode);
		assertEquals(realclass, methode.getRealClass());
	}

	@Test(expected = IllegalStateException.class)
	public void setMethodAbstractAndStatic1() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setAbstract(true);
		method.setStatic(true);
	}

	@Test(expected = IllegalStateException.class)
	public void setMethodAbstractAndStatic2() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setStatic(true);
		method.setAbstract(true);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setIllegalMethodName1() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setName("NewMethod");
	}

	@Test(expected = IllegalArgumentException.class)
	public void setIllegalMethodName2() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setName("new Method");
	}

	@Test(expected = IllegalArgumentException.class)
	public void setIllegalMethodName3() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setName("1newMethod");
	}

	@Test
	public void setMethodName() {
		LogicalClass logicalClass = new LogicalClass();
		Method method = logicalClass.addMethod();
		method.setName("myNewMethod");
		assertEquals("myNewMethod", method.getName());
	}
}
