package logicalobjects;

import static org.junit.Assert.*;

import org.junit.Test;

import logicalobjects.LogicalClass;
import logicalobjects.Method;

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

}
